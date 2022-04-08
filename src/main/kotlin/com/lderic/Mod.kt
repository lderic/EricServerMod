package com.lderic

import net.minecraft.server.dedicated.MinecraftDedicatedServer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Mod {
    val logger: Logger = LogManager.getLogger("Eric's Mod")
    lateinit var server: MinecraftDedicatedServer

    fun init() {
        logger.info("the mod has been initialized")
    }
}