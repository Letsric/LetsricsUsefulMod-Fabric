package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class AdvancedScreen extends Screen {

    private Screen parent;

    public AdvancedScreen(Screen parent) {
        super(new LiteralText("Advanced Options"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, 140, 204, 20, new LiteralText("UFM-Config Schreiben"), button -> {
            LetsricsUsefulMod.WriteUFMOptionsFile();
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, 164, 204, 20, new LiteralText("UFM-Config laden"), button -> {
            LetsricsUsefulMod.LoadUFMOptionsFile();
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("§l§cWARNUNG!"), this.width / 2, 60, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("mit diesen Befehlen kann man sehr"), this.width / 2, 70, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("leicht das Spiel zum abstürtzen"), this.width / 2, 80, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("bringen, da das laden der Configs"), this.width / 2, 90, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("nicht die Variablen leert!"), this.width / 2, 100, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("Wenn du dich nicht so gut außkennst, lass diese Optionen lieber!"), this.width / 2, 120, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(this.parent);
    }

}
