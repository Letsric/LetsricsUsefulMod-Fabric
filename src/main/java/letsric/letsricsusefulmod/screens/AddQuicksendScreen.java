package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class AddQuicksendScreen extends Screen {
    public AddQuicksendScreen() {
        super(Text.literal("Quicksend hinzufügen"));
    }

    private static TextFieldWidget NameField;
    private static TextFieldWidget MsgField;

    @Override
    protected void init() {

        // Textfeld initialisieren
        NameField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 - 38, 200, 20, Text.literal(""));
        MsgField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 + 2, 200, 20, Text.literal(""));

        // Komponente hinzufügen
        addDrawableChild(NameField);
        addDrawableChild(MsgField);
        addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 2 + 27, 200, 20, Text.literal("Fertig"), button -> {
            if(!MsgField.getText().isEmpty() && !NameField.getText().isEmpty()) {
                LetsricsUsefulMod.quicksendArray.add(MsgField.getText());
                LetsricsUsefulMod.quicksendNameArray.add(NameField.getText());
                LetsricsUsefulMod.WriteUFMOptionsFile();
                this.close();
            }
        }));

    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new QuicksendScreen(new UfmScreen()));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        drawCenteredText(matrices, textRenderer, "§7Name", this.width / 2 - 89, this.height / 2 - 48, 0xFFFFFF);
        drawCenteredText(matrices, textRenderer, "§7Nachricht", this.width / 2 - 77, this.height / 2 - 8, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }



}
