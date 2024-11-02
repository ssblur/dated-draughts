package com.ssblur.dateddraughts;

import com.ssblur.dateddraughts.events.network.SyncedRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class DatedDraughtsGameRules {
  public static List<GameRules.Key<GameRules.BooleanValue>> syncedRules = new ArrayList<>();

  public static GameRules.Key<GameRules.BooleanValue> BETA_FOOD;
  public static GameRules.Key<GameRules.BooleanValue> ENABLE_ALL;

  public static void init() {
    BETA_FOOD = register("dated_draughts:beta_food", true);
    ENABLE_ALL = register("dated_draughts:enable_all", true);
  }

  public static boolean getValue(@Nullable Level level, GameRules.Key<GameRules.BooleanValue> key) {
    if(level == null) return false;
    if(level.isClientSide)
      return SyncedRules.getValue(ENABLE_ALL) || SyncedRules.getValue(key);
    return level.getGameRules().getBoolean(ENABLE_ALL) || level.getGameRules().getBoolean(key);
  }

  private static final Map<String, GameRules.Key<GameRules.BooleanValue>> keyMap = new HashMap<>();
  public static boolean getValue(@Nullable Level level, String key) {
    if(level == null) return false;
    if(keyMap.containsKey(key))
      return getValue(level, keyMap.get(key));
    if(level.isClientSide && SyncedRules.getValue(ENABLE_ALL))
      return true;
    if(level.getGameRules().getBoolean(ENABLE_ALL))
      return true;

    var visitor = new GameRuleVisitor(key);
    GameRules.visitGameRuleTypes(visitor);
    if(visitor.key != null) {
      keyMap.put(key, visitor.key);
      return getValue(level, visitor.key);
    }
    throw new RuntimeException("Could not find gamerule \"%s\"".formatted(key));
  }

  public static class GameRuleVisitor implements GameRules.GameRuleTypeVisitor {
    String id;
    GameRules.Key<GameRules.BooleanValue> key;
    public GameRuleVisitor(String key) {
      this.id = key;
    }

    @Override
    public void visitBoolean(GameRules.Key<GameRules.BooleanValue> key, GameRules.Type<GameRules.BooleanValue> type) {
      if(key.toString().equals(this.id))
        this.key = key;
    }
  }

  private static GameRules.Key<GameRules.BooleanValue> register(String key) {
    return register(key, false);
  }

  private static GameRules.Key<GameRules.BooleanValue> register(String key, boolean synced) {
    if(synced) {
      var rule = GameRules.register(key, GameRules.Category.MISC, GameRules.BooleanValue.create(false, syncHelper(key)));
      syncedRules.add(rule);
      return rule;
    }
    return GameRules.register(key, GameRules.Category.MISC, GameRules.BooleanValue.create(false));
  }

  private static BiConsumer<MinecraftServer, GameRules.BooleanValue> syncHelper(String key) {
    return (server, value) -> SyncedRules.send(server.getPlayerList().getPlayers(), key, value.get());
  }
}
