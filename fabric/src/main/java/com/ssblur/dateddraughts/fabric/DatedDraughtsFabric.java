package com.ssblur.dateddraughts.fabric;

import net.fabricmc.api.ModInitializer;

import com.ssblur.dateddraughts.DatedDraughts;

public final class DatedDraughtsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        DatedDraughts.init();
    }
}
