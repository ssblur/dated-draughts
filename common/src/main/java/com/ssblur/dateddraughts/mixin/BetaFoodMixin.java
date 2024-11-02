package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class BetaFoodMixin {
  @Inject(
    method = "Lnet/minecraft/world/item/Item;use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;",
    at = @At("HEAD"),
    cancellable = true
  )
  private void dd$use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
    if(EffectValidator.applyOldHungerMechanics(player)) {
      ItemStack itemStack = player.getItemInHand(interactionHand);
      FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
      if (foodProperties != null) {
        if (player.getHealth() < player.getMaxHealth() || foodProperties.canAlwaysEat()) {
          player.heal(foodProperties.nutrition());
          player.eat(level, itemStack, foodProperties);
          cir.setReturnValue(InteractionResultHolder.consume(itemStack));
        } else {
          cir.setReturnValue(InteractionResultHolder.pass(itemStack));
        }
      }
    }
  }
}
