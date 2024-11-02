package com.ssblur.dateddraughts.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.ssblur.dateddraughts.DatedDraughts;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class BetaPlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
  @Unique
  private static final HumanoidModel.ArmPose dd$BLOCK_POSE = HumanoidModel.ArmPose.valueOf("dated_draughts:block");
  public BetaPlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> entityModel, float f) {
    super(context, entityModel, f);
  }

  @Inject(
    method = "getArmPose",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;", shift = At.Shift.AFTER),
    cancellable = true)
  private static void dd$getArmPose(
    AbstractClientPlayer abstractClientPlayer, InteractionHand interactionHand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir, @Local ItemStack itemStack
  ) {
    var anim = itemStack.getUseAnimation();
    if (anim == DatedDraughts.BLOCK && anim == itemStack.getUseAnimation()) {
      cir.setReturnValue(dd$BLOCK_POSE);
    }
  }
}
