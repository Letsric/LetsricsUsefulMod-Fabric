package letsric.letsricsusefulmod.mixin;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin{

    @Inject(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;getText()Ljava/lang/String;"))
    private void injectedChatScreen(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        ChatScreen thisObject = ((ChatScreen)(Object)this);
        TextFieldWidget ChatField = ((ChatScreenAccessor)thisObject).getChatField();
        String message = ChatField.getText().trim();
        letsric.letsricsusefulmod.onChatEvent.onChatEvent(thisObject, ChatField, message);
    }
}
