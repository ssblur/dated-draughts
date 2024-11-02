package com.ssblur.dateddraughts.mixin;

import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(UseAnim.class)
public class BetaUseAnimMixin {
  @Shadow
  @Final
  @Mutable
  private static UseAnim[] $VALUES;
  @SuppressWarnings("unused")
  @Unique
  private static final UseAnim dd$BLOCK = dd$addAnim("dated_draughts:block");

  @Invoker("<init>")
  public static UseAnim dd$invokeInit(String name, int id) {
    throw new AssertionError();
  }

  @Unique
  private static UseAnim dd$addAnim(String animName) {
    assert BetaUseAnimMixin.$VALUES != null;
    ArrayList<UseAnim> animations = new ArrayList<>(Arrays.asList(BetaUseAnimMixin.$VALUES));
    UseAnim animation = dd$invokeInit(animName, animations.getLast().ordinal() + 1);
    animations.add(animation);
    BetaUseAnimMixin.$VALUES = animations.toArray(new UseAnim[0]);
    return animation;
  }
}
