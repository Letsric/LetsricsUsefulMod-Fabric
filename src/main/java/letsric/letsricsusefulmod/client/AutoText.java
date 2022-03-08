package letsric.letsricsusefulmod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class AutoText {
    public static ArrayList<KeyBinding> Autotexts = new ArrayList<>();
    public static int addAutoTextKeybind(String message) {
        String command;
        String key;
        try {
            String SplitMessage[] = message.split(";");
            key = SplitMessage[0];
            command = SplitMessage[1];
        } catch (Exception e) {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cFEHLER! §b(,ufm autotext für Hilfe)"), false);
            return 1;
        }
        InputUtil.Key key2;
        try {
            key2 = InputUtil.fromTranslationKey("key.keyboard." + key);
        } catch (Exception e) {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cDiese Taste existiert nicht! §b(nutze das englische Layout!)"), false);
            return 1;
        }
        KeyBinding KeyBinding = new KeyBinding(command, key2.getCode(), "AutoText");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBinding.wasPressed() && Autotexts.contains(KeyBinding)) {
                MinecraftClient.getInstance().player.sendChatMessage(command);
                MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aAutoText Ausgeführt!"), true);
            }
        });
        Autotexts.add(KeyBinding);
        return 0;
    }
    public static int removeAutoTextKeybind(String message) {
        String command;
        String key;
        try {
            String SplitMessage[] = message.split(";");
            key = SplitMessage[0];
            command = SplitMessage[1];
        } catch (Exception e) {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cFEHLER! §b(,ufm autotext für Hilfe)"), false);
            return 1;
        }
        InputUtil.Key key2;
        try {
            key2 = InputUtil.fromTranslationKey("key.keyboard." + key);
        } catch (Exception e) {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("§cDiese Taste existiert nicht! §b(nutze das englische Layout!)"), false);
            return 1;
        }
        KeyBinding KeyBinding = new KeyBinding(command, key2.getCode(), "AutoText");
        Autotexts.remove(KeyBinding);
        return 0;
    }
}