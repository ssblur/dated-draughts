package com.ssblur.dateddraughts;

import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import com.ssblur.dateddraughts.events.DatedDraughtsEvents;
import com.ssblur.dateddraughts.item.DatedDraughtsItems;
import com.ssblur.dateddraughts.sounds.DatedDraughtsSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.UseAnim;

import java.util.Objects;

public final class DatedDraughts {
  public static final String MOD_ID = "dated_draughts";

  public static final UseAnim BLOCK = UseAnim.valueOf("dated_draughts:block");
  @Environment(EnvType.CLIENT)
  public static final HumanoidModel.ArmPose BLOCK_POSE = HumanoidModel.ArmPose.valueOf("dated_draughts:block");

  public static void init() {
    DatedDraughtsGameRules.init();
    DatedDraughtsEvents.init();
    DatedDraughtsEffects.init();
    DatedDraughtsItems.init();
    DatedDraughtsSounds.init();
  }

  public static ResourceLocation location(String path) {
    return Objects.requireNonNull(ResourceLocation.tryBuild(MOD_ID, path));
  }
}
