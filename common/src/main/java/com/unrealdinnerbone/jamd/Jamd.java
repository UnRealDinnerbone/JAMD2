package com.unrealdinnerbone.jamd;

import com.unrealdinnerbone.jamd.block.PortalBlock;
import com.unrealdinnerbone.jamd.block.PortalTileEntity;
import me.shedaniel.architectury.registry.BiomeModifications;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Supplier;

public class Jamd {
    public static final String MOD_ID = "jamd";
    public static final ResourceLocation DIM_ID = new ResourceLocation(MOD_ID, "mining");

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);


    public static final RegistrySupplier<Block> PORTAL_BLOCK = BLOCKS.register("mine_portal_block", PortalBlock::new);
    public static final RegistrySupplier<Item> PORTAL_ITEM = ITEMS.register("mine_portal_block", () -> new BlockItem(PORTAL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistrySupplier<BlockEntityType<PortalTileEntity>> PORTAL_BLOCK_ENTITY = BLOCK_ENTITY.register("portal", () -> BlockEntityType.Builder.of(PortalTileEntity::new, PORTAL_BLOCK.get()).build(null));

    public static void init() {
        BLOCKS.register();
        BLOCK_ENTITY.register();
        ITEMS.register();
        JamdConfig.init();
        BiomeModifications.postProcessProperties(biomeContext -> biomeContext.getKey().equals(DIM_ID), (biomeContext, mutable) -> {
            if (JamdConfig.CONFIG.get().isFlowers()) {
                remove(mutable.getGenerationProperties().getFeatures(), GenerationStep.Decoration.VEGETAL_DECORATION.ordinal());
            }
            if (JamdConfig.CONFIG.get().isEntities()) {
                mutable.getSpawnProperties().removeSpawns((mobCategory, spawnerData) -> true);
            }
            if (JamdConfig.CONFIG.get().isLakes()) {
                remove(mutable.getGenerationProperties().getFeatures(), GenerationStep.Decoration.LAKES.ordinal());
            }
            if (JamdConfig.CONFIG.get().isStructures()) {
                remove(mutable.getGenerationProperties().getFeatures(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES.ordinal());
            }
        });
    }

    public static void remove(List<List<Supplier<ConfiguredFeature<?, ?>>>> list, int spot) {
        if(list.size() > spot) {
            list.get(spot).clear();
        }
    }
}
