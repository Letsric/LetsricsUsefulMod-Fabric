package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.screens.UfmScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At(value = "TAIL"))
    private void InjectInitWidgets(CallbackInfo ci) {

        addDrawableChild(new ButtonWidget(this.width / 2 - 102, 5, 204, 20, new LiteralText("Letsrics Useful Mod"), button -> {
            MinecraftClient.getInstance().setScreen(new UfmScreen());
        }));

    }

}
