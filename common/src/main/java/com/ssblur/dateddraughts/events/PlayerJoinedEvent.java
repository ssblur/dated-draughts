package com.ssblur.dateddraughts.events;

import com.ssblur.dateddraughts.DatedDraughtsGameRules;
import com.ssblur.dateddraughts.events.network.SyncedRules;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;

public class PlayerJoinedEvent implements PlayerEvent.PlayerJoin {
  @Override
  public void join(ServerPlayer player) {
    for(var rule: DatedDraughtsGameRules.syncedRules) {
      SyncedRules.send(player, rule.getId(), player.server.overworld().getGameRules().getBoolean(rule));
    }
  }
}
