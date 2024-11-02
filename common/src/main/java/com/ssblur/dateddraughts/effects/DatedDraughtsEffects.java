package com.ssblur.dateddraughts.effects;

import com.ssblur.dateddraughts.DatedDraughts;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.Nullable;

public class DatedDraughtsEffects {
  public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.MOB_EFFECT);
  public static final RegistrySupplier<MobEffect> BETA_FOOD = EFFECTS.register("beta_food",
    () -> new DatedDraughtEffect(MobEffectCategory.NEUTRAL, 0xff44cc00));
  public static final RegistrySupplier<MobEffect> OLD_WATER = EFFECTS.register("old_water",
    () -> new DatedDraughtEffect(MobEffectCategory.NEUTRAL, 0xff4444cc));


  public static void init() {
    EFFECTS.register();
  }

  @Nullable
  public static Holder<MobEffect> get(RegistrySupplier<MobEffect> effect) {
    return EFFECTS.getRegistrar().getHolder(effect.getId());
  }
}
