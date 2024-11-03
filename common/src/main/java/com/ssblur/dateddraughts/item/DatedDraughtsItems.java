package com.ssblur.dateddraughts.item;

import com.ssblur.dateddraughts.DatedDraughts;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

@SuppressWarnings("unused")
public class DatedDraughtsItems {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(DatedDraughts.MOD_ID, Registries.ITEM);
  public static final RegistrySupplier<Item> COOKED_PORKCHOP = ITEMS.register("cooked_porkchop", () ->
    new Item(new Item.Properties().food(Foods.COOKED_PORKCHOP)));
  public static final RegistrySupplier<Item> PORKCHOP = ITEMS.register("porkchop", () ->
    new Item(new Item.Properties().food(Foods.PORKCHOP)));
  public static final RegistrySupplier<Item> FISH = ITEMS.register("fish", () ->
    new Item(new Item.Properties().food(Foods.COD)));
  public static final RegistrySupplier<Item> COOKED_FISH = ITEMS.register("cooked_fish", () ->
    new Item(new Item.Properties().food(Foods.COOKED_COD)));

  public static void init() {
    ITEMS.register();
  }
}
