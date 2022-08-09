package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.ChatSound;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class ChatSoundFilterScreen extends Screen {

    public ChatSoundFilterScreen() {
        super(new LiteralText("Chatsoundfilter Konfiguration"));
    }

    @Override
    public void init() {
        for (int i = 0; i < ChatSound.Filters.size(); i++) {
            int i2 = i;
            addDrawableChild(new ButtonWidget(this.width / 2 + 100, i * 24 + 80, 98, 20, new LiteralText("Entfernen"), action -> {
                ChatSound.Filters.remove(i2);
                LetsricsUsefulMod.WriteUFMOptionsFile();
                MinecraftClient.getInstance().setScreen(this);
            }));
        }

        addDrawableChild(new ButtonWidget(this.width - 100, 40, 25, 20, new LiteralText("+"), action -> {
            MinecraftClient.getInstance().setScreen(new AddChatSoundFilterScreen());
        }));
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new UfmScreen());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        for (int i = 0; i < ChatSound.Filters.size() ; i++) {
            drawTextWithShadow(matrices, this.textRenderer, new LiteralText(ChatSound.Filters.get(i)), this.width / 2 - 100, i * 24 + 85, 0xFFFFFF);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }
}
