package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.DatedDraughts;
import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public abstract class BetaSwordMixin extends TieredItem {
  public BetaSwordMixin(Tier tier, Properties properties) {
    super(tier, properties);
  }

  @Override
  public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
    if(livingEntity instanceof Player player && EffectValidator.applyOldCombatMechanics(player))
      return 72000;
    return super.getUseDuration(stack, livingEntity);
  }

  @Override
  public UseAnim getUseAnimation(ItemStack itemStack) {
    return DatedDraughts.BLOCK;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
    if(EffectValidator.applyOldCombatMechanics(player)) {
      player.startUsingItem(hand);
      return InteractionResultHolder.success(player.getItemInHand(hand));
    }
    return super.use(world, player, hand);
  }
}
