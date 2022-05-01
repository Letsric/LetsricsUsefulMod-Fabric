package letsric.letsricsusefulmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ChatSound {
    public static ArrayList<String> Filters = new ArrayList<>();

    public static void messageReceived(Text message) {
        for (int i = 0 ; i < Filters.size() ; i++) {
            if (message.toString().contains(Filters.get(i))) {
                MinecraftClient.getInstance().player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 2f, 1f);
            }
        }
    }
}