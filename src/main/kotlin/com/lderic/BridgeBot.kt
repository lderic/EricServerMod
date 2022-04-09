package com.lderic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import java.io.File
import kotlin.coroutines.EmptyCoroutineContext

object BridgeBot : Thread("bot-main") {
    private val bot: Bot = BotFactory.newBot(3283056961, "") {
        this.workingDir = File(System.getProperty("user.dir") + "/temp")
    }
    private val botCoroutineScope = CoroutineScope(EmptyCoroutineContext)

    override fun run() {
        botCoroutineScope.launch {
            bot.getFriend(1278350812)!!.sendMessage("Bot has been initialized")
        }
    }
}