package com.ssblur.dateddraughts.mixin;

import com.ssblur.dateddraughts.item.DatedDraughtsItemModels;
import com.ssblur.dateddraughts.util.EffectValidator;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public class BetaItemRendererMixin {
  @Inject(method = "getModel", cancellable = true, at = @At("HEAD"))
  private void dd$getModel(ItemStack itemStack, Level level, LivingEntity livingEntity, int i, CallbackInfoReturnable<BakedModel> cir) {
    ItemRenderer self = (ItemRenderer) (Object) this;
    if(livingEntity instanceof Player player && EffectValidator.applyOldHungerMechanics(player)) {
      var item = itemStack.getItem();
      if (item == Items.COOKED_PORKCHOP)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.COOKED_PORKCHOP));
      else if (item == Items.PORKCHOP)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.PORKCHOP));
      else if (item == Items.COD)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.FISH));
      else if (item == Items.SALMON)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.FISH));
      else if (item == Items.COOKED_COD)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.COOKED_FISH));
      else if (item == Items.COOKED_SALMON)
        cir.setReturnValue(self.getItemModelShaper().getModelManager().getModel(DatedDraughtsItemModels.COOKED_FISH));
    }
  }
}
