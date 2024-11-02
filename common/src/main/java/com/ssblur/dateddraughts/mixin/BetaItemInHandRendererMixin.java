package com.ssblur.dateddraughts.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ssblur.dateddraughts.DatedDraughts;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class BetaItemInHandRendererMixin {
  @Inject(method = "renderArmWithItem",
    at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;")
  )
  private void dd$renderArmWithItem(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack matrices, MultiBufferSource buffer, int combinedLight, CallbackInfo info) {
    if (stack.getUseAnimation() == DatedDraughts.BLOCK) {
      matrices.translate(0.26f, -0.32f, -0.7f);
      matrices.mulPose(Axis.XP.rotationDegrees(340));
      matrices.mulPose(Axis.YP.rotationDegrees(230));
      matrices.mulPose(Axis.ZP.rotationDegrees(340));
    }
  }

}
