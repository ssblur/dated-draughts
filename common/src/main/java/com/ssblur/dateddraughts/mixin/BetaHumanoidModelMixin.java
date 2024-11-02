package com.ssblur.dateddraughts.mixin;
import com.ssblur.dateddraughts.DatedDraughts;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class BetaHumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {

  @Final
  @Shadow
  public ModelPart rightArm;


  @Shadow
  public HumanoidModel.ArmPose rightArmPose;

  @Inject(method = "poseRightArm", at = @At(value = "HEAD"), cancellable = true)
  private void dd$poseRightArm(T entity, CallbackInfo info){
    if (this.rightArmPose.name().equals(DatedDraughts.BLOCK.name())) {
      this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424778F;
      this.rightArm.yRot = -0.5235988F;
      info.cancel();
    }
  }


  @Inject(method = "setupAttackAnimation", at = @At(value = "HEAD"), cancellable = true)
  private void dd$setupAttackAnimation(T entity, float ageInTicks, CallbackInfo info){
    if (this.rightArmPose.name().equals(DatedDraughts.BLOCK.name())) {
      info.cancel();
    }
  }
}