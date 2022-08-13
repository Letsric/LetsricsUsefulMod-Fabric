package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.ChatSound;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    // Chatsoundfilter
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("TAIL"))
    private void InjectAddMessage(Text message, CallbackInfo ci) {
        ChatSound.messageReceived(message);
    }
}
