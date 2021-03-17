package com.unrealdinnerbone.jamd.fabric;

import com.unrealdinnerbone.jamd.Jamd;
import net.fabricmc.api.ModInitializer;

public class JamdFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Jamd.init();
    }
}
