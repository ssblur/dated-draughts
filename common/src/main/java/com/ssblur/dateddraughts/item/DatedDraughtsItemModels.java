package com.ssblur.dateddraughts.item;

import com.ssblur.dateddraughts.DatedDraughts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.model.ModelResourceLocation;

@Environment(EnvType.CLIENT)
public class DatedDraughtsItemModels {
  public static final ModelResourceLocation COOKED_PORKCHOP = ModelResourceLocation.inventory(DatedDraughts.location("cooked_porkchop"));
  public static final ModelResourceLocation PORKCHOP = ModelResourceLocation.inventory(DatedDraughts.location("porkchop"));
  public static final ModelResourceLocation FISH = ModelResourceLocation.inventory(DatedDraughts.location("fish"));
  public static final ModelResourceLocation COOKED_FISH = ModelResourceLocation.inventory(DatedDraughts.location("cooked_fish"));
}
