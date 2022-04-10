package com.lderic

import com.lderic.BridgeBot.sendToQQ
import net.minecraft.network.MessageType
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
import net.minecraft.server.dedicated.MinecraftDedicatedServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.LiteralText
import net.minecraft.text.MutableText
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Util
import net.minecraft.world.World
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

    fun onDeath(player: ServerPlayerEntity) {
        sendToGame(
            LiteralText("${player.name}在")
                .append(player.dimText)
                .append("的")
                .append("(${player.blockPos.x}, ${player.blockPos.y}, ${player.blockPos.z})")
                .append("处死亡了")
        )
    }

    fun sendToGame(message: String) = sendToGame(LiteralText(message))

    fun sendToGame(message: Text) = server.playerManager.sendToAll(
        GameMessageS2CPacket(
            message,
            MessageType.CHAT,
            Util.NIL_UUID
        )
    )

    val ServerPlayerEntity.dim get() = this.world.registryKey

    val ServerPlayerEntity.dimText: MutableText
        get() = when (dim) {
            World.OVERWORLD -> LiteralText("主世界").setStyle(Style.EMPTY.withColor(Formatting.GREEN))
            World.NETHER -> LiteralText("地狱").setStyle(Style.EMPTY.withColor(Formatting.RED))
            World.END -> LiteralText("末地").setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE))
            else -> LiteralText("")
        }
}