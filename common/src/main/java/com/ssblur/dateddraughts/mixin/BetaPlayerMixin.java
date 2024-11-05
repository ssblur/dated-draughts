package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.DatedDraughts;
import com.ssblur.dateddraughts.item.DatedDraughtsItems;
import com.ssblur.dateddraughts.sounds.DatedDraughtsSounds;
import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class BetaPlayerMixin extends LivingEntity {
  protected BetaPlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
    super(entityType, level);
  }

  @Inject(method = "isSwimming", cancellable = true, at = @At("HEAD"))
  private void dd$isSwimming(CallbackInfoReturnable<Boolean> cir) {
    if(EffectValidator.applyOldSwimmingMechanics((Player) (Object) this))
      cir.setReturnValue(false);
  }

  @Inject(method = "resetAttackStrengthTicker", cancellable = true, at = @At("HEAD"))
  private void dd$resetAttackStrengthTicker(CallbackInfo ci) {
    Player self = (Player) (Object) this;
    if(EffectValidator.applyOldCombatMechanics(self))
      ci.cancel();
  }

  @Inject(method = "getHurtSound", cancellable = true, at = @At("HEAD"))
  private void dd$getHurtSound(DamageSource damageSource, CallbackInfoReturnable<SoundEvent> cir) {
    Player self = (Player) (Object) this;
    if(EffectValidator.applyOldCombatMechanics(self))
      cir.setReturnValue(DatedDraughtsSounds.OUGH.value());
  }

  @Inject(method = "getItemBySlot", cancellable = true, at = @At("RETURN"))
  private void dd$getItemBySlot(EquipmentSlot equipmentSlot, CallbackInfoReturnable<ItemStack> cir) {
    Player self = (Player) (Object) this;
    if(EffectValidator.applyOldCombatMechanics(self))
      if(self.level().isClientSide && equipmentSlot == EquipmentSlot.OFFHAND) {
        if (!cir.getReturnValue().is(DatedDraughtsItems.ALWAYS_OFFHAND) &&
          !self.getItemBySlot(EquipmentSlot.MAINHAND).is(DatedDraughtsItems.ALWAYS_OFFHAND))
          cir.setReturnValue(ItemStack.EMPTY);
      }
  }

  @ModifyVariable(
    method = "attack",
    at = @At("STORE"),
    ordinal = 4
  )
  private boolean dd$attack(boolean bl4) {
    Player self = (Player) (Object) this;
    if(EffectValidator.applyOldCombatMechanics(self))
      return false;
    return bl4;
  }

  @ModifyVariable(
    method = "hurt",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z"),
    ordinal = 0,
    argsOnly = true
  )
  private float dd$hurt(float damage, DamageSource source) {
    if (!this.isInvulnerable() || source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
      if (!source.is(DamageTypeTags.BYPASSES_ARMOR) && damage > 0) {
        if (this.isUsingItem() && !this.useItem.isEmpty()) {
          Item item = this.useItem.getItem();
          if(item.getUseAnimation(this.useItem) == DatedDraughts.BLOCK) {
            damage = (1F + damage) * 0.5F;
          }
        }
      }
    }
    return damage;
  }
}
