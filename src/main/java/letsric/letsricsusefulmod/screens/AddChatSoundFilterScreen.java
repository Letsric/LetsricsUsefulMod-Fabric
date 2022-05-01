package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.ChatSound;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class AddChatSoundFilterScreen extends Screen {

    public AddChatSoundFilterScreen() {
        super(new LiteralText("Neuer ChatSoundFilter"));
    }

    // Initialize all Components
    @Override
    public void init() {
        // Add Text Field for the Filter
        TextFieldWidget FilterTextField = new TextFieldWidget(textRenderer, this.width / 2 - 100, this.height / 2 - 12, 200, 20, new LiteralText(""));
        addDrawableChild(FilterTextField);

        // Add Button to finish up
        addDrawableChild(new ButtonWidget(this.width / 2 - 50, this.height / 2 + 12, 100, 20, new LiteralText("Fertig"), action -> {
            String FilterText = FilterTextField.getText();
            if (!FilterText.isEmpty()) {
                ChatSound.Filters.add(FilterText);
                MinecraftClient.getInstance().setScreen(new ChatSoundFilterScreen());
            }
        }));

    }

    // Return to menu if Escape is pressed
    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(new ChatSoundFilterScreen());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

}
