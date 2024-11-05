package com.ssblur.dateddraughts.util;

import com.ssblur.dateddraughts.DatedDraughtsGameRules;
import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import com.ssblur.dateddraughts.events.network.SyncedRules;
import com.ssblur.dateddraughts.item.DatedDraughtsItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class EffectValidator {
  public static boolean applyOldHungerMechanics(Player player) {
    if(player.getItemBySlot(EquipmentSlot.MAINHAND).is(DatedDraughtsItems.BETA_FOOD))
      return true;
    if(player.getItemBySlot(EquipmentSlot.OFFHAND).is(DatedDraughtsItems.BETA_FOOD))
      return true;
    if(player.hasEffect(DatedDraughtsEffects.get(DatedDraughtsEffects.BETA_FOOD)))
      return true;
    if(player.level() instanceof ServerLevel level) {
      return DatedDraughtsGameRules.getValue(level, DatedDraughtsGameRules.BETA_FOOD);
    } else {
      return SyncedRules.getValue(DatedDraughtsGameRules.BETA_FOOD);
    }
  }

  public static boolean applyOldSwimmingMechanics(Player player) {
    if(player.hasEffect(DatedDraughtsEffects.get(DatedDraughtsEffects.OLD_WATER)))
      return true;
    if(player.level() instanceof ServerLevel level) {
      return DatedDraughtsGameRules.getValue(level, DatedDraughtsGameRules.OLD_WATER);
    } else {
      return SyncedRules.getValue(DatedDraughtsGameRules.OLD_WATER);
    }
  }

  public static boolean applyOldCombatMechanics(Player player) {
    if(player.hasEffect(DatedDraughtsEffects.get(DatedDraughtsEffects.OLD_COMBAT)))
      return true;
    if(player.level() instanceof ServerLevel level) {
      return DatedDraughtsGameRules.getValue(level, DatedDraughtsGameRules.OLD_COMBAT);
    } else {
      return SyncedRules.getValue(DatedDraughtsGameRules.OLD_COMBAT);
    }
  }
}
