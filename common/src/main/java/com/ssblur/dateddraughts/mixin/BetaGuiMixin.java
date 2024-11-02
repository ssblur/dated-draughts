package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class BetaGuiMixin {
  @Inject(
    method = "renderFood(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;II)V",
    at = @At("HEAD"),
    cancellable = true
  )
  private void dd$renderFood(GuiGraphics guiGraphics, Player player, int i, int j, CallbackInfo ci) {
    if(EffectValidator.applyOldHungerMechanics(player))
      ci.cancel();
  }

  @Redirect(
    method="renderCrosshair",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V")
  )
  private void dd$renderCrosshair(GuiGraphics instance, ResourceLocation arg, int i, int j, int k, int l) {
    if(Minecraft.getInstance().player != null && EffectValidator.applyOldCombatMechanics(Minecraft.getInstance().player)) {
      if(arg.getPath().startsWith("hud/crosshair_attack_indicator"))
        return;
    }
    instance.blitSprite(arg, i, j, k, l);
  }
}
