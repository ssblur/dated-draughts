package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class BetaPlayerMixin {
  @Inject(method = "isSwimming", cancellable = true, at = @At("HEAD"))
  private void dd$isSwimming(CallbackInfoReturnable<Boolean> cir) {
    if(EffectValidator.applyOldSwimmingMechanics((Player) (Object) this))
      cir.setReturnValue(false);
  }
}
