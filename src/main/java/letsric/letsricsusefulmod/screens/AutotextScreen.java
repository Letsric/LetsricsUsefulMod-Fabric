package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.AutoText;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class AutotextScreen extends Screen {

    public AutotextScreen() {
        super(new LiteralText("Autotext Konfiguration"));
    }

    @Override
    public void init() {
        for (int i = 0 ; i < AutoText.Autotexts.size() ; i++) {
            int i2 = i;
            addDrawableChild(new ButtonWidget(this.width / 2 + 100, i * 24 + 80, 98, 20, new LiteralText("Entfernen"), action -> {
                AutoText.Autotexts.remove(i2);
                LetsricsUsefulMod.autoTextArray.remove(i2);
                LetsricsUsefulMod.WriteUFMOptionsFile();
                MinecraftClient.getInstance().setScreen(this);
            }));
        }

        addDrawableChild(new ButtonWidget(this.width - 100, 40, 25, 20, new LiteralText("+"), action -> {
            MinecraftClient.getInstance().setScreen(new AddAutotextScreen());
        }));
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new UfmScreen());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        for (int i = 0 ; i < AutoText.Autotexts.size() ; i++) {
            drawTextWithShadow(matrices, this.textRenderer, new LiteralText(LetsricsUsefulMod.autoTextArray.get(i).split(";")[1]), this.width / 2 - 100, i * 24 + 85, 0xFFFFFF);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }
}
