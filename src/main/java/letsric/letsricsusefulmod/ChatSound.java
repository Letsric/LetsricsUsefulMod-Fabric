package letsric.letsricsusefulmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ChatSound {
    public static ArrayList<String> Filters = new ArrayList<>();

    public static void addFilter(String Filter) {
        Filters.add(Filter);
        SendMessageToPlayer.SendMessageToPlayer("§AChatSoundFilter hinzugefügt!", true);
        LetsricsUsefulMod.WriteUFMOptionsFile();
    }

    public static void removeFilter(String Filter) {
        int temp_size = Filters.size();
        for (int i = 0 ; i < Filters.size() ; i++) if (Filters.get(i).startsWith(Filter)) Filters.remove(i);
        if (Filters.size() < temp_size) SendMessageToPlayer.SendMessageToPlayer("§aEntfernt!", true);
        else SendMessageToPlayer.SendMessageToPlayer("§cNichts gefunden, dass mit §b" + Filter + "§c beginnt!", true);
        LetsricsUsefulMod.WriteUFMOptionsFile();
    }

    public static void printFilters() {
        if (!Filters.isEmpty()) {
            SendMessageToPlayer.SendMessageToPlayer("------------------------", false);
            SendMessageToPlayer.SendMessageToPlayer("§a§lChatSoundFilter:", false);
            for (int i = 0; i < Filters.size(); i++)
                SendMessageToPlayer.SendMessageToPlayer("§b " + Filters.get(i), false);
            SendMessageToPlayer.SendMessageToPlayer("------------------------", false);
        } else SendMessageToPlayer.SendMessageToPlayer("§cEs gibt keinen ChatSoundFilter!", true);
    }

    public static void messageReceived(Text message) {
        for (int i = 0 ; i < Filters.size() ; i++) {
            if (message.toString().contains(Filters.get(i))) {
                MinecraftClient.getInstance().player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 2f, 1f);
            }
        }
    }
}