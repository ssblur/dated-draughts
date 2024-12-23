package com.ssblur.dateddraughts.item;

import com.ssblur.dateddraughts.DatedDraughts;
import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

@SuppressWarnings(value = {"unused", "UnstableApiUsage"})
public class DatedDraughtsItems {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.ITEM);
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.CREATIVE_MODE_TAB);

  public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register(
    DatedDraughts.location("dated_draughts_tab"),
    () ->
      CreativeTabRegistry.create(
        builder -> {
          builder.title(Component.translatable("itemGroup.dated_draughts.tab"));
          builder.icon(() -> new ItemStack(DatedDraughtsItems.COOKED_PORKCHOP.get()));
          builder.displayItems((displayParameters, output) -> {
            output.accept(PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.OLD_COMBAT_POTION));
            output.accept(PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.OLD_WATER_POTION));
            output.accept(PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.BETA_FOOD_POTION));
            output.accept(PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.DATED_DRAUGHT_POTION));

            output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, DatedDraughtsEffects.OLD_COMBAT_POTION));
            output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, DatedDraughtsEffects.OLD_WATER_POTION));
            output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, DatedDraughtsEffects.BETA_FOOD_POTION));
            output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, DatedDraughtsEffects.DATED_DRAUGHT_POTION));

            output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, DatedDraughtsEffects.OLD_COMBAT_POTION));
            output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, DatedDraughtsEffects.OLD_WATER_POTION));
            output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, DatedDraughtsEffects.BETA_FOOD_POTION));
            output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, DatedDraughtsEffects.DATED_DRAUGHT_POTION));

            output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, DatedDraughtsEffects.OLD_COMBAT_POTION));
            output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, DatedDraughtsEffects.OLD_WATER_POTION));
            output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, DatedDraughtsEffects.BETA_FOOD_POTION));
            output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, DatedDraughtsEffects.DATED_DRAUGHT_POTION));
          });
        }
      )
  );

  public static final RegistrySupplier<Item> COOKED_PORKCHOP = ITEMS.register("cooked_porkchop", () ->
    new Item(new Item.Properties().food(Foods.COOKED_PORKCHOP).arch$tab(TAB)));
  public static final RegistrySupplier<Item> PORKCHOP = ITEMS.register("porkchop", () ->
    new Item(new Item.Properties().food(Foods.PORKCHOP).arch$tab(TAB)));
  public static final RegistrySupplier<Item> FISH = ITEMS.register("fish", () ->
    new Item(new Item.Properties().food(Foods.COD).arch$tab(TAB)));
  public static final RegistrySupplier<Item> COOKED_FISH = ITEMS.register("cooked_fish", () ->
    new Item(new Item.Properties().food(Foods.COOKED_COD).arch$tab(TAB)));

  public static final TagKey<Item> ALWAYS_OFFHAND = TagKey.create(Registries.ITEM, DatedDraughts.location("always_offhand"));
  public static final TagKey<Item> BETA_FOOD = TagKey.create(Registries.ITEM, DatedDraughts.location("beta_food"));

  public static void init() {
    ITEMS.register();
    TABS.register();
  }
}
