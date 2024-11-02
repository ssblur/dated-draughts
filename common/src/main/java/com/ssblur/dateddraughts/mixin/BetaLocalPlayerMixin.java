package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class BetaLocalPlayerMixin {
  @Inject(method = "canStartSprinting", cancellable = true, at = @At("HEAD"))
  private void dd$canSprint(CallbackInfoReturnable<Boolean> cir) {
    if(EffectValidator.applyOldHungerMechanics((Player) (Object) this))
      cir.setReturnValue(false);
  }
}
