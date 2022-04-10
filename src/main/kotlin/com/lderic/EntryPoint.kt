package com.lderic

import net.fabricmc.api.DedicatedServerModInitializer

class EntryPoint : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Mod.init()
    }
}