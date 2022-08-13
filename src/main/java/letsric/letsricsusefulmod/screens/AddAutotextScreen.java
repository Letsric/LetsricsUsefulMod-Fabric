package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.AutoText;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class AddAutotextScreen extends Screen {

    private boolean setKeyMode = false;
    private InputUtil.Key Key;
    private TextFieldWidget commandField;

    private Text getButtonText() {
        if (this.setKeyMode) return Text.literal("§eDrücke eine Taste...");
        else if (this.Key != null) return this.Key.getLocalizedText();
        else return Text.literal("Klicke um Taste zu wählen");
    }

    protected AddAutotextScreen() {
        super(Text.literal("Autotext Hinzufügen"));
    }

    @Override
    public void init() {
        commandField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 -11, 200, 20, Text.literal("Chat-Nachricht"));
        addDrawableChild(commandField);
        addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 2 + 13, 200, 20, getButtonText(), action -> {
            this.setKeyMode = true;
            reload(commandField.getText());
        }));
        addDrawableChild(new ButtonWidget(this.width / 2 -100, this.height / 2 + 36, 200, 20, Text.literal("Fertig"), action -> {
            String command = commandField.getText();
            if (!command.isEmpty() && Key != null) {
                AutoText.addAutoTextKeybind(Key, command);
                MinecraftClient.getInstance().setScreen(new AutotextScreen());
            }
            LetsricsUsefulMod.WriteUFMOptionsFile();
        }));
    }

    private void reload(String command) {
        MinecraftClient.getInstance().setScreen(this);
        commandField.setText(command);
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new AutotextScreen());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.setKeyMode) {
            this.Key = InputUtil.fromKeyCode(keyCode, scanCode);
            this.setKeyMode = false;
            reload(commandField.getText());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, Text.literal("§7Chat-Nachricht"), this.width / 2 - 62, this.height / 2 - 21, 0xFFFFFF);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, this.height / 2 - 35, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
