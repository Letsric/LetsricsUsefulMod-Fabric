package letsric.letsricsusefulmod.mixin;

import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleOption.class)
public class SimpleOptionMixin<T> {
    @Shadow private T value;

    @Inject(method = "setValue", at = @At("TAIL"))
    private void InjectSetValue(T value, CallbackInfo ci) {
        this.value = value;
    }
}
