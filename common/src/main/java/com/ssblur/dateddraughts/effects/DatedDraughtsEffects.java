package com.ssblur.dateddraughts.effects;

import com.ssblur.dateddraughts.DatedDraughts;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DatedDraughtsEffects {
  public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.MOB_EFFECT);
  public static final RegistrySupplier<MobEffect> BETA_FOOD = EFFECTS.register("beta_food",
    () -> new DatedDraughtEffect(MobEffectCategory.NEUTRAL, 0xff44cc00));
  public static final RegistrySupplier<MobEffect> OLD_WATER = EFFECTS.register("old_water",
    () -> new DatedDraughtEffect(MobEffectCategory.NEUTRAL, 0xff7777cc));
  public static final RegistrySupplier<MobEffect> OLD_COMBAT = EFFECTS.register("old_combat",
    () -> new DatedDraughtEffect(MobEffectCategory.NEUTRAL, 0xffff0000));

  public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.POTION);
  public static final RegistrySupplier<Potion> BETA_FOOD_POTION = POTIONS.register("beta_food",
    () -> new Potion("beta_food", new MobEffectInstance(get(BETA_FOOD), 6000)));
  public static final RegistrySupplier<Potion> OLD_WATER_POTION = POTIONS.register("old_water",
    () -> new Potion("old_water", new MobEffectInstance(get(OLD_WATER), 6000)));
  public static final RegistrySupplier<Potion> OLD_COMBAT_POTION = POTIONS.register("old_combat",
    () -> new Potion("old_combat", new MobEffectInstance(get(OLD_COMBAT), 6000)));
  public static final RegistrySupplier<Potion> DATED_DRAUGHT_POTION = POTIONS.register("dated_draught",
    () -> new Potion(
      "dated_draught",
      new MobEffectInstance(get(OLD_COMBAT), 12000),
      new MobEffectInstance(get(OLD_WATER), 12000),
      new MobEffectInstance(get(BETA_FOOD), 12000)
    )
  );

  public static void init() {
    EFFECTS.register();
    POTIONS.register();
  }

  public static Holder<MobEffect> get(@NotNull RegistrySupplier<MobEffect> effect) {
    return EFFECTS.getRegistrar().getHolder(effect.getId());
  }

  public static Holder<Potion> getPotion(@NotNull RegistrySupplier<Potion> effect) {
    return POTIONS.getRegistrar().getHolder(effect.getId());
  }
}
