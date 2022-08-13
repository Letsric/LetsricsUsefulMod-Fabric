package letsric.letsricsusefulmod.client;

import letsric.letsricsusefulmod.screens.QuicksendScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class LetsricsUsefulModClient implements ClientModInitializer {

    private static KeyBinding openQuicksendScreen;

    @Override
    public void onInitializeClient() {
        openQuicksendScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Quicksend", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_U, // The keycode of the key
                "Letsrics Useful Mod" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openQuicksendScreen.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new QuicksendScreen(null));
            }
        });
    }
}
