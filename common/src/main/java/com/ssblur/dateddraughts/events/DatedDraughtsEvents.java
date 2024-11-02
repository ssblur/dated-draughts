package com.ssblur.dateddraughts.events;

import com.ssblur.dateddraughts.DatedDraughts;
import com.ssblur.dateddraughts.events.network.DatedDraughtsNetworkInterface;
import com.ssblur.dateddraughts.events.network.SyncedRules;
import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class DatedDraughtsEvents {
  public static final ResourceLocation SYNC_MESSAGE = DatedDraughts.location("sync");

  public static void init() {
    PlayerEvent.PLAYER_JOIN.register(new PlayerJoinedEvent());

    register(new SyncedRules());
  }

  public static <T extends CustomPacketPayload> void register(DatedDraughtsNetworkInterface<T> networkInterface) {
    if(networkInterface.side() == NetworkManager.Side.C2S || Platform.getEnv() == EnvType.CLIENT)
      NetworkManager.registerReceiver(networkInterface.side(), networkInterface.type(), networkInterface.streamCodec(), networkInterface);

    if(networkInterface.side() == NetworkManager.Side.S2C && Platform.getEnv() == EnvType.SERVER)
      NetworkManager.registerS2CPayloadType(networkInterface.type(), networkInterface.streamCodec());
  }
}
