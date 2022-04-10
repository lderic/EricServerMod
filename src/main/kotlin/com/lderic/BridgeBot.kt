package com.lderic

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

object BridgeBot {
    var isConnected = false
    val coroutineScope = CoroutineScope(EmptyCoroutineContext)
    lateinit var qq: SendChannel<Frame.Text>

    val client = HttpClient() {
        install(WebSockets)
    }

    fun init() = coroutineScope.launch {
        client.webSocket(port = 6888, host = "0.0.0.0", path = "/mc") {
            println("Connected")
            qq = outgoing
            isConnected = true
            sendToQQ("The bridge Bot is connected")

            for (frame in incoming) {
                if (frame is Frame.Text) {
                    sendToGame(frame.readText())
                }
            }
            isConnected = false
        }
    }

    fun sendToQQ(message: String) =  coroutineScope.launch {
        if (isConnected) {
            println("sent to qq: $message")
            qq.send(Frame.Text(message))
        }
    }

    fun sendToGame(message: String) = coroutineScope.launch {
        Mod.sendToGame(message)
    }
}