package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.AutoText;
import letsric.letsricsusefulmod.LetsricsUsefulMod;
import letsric.letsricsusefulmod.SendMessageToPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class AddAutotextScreen extends Screen {

    private boolean setKeyMode = false;
    private InputUtil.Key Key;
    private TextFieldWidget nameField;
    private TextFieldWidget commandField;

    private Text getButtonText() {
        if (this.setKeyMode) return new LiteralText("§eDrücke eine Taste...");
        else if (this.Key != null) return this.Key.getLocalizedText();
        else return new LiteralText("Klicke um Taste zu wählen");
    }

    protected AddAutotextScreen() {
        super(new LiteralText("Autotext Hinzufügen"));
    }

    @Override
    public void init() {
        nameField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 + 5, 200, 20, new LiteralText(""));
        commandField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 + 29, 200, 20, new LiteralText(""));
        addDrawableChild(commandField);
        addDrawableChild(nameField);
        addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 2 + 53, 200, 20, getButtonText(), action -> {
            this.setKeyMode = true;
            reload(nameField.getText(), commandField.getText());
        }));
        addDrawableChild(new ButtonWidget(this.width / 2 -100, this.height / 2 + 77, 200, 20, new LiteralText("Fertig"), action -> {
            String name = nameField.getText();
            String command = commandField.getText();
            if (!command.isEmpty() && !name.isEmpty() && Key != null) {
                AutoText.addAutoTextKeybind(Key, command, name);
                MinecraftClient.getInstance().setScreen(new AutotextScreen());
            }
            LetsricsUsefulMod.WriteUFMOptionsFile();
        }));
    }

    private void reload(String name, String command) {
        MinecraftClient.getInstance().setScreen(this);
        nameField.setText(name);
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
            reload(nameField.getText(), commandField.getText());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, this.height / 2 - 15, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
