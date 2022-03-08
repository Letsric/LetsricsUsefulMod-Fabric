package letsric.letsricsusefulmod;

import letsric.letsricsusefulmod.client.AutoText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class onChatEvent {
    public static ArrayList<String> autoTextArray = new ArrayList<>();
    public static void onChatEvent(ChatScreen thisObject, TextFieldWidget ChatField, String message) {
        if(message.startsWith(",ufm")) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addToMessageHistory(message);
            {
                if (message.startsWith(",ufm gamma100")) {
                    MinecraftClient.getInstance().options.gamma = 100;
                    MinecraftClient.getInstance().options.write();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aGamma auf 100 gesetzt!"), true);
                } else if (message.startsWith(",ufm rloptionstxt")) {
                    MinecraftClient.getInstance().options.load();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aOptions.txt neu geladen!"), true);
                } else if (message.startsWith(",ufm toggletab")) {
                    if (LetsricsUsefulMod.TablistInToggleMode) {
                        LetsricsUsefulMod.TablistInToggleMode = false;
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §cdeaktiviert§f!"), true);
                    } else {
                        LetsricsUsefulMod.TablistInToggleMode = true;
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §aaktiviert§f!"), true);
                    }
                } else if (message.startsWith(",ufm autotext")) {
                    if (message.startsWith(",ufm autotext add")) {
                        String trimmedMessage = message.substring(18);
                        if (AutoText.addAutoTextKeybind(trimmedMessage) == 0) {
                            autoTextArray.add(trimmedMessage);
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aHinzugefügt!"), true);
                        }
                    } else if (message.startsWith(",ufm autotext list")) {
                        if (!autoTextArray.isEmpty()) {
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("------------------------"), false);
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a§lAutotext:"), false);
                            for (int i = 0; i < autoTextArray.size(); i++) {
                                MinecraftClient.getInstance().player.sendMessage(new LiteralText("§b " + autoTextArray.get(i)), false);
                            }
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("------------------------"), false);
                        } else MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cAutoText ist leer!"), false);
                    } else if (message.startsWith(",ufm autotext remove")) {
                        String trimmedMessage = message.substring(21);
                        if (AutoText.removeAutoTextKeybind(trimmedMessage) == 0) {
                            autoTextArray.remove(trimmedMessage);
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aEntfernt! §c(es sei denn es wurde nie hinzugefügt...)"), true);
                        }
                    } else {
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§lAUTOTEXT:"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm autotext add <KEY>;<COMMAND>§f:"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   Einen neuen Autotext Hotkey hinzufügen."), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   §cALLE TASTEN MÜSSEN IM ENGLISCHEN LAYOUT SEIN!"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   Liste von allen möglichen Tasten ist auf dem Github!"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   <KEY> = Die Taste für den neuen Hotkey"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   <MESSAGE> = Die Chatnachricht, die gesendet werden soll."), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   §eBEISPIEL§f: §b,ufm autotext add c;/gamemode creative"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm autotect list§f:"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   Alle Autotexts auflisten"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm autotext remove <KEY>;<COMMAND>§f:"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   Das selbe wie autotext add, nur das"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   es keinen neuen Hotkey hinzufügt,"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   sondern einen vorhandenen löscht."), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   Wenn dieser nicht vorhanden ist,"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   passiert nichts."), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("   §eBEISPIEL§f: §b,ufm autotext remove c;/gamemode creative"), false);
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                    }
                } else {
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§lCOMMANDS:"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm gamma100§f - Gamma auf 100 setzen"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm rloptionstxt§f - options.txt neu laden"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm toggletab§f - ToggleTab aktivieren"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm autotext§f - Autotext: Für Hilfe: '§b,ufm autotext§f'"), false);
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a-------------------------------------"), false);
                }
            }
            ChatField.setText("");
        }
    }
}
