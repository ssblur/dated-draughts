package com.ssblur.dateddraughts.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Environment(EnvType.CLIENT)
@Mixin(HumanoidModel.ArmPose.class)
@Unique
public class BetaArmPoseMixin {
  @Shadow
  @Final
  @Mutable
  private static HumanoidModel.ArmPose[] $VALUES;
  @Unique
  @SuppressWarnings("unused")
  private static final HumanoidModel.ArmPose dd$BLOCK = dd$addArmPose("dated_draughts:block", true);

  @Invoker("<init>")
  public static HumanoidModel.ArmPose dd$invokeInit(String name, int id, boolean twoHanded) {
    throw new AssertionError();
  }

  @Unique
  private static HumanoidModel.ArmPose dd$addArmPose(String poseName, boolean twoHanded) {
    assert BetaArmPoseMixin.$VALUES != null;
    ArrayList<HumanoidModel.ArmPose> poses = new ArrayList<>(Arrays.asList(BetaArmPoseMixin.$VALUES));
    HumanoidModel.ArmPose pose = dd$invokeInit(poseName, poses.getLast().ordinal() + 1, twoHanded);
    poses.add(pose);
    BetaArmPoseMixin.$VALUES = poses.toArray(new HumanoidModel.ArmPose[0]);
    return pose;
  }
}
