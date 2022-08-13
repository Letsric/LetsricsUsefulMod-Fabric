package letsric.letsricsusefulmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SendMessageToPlayer {
    public static void SendMessageToPlayer(String message, boolean actionBar) {
        try {
            MinecraftClient.getInstance().player.sendMessage(Text.literal(message), actionBar);
        } catch (NullPointerException e) {
            // We can skip sending Messages while the mod is initializing
        }
    }
}
