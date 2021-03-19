package com.unrealdinnerbone.jamd;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Jamd.MOD_ID)
public class JamdConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static ConfigHolder<JamdConfig> CONFIG = null;

    @Comment("Stop mods from adding custom flowers")
    public boolean FLOWERS = true;
    @Comment("Stop mods from adding surface structures")
    public boolean STRUCTURES = true;
    @Comment("Stop mods from adding entities")
    public boolean ENTITIES = true;
    @Comment("Stop mods from adding lakes")
    public boolean LAKES = true;

    public boolean isFlowers() {
        return FLOWERS;
    }

    public boolean isEntities() {
        return ENTITIES;
    }

    public boolean isStructures() {
        return STRUCTURES;
    }

    public boolean isLakes() {
        return LAKES;
    }

    public static void init() {
        CONFIG = AutoConfig.register(JamdConfig.class, Toml4jConfigSerializer::new);
    }
}
