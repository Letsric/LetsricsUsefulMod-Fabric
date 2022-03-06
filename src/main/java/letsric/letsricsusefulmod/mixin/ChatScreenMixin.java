package letsric.letsricsusefulmod.mixin;

import letsric.letsricsusefulmod.LetsricsUsefulMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin{
    @Inject(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;getText()Ljava/lang/String;"))
    private void injectedChatScreen(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        TextFieldWidget ChatField = ((ChatScreenAccessor)this).getChatField();
        String message = ChatField.getText().trim();
        if(message.startsWith(",ufm")) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addToMessageHistory(message);
            switch (message) {
                case ",ufm gamma100":
                    MinecraftClient.getInstance().options.gamma = 100;
                    MinecraftClient.getInstance().options.write();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aGamma auf 100 gesetzt!"), true);
                    break;
                case ",ufm rloptionstxt":
                    MinecraftClient.getInstance().options.load();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aOptions.txt neu geladen!"), true);
                    break;
                case ",ufm toggletab":
                    if (LetsricsUsefulMod.TablistInToggleMode) {
                        LetsricsUsefulMod.TablistInToggleMode = false;
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §cdeaktiviert§f!"), true);
                    } else {
                        LetsricsUsefulMod.TablistInToggleMode = true;
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §aaktiviert§f!"), true);
                    }
                    break;
                default:
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§lCOMMANDS:"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm gamma100§f - Gamma auf 100 setzen"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm rloptionstxt§f - options.txt neu laden"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm toggletab§f - ToggleTab aktivieren"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                    break;
            }
            ChatField.setText("");
        }
    }
}
