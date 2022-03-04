package letsric.letsricsusefulmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatScreen.class)
public class ChatScreenMixin{
    @Redirect(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;sendMessage(Ljava/lang/String;)V"))
    private void injectedChatScreen(ChatScreen instance, String message) {
        if(message.startsWith(",ufm")) {
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
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cDiese Funktion ist noch nicht fertig!"), true);
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
        } else {
            ((ChatScreen)(Object)this).sendMessage(message);
        }
    }
}
