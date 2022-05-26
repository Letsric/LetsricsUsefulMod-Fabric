package letsric.letsricsusefulmod.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class HelpScreen extends Screen {
    public HelpScreen() {
        super(new LiteralText("Hilfe"));
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new GameMenuScreen(true));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("§l§nGamma100:"), this.width / 2 - 150, 80, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("Stellt die Minecraft Helligkeit extrem hoch"), this.width / 2 - 150, 92, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("§l§nAutotext:"), this.width / 2 + 150, 80, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("Hiermit kannst du beim Drücken einer Taste auf"), this.width / 2 + 150, 92, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("deiner Tastatur eine beliebige Chatnachricht absenden"), this.width / 2 + 150, 102, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("§l§nToggleTab:"), this.width / 2 - 150, 120, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("Macht, dass du die Spielerliste (standertmäßig TAB)"), this.width / 2 - 150, 132, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("an und ausschalten kannst"), this.width / 2 - 150, 142, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("§l§nChatsoundfilter:"), this.width / 2 + 150, 120, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("Hiermit kannst du Zeichenfolgen definieren. Falls diese"), this.width / 2 + 150, 132, 0xFFFFFF);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, new LiteralText("in einer Chatnachricht gefunden werden, wird ein Ton gespielt."), this.width / 2 + 150, 142, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
