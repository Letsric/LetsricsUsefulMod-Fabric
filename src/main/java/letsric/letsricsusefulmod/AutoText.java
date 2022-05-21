package letsric.letsricsusefulmod;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class AutoText {
    public static ArrayList<KeyBinding> Autotexts = new ArrayList<>();
    public static void addAutoTextKeybind(InputUtil.Key Key, String command) {
        KeyBinding KeyBinding = new KeyBinding(Key.getTranslationKey(), Key.getCode(), "AutoText");
        LetsricsUsefulMod.autoTextArray.add(Key.getTranslationKey() + ";" + command);
        Autotexts.add(KeyBinding);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBinding.wasPressed() && Autotexts.contains(KeyBinding)) {
                MinecraftClient.getInstance().player.sendChatMessage(command);
                MinecraftClient.getInstance().player.sendMessage(new LiteralText("§aAutoText Ausgeführt!"), true);
            }
        });
    }
}