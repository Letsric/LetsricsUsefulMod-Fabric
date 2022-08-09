package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.ChatSound;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    public abstract TextRenderer getTextRenderer();

    private boolean TablistToggled = false;

    // Toggletab
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
    private void InjectInGameHud1(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (LetsricsUsefulMod.TablistInToggleMode) {
            if (this.TablistToggled) MinecraftClient.getInstance().options.playerListKey.setPressed(true);
            if (MinecraftClient.getInstance().options.playerListKey.wasPressed()) {
                this.TablistToggled = !this.TablistToggled;
            }
        }
    }

    // Chatsoundfilter
    @Inject(method = "addChatMessage", at = @At(value = "TAIL"))
    private void InjectAddChatMessage(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        ChatSound.messageReceived(message);
    }

    // UFM-HUD (FPS, X, Y, Z)
    @Inject(method = "render", at = @At(value = "HEAD"))
    public void InjectRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

        if (LetsricsUsefulMod.showHud) {
            TextRenderer TextRenderer = getTextRenderer();

            // FPS
            TextRenderer.draw(matrices, "[", 5, 5, 0xFFFFFF);
            TextRenderer.draw(matrices, "]", 27, 5, 0xFFFFFF);
            TextRenderer.draw(matrices, "FPS", 9, 5, 0x8f2bce);
            TextRenderer.draw(matrices, String.valueOf(((MinecraftClientAccessor) MinecraftClient.getInstance()).getCurrentFps()), 34, 5, 0x126ed1);

            // XYZ
            BlockPos BlockPos = MinecraftClient.getInstance().player.getBlockPos();
            TextRenderer.draw(matrices, "[", 5, 14, 0xFFFFFF);
            TextRenderer.draw(matrices, "]", 27, 14, 0xFFFFFF);
            TextRenderer.draw(matrices, "XYZ", 9, 14, 0x8f2bce);
            TextRenderer.draw(matrices, String.valueOf(BlockPos.getX()) + ", " + BlockPos.getY() + ", " + BlockPos.getZ(), 34, 14, 0x126ed1);
        }
    }
}
