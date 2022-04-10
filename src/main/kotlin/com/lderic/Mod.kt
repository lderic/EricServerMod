package com.lderic

import com.lderic.BridgeBot.sendToQQ
import net.minecraft.network.MessageType
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
import net.minecraft.server.dedicated.MinecraftDedicatedServer
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.Util
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

object Mod {
    val logger: Logger = LogManager.getLogger("Eric's Mod")
    lateinit var server: MinecraftDedicatedServer

    fun init() {
        BridgeBot.init()
        logger.info("The mod has been initialized")
    }


    fun onMessage(text: Text, messageType: MessageType, uuid: UUID) {
        if (text.string.contains("<")) {
            text.string.split("<", limit = 2)[1].split(">", limit = 2).let {
                sendToQQ("${it[0]}: ${it[1]}")
            }
        } else {
            sendToQQ(text.string)
        }
    }

    fun sendToGame(message: String) {
        server.playerManager.sendToAll(
            GameMessageS2CPacket(
                LiteralText(message),
                MessageType.CHAT,
                Util.NIL_UUID
            )
        )
    }
}