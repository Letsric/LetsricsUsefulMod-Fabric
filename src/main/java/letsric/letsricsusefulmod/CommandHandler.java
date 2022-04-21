package letsric.letsricsusefulmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;

import static letsric.letsricsusefulmod.LetsricsUsefulMod.autoTextArray;

public class CommandHandler {
    public static void onChatEvent(ChatScreen thisObject, TextFieldWidget ChatField, String message) {
        if(message.startsWith(",ufm")) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addToMessageHistory(message);


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
                    LetsricsUsefulMod.WriteUFMOptionsFile();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §cdeaktiviert§f!"), true);
                } else {
                    LetsricsUsefulMod.TablistInToggleMode = true;
                    LetsricsUsefulMod.WriteUFMOptionsFile();
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("ToggleTab §aaktiviert§f!"), true);
                }


            } else if (message.startsWith(",ufm autotext")) {
                if (message.startsWith(",ufm autotext add")) {
                    String trimmedMessage = message.substring(18);
                    if (AutoText.addAutoTextKeybind(trimmedMessage) == 0) {
                        autoTextArray.add(trimmedMessage);
                        LetsricsUsefulMod.WriteUFMOptionsFile();
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
                    } else
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cAutoText ist leer!"), false);

                } else if (message.startsWith(",ufm autotext remove")) {
                    String trimmedMessage = message.substring(21);
                    String fullMessage = "";
                    for (int i = 0; i < autoTextArray.size(); i++) {
                        String i2 = autoTextArray.get(i);
                        if (i2.startsWith(trimmedMessage)) fullMessage = i2;
                    }
                    if (!fullMessage.equals("")) {
                        if (AutoText.removeAutoTextKeybind(fullMessage) == 0) {
                            autoTextArray.remove(fullMessage);
                            LetsricsUsefulMod.WriteUFMOptionsFile();
                            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aEntfernt!"), true);
                        }
                    } else
                        MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cNichts gefunden, dass mit §b" + trimmedMessage + "§c beginnt!"), true);

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
                    MinecraftClient.getInstance().player.sendMessage(new LiteralText("§a,ufm autotext list§f:"), false);
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

            } else if (message.startsWith(",ufm chatsoundfilter")) {
                if (message.startsWith(",ufm chatsoundfilter add")) ChatSound.addFilter(message.substring(25));
                else if (message.startsWith(",ufm chatsoundfilter remove")) ChatSound.removeFilter(message.substring(28));
                else if (message.startsWith(",ufm chatsoundfilter list")) ChatSound.printFilters();
                else {
                    SendMessageToPlayer.SendMessageToPlayer("§a------------------------------------------", false);
                    SendMessageToPlayer.SendMessageToPlayer("§lChatSound-Filter", false);
                    SendMessageToPlayer.SendMessageToPlayer(" Mit einem ChatSound-Filter kannst du Zeichenfolgen definieren.", false);
                    SendMessageToPlayer.SendMessageToPlayer(" Wenn eine dieser Zeichenfolgen im Chat gefunden wird, wird ein", false);
                    SendMessageToPlayer.SendMessageToPlayer(" Ton abgespielt.", false);
                    SendMessageToPlayer.SendMessageToPlayer("", false);
                    SendMessageToPlayer.SendMessageToPlayer(" §lUsage:", false);
                    SendMessageToPlayer.SendMessageToPlayer(" - §a,ufm chatsoundfilter add <ZEICHENFOLGE>", false);
                    SendMessageToPlayer.SendMessageToPlayer("   Eine Zeichenfolge hinzufügen", false);
                    SendMessageToPlayer.SendMessageToPlayer("", false);
                    SendMessageToPlayer.SendMessageToPlayer(" - §a,ufm chatsoundfilter remove <ZEICHENFOLGE>", false);
                    SendMessageToPlayer.SendMessageToPlayer("   Eine Zeichenfolge entfernen", false);
                    SendMessageToPlayer.SendMessageToPlayer("", false);
                    SendMessageToPlayer.SendMessageToPlayer(" - §a,ufm chatsoundfilter list", false);
                    SendMessageToPlayer.SendMessageToPlayer("   Alle Zeichenfolgen in einer Liste anzeigen", false);
                    SendMessageToPlayer.SendMessageToPlayer("§a------------------------------------------", false);
                }


            } else if (message.startsWith(",ufm config write")) {
                LetsricsUsefulMod.WriteUFMOptionsFile();
            } else if (message.startsWith(",ufm config load")) {
                LetsricsUsefulMod.LoadUFMOptionsFile();


            } else {
                SendMessageToPlayer.SendMessageToPlayer("§a-------------------------------------", false);
                SendMessageToPlayer.SendMessageToPlayer("§lCOMMANDS:", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm gamma100§f - Gamma auf 100 setzen", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm rloptionstxt§f - options.txt neu laden", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm toggletab§f - ToggleTab aktivieren / deaktivieren", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm autotext§f - Für Hilfe: '§b,ufm autotext§f'", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm chatsoundfilter§f - Für Hilfe: '§b,ufm chatsoundfilter§f'", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm config write§f - UFM-config schreiben§c*", false);
                SendMessageToPlayer.SendMessageToPlayer("§a,ufm config load§f - UFM-config laden§c*", false);
                SendMessageToPlayer.SendMessageToPlayer("§c*§f = §lNUR FÜR EXPERTEN, wird auch automatisch gemacht!", false);
                SendMessageToPlayer.SendMessageToPlayer("§a-------------------------------------", false);
            }
            ChatField.setText("");
        }
    }
}
