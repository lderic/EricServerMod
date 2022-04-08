package com.lderic

import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory

object BridgeBot {
    val bot: Bot = BotFactory.newBot(3283056961, "")

    suspend fun init() {
        bot.getFriend(1278350812)!!.sendMessage("Bot has been initialized")
    }
}