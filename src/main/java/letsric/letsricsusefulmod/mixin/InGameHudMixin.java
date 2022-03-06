package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private boolean TablistToggled = false;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
    private void InjectIngameHud2(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (LetsricsUsefulMod.TablistInToggleMode) {
            if (this.TablistToggled) MinecraftClient.getInstance().options.playerListKey.setPressed(true);
            if(MinecraftClient.getInstance().options.playerListKey.wasPressed()) {
                this.TablistToggled = !this.TablistToggled;
            }
        }
    }
}
