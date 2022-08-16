package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.ChatSound;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class MessageHandlerMixin {
    // Chatsoundfilter
    @Inject(method = "onChatMessage", at = @At("HEAD"))
    private void InjectOnChatMessage(SignedMessage message, MessageType.Parameters params, CallbackInfo ci) {
        ChatSound.messageReceived(message.getContent());
    }
}
