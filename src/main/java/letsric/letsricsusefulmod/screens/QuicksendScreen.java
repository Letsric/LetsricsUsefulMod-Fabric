package letsric.letsricsusefulmod.screens;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class QuicksendScreen extends Screen {
    Screen parent;
    public QuicksendScreen(Screen parent) {
        super(Text.literal("Quicksend"));
        this.parent = parent;
    }

    private static boolean deleteMode = false;

    @Override
    protected void init() {

        deleteMode = false;
        int currentRow = 0;
        int currentColum = 0;
        int avalibleColums = this.width / 150;

        // adding Components

        for (int i = 0; i < LetsricsUsefulMod.quicksendArray.size() ; i++) {
            String i2 = LetsricsUsefulMod.quicksendArray.get(i);
            String i3 = LetsricsUsefulMod.quicksendNameArray.get(i);
            int i4 = i;

            if(currentColum > avalibleColums) {
                currentColum = 0;
                currentRow++;
            }
            addDrawableChild(new ButtonWidget(this.width / 10 + currentColum * 110, this.height / 10 + currentRow * 25 + 20, 100, 20, Text.literal(i3), button -> {
                if(!deleteMode) {
                    if(i2.startsWith("/"))
                        MinecraftClient.getInstance().player.sendCommand(i2.substring(1));
                    else
                        MinecraftClient.getInstance().player.sendChatMessage(i2, Text.literal(i2));
                }
                if(deleteMode) {
                    LetsricsUsefulMod.quicksendArray.remove(i4);
                    LetsricsUsefulMod.quicksendNameArray.remove(i4);
                    LetsricsUsefulMod.WriteUFMOptionsFile();
                    MinecraftClient.getInstance().setScreen(this);
                }
            }));
            currentColum++;
        }

        addDrawableChild(new ButtonWidget(this.width - 100, 40, 25, 20, Text.literal("+"), button -> {
            MinecraftClient.getInstance().setScreen(new AddQuicksendScreen());
        }));

        addDrawableChild(new ButtonWidget(this.width - 150, 40, 50, 20, Text.literal("Löschen"), button -> {
            deleteMode = !deleteMode;
            if(deleteMode) button.setMessage(Text.literal("§eOBJEKT AUSWÄHLEN"));
            if(!deleteMode) button.setMessage(Text.literal("Löschen"));
        }));

    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(this.parent);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices,  textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
