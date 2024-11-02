package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class BetaFoodDataMixin {
  @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
  private void dd$tick(Player player, CallbackInfo ci) {
    if(EffectValidator.applyOldHungerMechanics(player)) {
      ci.cancel();
    }
  }
}
