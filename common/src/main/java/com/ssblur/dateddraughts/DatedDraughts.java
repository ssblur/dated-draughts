package com.ssblur.dateddraughts;

import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import com.ssblur.dateddraughts.events.DatedDraughtsEvents;
import com.ssblur.dateddraughts.item.DatedDraughtsItems;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public final class DatedDraughts {
  public static final String MOD_ID = "dated_draughts";

  public static void init() {
    DatedDraughtsGameRules.init();
    DatedDraughtsEvents.init();
    DatedDraughtsEffects.init();
    DatedDraughtsItems.init();
  }

  public static ResourceLocation location(String path) {
    return Objects.requireNonNull(ResourceLocation.tryBuild(MOD_ID, path));
  }
}
