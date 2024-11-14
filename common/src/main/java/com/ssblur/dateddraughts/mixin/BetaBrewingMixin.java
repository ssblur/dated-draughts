package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.effects.DatedDraughtsEffects;
import com.ssblur.dateddraughts.item.DatedDraughtsItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionBrewing.class)
public class BetaBrewingMixin {
  @Inject(method = "isIngredient", at = @At("HEAD"), cancellable = true)
  private void dd$isIngredient(ItemStack item, CallbackInfoReturnable<Boolean> info) {
    if(
      item.getItem() == DatedDraughtsItems.COOKED_PORKCHOP.get() ||
      item.getItem() == DatedDraughtsItems.COOKED_FISH.get() ||
      item.getItem() == Items.STONE_SWORD
    ) info.setReturnValue(true);
  }

  @Inject(method = "mix", at = @At("RETURN"), cancellable = true)
  private void dd$mix(ItemStack ingredient, ItemStack potion, CallbackInfoReturnable<ItemStack> info) {
    var potionContents = potion.get(DataComponents.POTION_CONTENTS);
    if(ingredient.getItem() == DatedDraughtsItems.COOKED_PORKCHOP.get() && potionContents != null && potionContents.is(Potions.THICK)) {
      var out = PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.getPotion(DatedDraughtsEffects.BETA_FOOD_POTION));
      info.setReturnValue(out);
    } else if(ingredient.getItem() == DatedDraughtsItems.COOKED_FISH.get() && potionContents != null && potionContents.is(Potions.THICK)) {
      var out = PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.getPotion(DatedDraughtsEffects.OLD_WATER_POTION));
      info.setReturnValue(out);
    } else if(ingredient.getItem() == Items.STONE_SWORD && potionContents != null && potionContents.is(Potions.THICK)) {
      var out = PotionContents.createItemStack(Items.POTION, DatedDraughtsEffects.getPotion(DatedDraughtsEffects.OLD_COMBAT_POTION));
      info.setReturnValue(out);
    }  else if(ingredient.is(Items.GUNPOWDER) && potion.is(Items.POTION) && ItemStack.isSameItemSameComponents(info.getReturnValue(), potion)) {
      var out = potion.transmuteCopy(Items.SPLASH_POTION);
      info.setReturnValue(out);
    } else if(ingredient.is(Items.DRAGON_BREATH) && potion.is(Items.POTION) && ItemStack.isSameItemSameComponents(info.getReturnValue(), potion)) {
      var out = potion.transmuteCopy(Items.LINGERING_POTION);
      info.setReturnValue(out);
    }
  }

  @Inject(method = "hasMix", at = @At("HEAD"), cancellable = true)
  private void dd$hasMix(ItemStack potion, ItemStack ingredient, CallbackInfoReturnable<Boolean> info) {
    var potionContents = potion.get(DataComponents.POTION_CONTENTS);
    if(
      (
        ingredient.getItem() == DatedDraughtsItems.COOKED_PORKCHOP.get() ||
        ingredient.getItem() == DatedDraughtsItems.COOKED_FISH.get() ||
        ingredient.getItem() == Items.STONE_SWORD
      ) && potionContents != null && potionContents.is(Potions.THICK))
      info.setReturnValue(true);
    else if(ingredient.is(Items.DRAGON_BREATH) && potionContents != null && potion.is(Items.POTION))
      info.setReturnValue(true);
    else if(ingredient.is(Items.GUNPOWDER) && potionContents != null && potion.is(Items.POTION))
      info.setReturnValue(true);
  }
}
