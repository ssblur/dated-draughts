package com.ssblur.dateddraughts.util;

import com.ssblur.dateddraughts.DatedDraughts;
import com.ssblur.dateddraughts.DatedDraughtsGameRules;
import com.ssblur.dateddraughts.effects.DatedDraughtEffect;
import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import com.ssblur.dateddraughts.events.network.SyncedRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class EffectValidator {
  public static boolean applyOldHungerMechanics(Player player) {
    if(player.hasEffect(DatedDraughtsEffects.get(DatedDraughtsEffects.BETA_FOOD)))
      return true;
    if(player.level() instanceof ServerLevel level) {
      return DatedDraughtsGameRules.getValue(level, DatedDraughtsGameRules.BETA_FOOD);
    } else {
      return SyncedRules.getValue(DatedDraughtsGameRules.BETA_FOOD);
    }
  }
}
