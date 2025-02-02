package io.github.codeutilities.mixin.render;

import io.github.codeutilities.config.CodeUtilsConfig;
import io.github.codeutilities.util.networking.TPSUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugHud.class)
public class MixinDebugHud {
    private final MinecraftClient minecraftClient = MinecraftClient.getInstance();

    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    protected void getLeftText(CallbackInfoReturnable<List<String>> callbackInfoReturnable) {
        try {
            List<String> leftText = callbackInfoReturnable.getReturnValue();

            if (CodeUtilsConfig.getBool("f3Tps")) {
                leftText.add("");
                leftText.add(Formatting.UNDERLINE + "CodeUtilities");
                leftText.add("Client TPS: " + TPSUtil.TPS);
            }

            callbackInfoReturnable.setReturnValue(leftText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
