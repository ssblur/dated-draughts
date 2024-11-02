package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.DatedDraughtsGameRules;
import com.ssblur.dateddraughts.events.network.SyncedRules;
import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
}
