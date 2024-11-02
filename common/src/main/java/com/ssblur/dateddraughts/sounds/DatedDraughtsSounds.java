package com.ssblur.dateddraughts.sounds;

import com.ssblur.dateddraughts.DatedDraughts;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class DatedDraughtsSounds {
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.SOUND_EVENT);
  public static final RegistrySupplier<SoundEvent> OUGH = SOUNDS.register("ough", () ->
    SoundEvent.createVariableRangeEvent(DatedDraughts.location("ough")));
  public static void init() {
    SOUNDS.register();
  }
}
