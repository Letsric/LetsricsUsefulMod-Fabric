package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import letsric.letsricsusefulmod.SendMessageToPlayer;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.Clipboard;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class UfmScreen extends Screen {

    public UfmScreen() {
        super(new LiteralText("Letsrics Useful Mod"));
    }

    @Override
    protected void init() {

        String FullbrightText = "§cFEHLER";
        if(LetsricsUsefulMod.fullbright) { FullbrightText = "§aAN"; }
        if(!LetsricsUsefulMod.fullbright) { FullbrightText = "§cAUS"; }
        addDrawableChild(new ButtonWidget(this.width / 2 - 45, 65, 90, 20, new LiteralText("Fullbright: " + FullbrightText), button -> {
            if(LetsricsUsefulMod.fullbright) {
                MinecraftClient.getInstance().options.gamma = 1;
                MinecraftClient.getInstance().options.write();
                LetsricsUsefulMod.fullbright = false;
                button.setMessage(new LiteralText("Fullbright: §cAUS"));
            }
            else {
                MinecraftClient.getInstance().options.gamma = 100;
                MinecraftClient.getInstance().options.write();
                LetsricsUsefulMod.fullbright = true;
                button.setMessage(new LiteralText("Fullbright: §aAN"));
            }
        }));

        String TablistInToggleModeString = "§cFEHLER";
        if(LetsricsUsefulMod.TablistInToggleMode) TablistInToggleModeString = "§aAN";
        if(!LetsricsUsefulMod.TablistInToggleMode) TablistInToggleModeString = "§cAUS";
        addDrawableChild(new ButtonWidget(this.width / 2 - 45, 89, 90, 20, new LiteralText("ToggleTab: " + TablistInToggleModeString), button -> {
            if (LetsricsUsefulMod.TablistInToggleMode) {
                LetsricsUsefulMod.TablistInToggleMode = false;
                LetsricsUsefulMod.WriteUFMOptionsFile();
            } else {
                LetsricsUsefulMod.TablistInToggleMode = true;
                LetsricsUsefulMod.WriteUFMOptionsFile();
            }
            MinecraftClient.getInstance().setScreen(this);
        }));

        addDrawableChild(new ButtonWidget(this.width / 2 - 140, 65, 90, 20, new LiteralText("Autotext"), button -> {
            MinecraftClient.getInstance().setScreen(new AutotextScreen());
        }));

        addDrawableChild(new ButtonWidget(this.width / 2 - 140, 89, 90, 20, new LiteralText("ChatsoundFilter"), button -> {
            MinecraftClient.getInstance().setScreen(new ChatSoundFilterScreen());
        }));

        addDrawableChild(new ButtonWidget(this.width / 2 + 50, 65, 90, 20, new LiteralText("Advanced"), button -> {
            MinecraftClient.getInstance().setScreen(new AdvancedScreen(this));
        }));

        String ButtonText;
        if (LetsricsUsefulMod.showHud) ButtonText = "§aAN";
        else ButtonText = "§cAUS";
        addDrawableChild(new ButtonWidget(this.width / 2 - 45, 113, 90, 20, new LiteralText("HUD: " + ButtonText), button -> {
            LetsricsUsefulMod.showHud = !LetsricsUsefulMod.showHud;
            LetsricsUsefulMod.WriteUFMOptionsFile();
            if (LetsricsUsefulMod.showHud) button.setMessage(new LiteralText("HUD: §aAN"));
            else button.setMessage(new LiteralText("HUD: §cAUS"));
        }));

        // Quicksend
        addDrawableChild(new ButtonWidget(this.width / 2 - 140, 113, 90, 20, new LiteralText("Quicksend"), button -> {
            MinecraftClient.getInstance().setScreen(new QuicksendScreen(new UfmScreen()));
        }));

        // Copy Coords
        addDrawableChild(new ButtonWidget(this.width / 2 + 50, 89, 90, 20, new LiteralText("Koortinaten Kopieren"), button -> {
            String coords = MinecraftClient.getInstance().player.getBlockX() + ", " + MinecraftClient.getInstance().player.getBlockY() + ", " + MinecraftClient.getInstance().player.getBlockZ();
            net.minecraft.client.util.Clipboard clipboard = new Clipboard();
            clipboard.setClipboard(this.client.getWindow().getHandle(), coords);
            SendMessageToPlayer.SendMessageToPlayer("§aKoordinaten Kopiert!", true);
            MinecraftClient.getInstance().setScreen(null);
        }));

    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new GameMenuScreen(true));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, "§nManager", this.width / 2 - 95, 50, 0xFFFFFF);
        drawCenteredText(matrices, textRenderer, "§nToggles", this.width / 2, 50, 0xFFFFFF);
        drawCenteredText(matrices, textRenderer, "§nSonstige", this.width / 2 + 95, 50, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
