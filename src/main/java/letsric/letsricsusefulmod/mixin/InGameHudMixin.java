package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.ChatSound;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private boolean TablistToggled = false;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
    private void InjectInGameHud1(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (LetsricsUsefulMod.TablistInToggleMode) {
            if (this.TablistToggled) MinecraftClient.getInstance().options.playerListKey.setPressed(true);
            if(MinecraftClient.getInstance().options.playerListKey.wasPressed()) {
                this.TablistToggled = !this.TablistToggled;
            }
        }
    }

    @Inject(method = "addChatMessage", at = @At(value = "TAIL"))
    private void InjectInGameHud2(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        ChatSound.messageReceived(message);
    }
}
