package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import letsric.letsricsusefulmod.screens.AdvancedScreen;
import letsric.letsricsusefulmod.screens.AutotextScreen;
import letsric.letsricsusefulmod.screens.ChatSoundFilterScreen;
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

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 24 + -16, 98, 20, new LiteralText("Gamma 100"), button -> {
            MinecraftClient.getInstance().options.gamma = 100;
            MinecraftClient.getInstance().options.write();
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aGamma auf 100 gesetzt!"), true);
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 48 + -16, 98, 20, new LiteralText("Reload Options.txt"), button -> {
            MinecraftClient.getInstance().options.load();
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aOptions.txt neu geladen!"), true);
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));

        String TablistInToggleModeString = "§cFEHLER";
        if(LetsricsUsefulMod.TablistInToggleMode) TablistInToggleModeString = "§aAN";
        if(!LetsricsUsefulMod.TablistInToggleMode) TablistInToggleModeString = "§cAUS";

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 72 + -16, 98, 20, new LiteralText("ToggleTab: " + TablistInToggleModeString), button -> {
            if (LetsricsUsefulMod.TablistInToggleMode) {
                LetsricsUsefulMod.TablistInToggleMode = false;
                LetsricsUsefulMod.WriteUFMOptionsFile();
            } else {
                LetsricsUsefulMod.TablistInToggleMode = true;
                LetsricsUsefulMod.WriteUFMOptionsFile();
            }
            MinecraftClient.getInstance().setScreen(this);
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 96 + -16, 98, 20, new LiteralText("Autotext"), button -> {
            MinecraftClient.getInstance().setScreen(new AutotextScreen());
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 120 + -16, 98, 20, new LiteralText("ChatsoundFilter"), button -> {
            MinecraftClient.getInstance().setScreen(new ChatSoundFilterScreen());
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 107, this.height / 4 + 144 + -16, 98, 20, new LiteralText("Advanced"), button -> {
            MinecraftClient.getInstance().setScreen(new AdvancedScreen(this));
        }));

    }

}
