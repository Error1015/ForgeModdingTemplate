package mod.author.examplemod.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "run")
    public void runInject(CallbackInfo ci) {
        System.out.println("Hello World from ExampleMixin: 14");
    }
}