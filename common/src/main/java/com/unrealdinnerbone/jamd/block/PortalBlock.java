package com.unrealdinnerbone.jamd.block;

import com.unrealdinnerbone.jamd.Jamd;
import com.unrealdinnerbone.jamd.TelerportUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PortalBlock extends Block implements EntityBlock {


    public PortalBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE));
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onPlace(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            try {
                TelerportUtils.teleport(this, player, getWorldFromTileEntity(level, blockPos).orElseThrow(() -> new RuntimeException("Invalid world ID set")), blockPos);
            } catch (Exception e) {
                player.displayClientMessage(new TextComponent(e.getMessage()), false);
            }
            return InteractionResult.PASS;
        }else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if(!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof PortalTileEntity) {
                PortalTileEntity portalTileEntity = (PortalTileEntity) blockEntity;
                if(portalTileEntity.getWorldId() != null) {
                    if(!level.dimension().equals(Level.OVERWORLD)) {
                        portalTileEntity.setWorldId(Level.OVERWORLD.location());
                    }else {
                        portalTileEntity.setWorldId(Jamd.DIM_ID);
                    }
                    portalTileEntity.setChanged();
                }
            }
        }
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
    }


    public static void placeBlock(Block block, Level level, BlockPos blockPos, ResourceKey<Level> portalTo) {
        level.setBlockAndUpdate(blockPos, block.defaultBlockState());
        BlockEntity tileEntity = level.getBlockEntity(blockPos);
        if(tileEntity instanceof PortalTileEntity) {
            ((PortalTileEntity) tileEntity).setWorldId(portalTo.location());
            tileEntity.setChanged();
        }
    }

    public static Optional<Level> getWorldFromTileEntity(Level level, BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof PortalTileEntity) {
            PortalTileEntity portalTileEntity = (PortalTileEntity) blockEntity;
            if (portalTileEntity.getWorldId() != null) {
                return Optional.ofNullable(level.getServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, portalTileEntity.getWorldId())));
            }
        }
        return Optional.empty();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new PortalTileEntity();
    }
}
