package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class BetaLivingEntityMixin {
  @Inject(method = "increaseAirSupply", cancellable = true, at = @At("HEAD"))
  private void dd$increaseAirSupply(int i, CallbackInfoReturnable<Integer> cir) {
    LivingEntity self = (LivingEntity) (Object) this;
    if(self instanceof Player player)
      if(EffectValidator.applyOldSwimmingMechanics(player))
        cir.setReturnValue(self.getMaxAirSupply());
  }
}
