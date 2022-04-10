package com.lderic.mixins;

import com.lderic.Mod;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "broadcastChatMessage", at = @At("RETURN"))
    public void onMessage(Text text, MessageType messageType, UUID uUID, CallbackInfo ci) {
        Mod.INSTANCE.onMessage(text, messageType, uUID);
    }
}
