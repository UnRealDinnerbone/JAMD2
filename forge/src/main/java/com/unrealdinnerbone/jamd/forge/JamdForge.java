package com.unrealdinnerbone.jamd.forge;

import me.shedaniel.architectury.platform.forge.EventBuses;
import com.unrealdinnerbone.jamd.Jamd;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Jamd.MOD_ID)
public class JamdForge {

    public JamdForge() {
        EventBuses.registerModEventBus(Jamd.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Jamd.init();
    }
}
