package com.unrealdinnerbone.jamd.block;

import com.unrealdinnerbone.jamd.Jamd;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PortalTileEntity extends BlockEntity {

    private ResourceLocation worldId;

    public PortalTileEntity() {
        super(Jamd.PORTAL_BLOCK_ENTITY.get());
    }

    public void setWorldId(ResourceLocation worldId) {
        this.worldId = worldId;
    }

    public ResourceLocation getWorldId() {
        return worldId == null ? new ResourceLocation("minecraft", "empty") : worldId;
    }


    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putString("world_id", getTheWorldId());
        return super.save(compoundTag);
    }

    @Override
    public void load(BlockState blockState, CompoundTag compoundTag) {
        if(compoundTag.contains("world_id")) {
            worldId = ResourceLocation.tryParse(compoundTag.getString("world_id"));
        }
        super.load(blockState, compoundTag);
    }


    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putString("world_id", getTheWorldId());
        return new ClientboundBlockEntityDataPacket(getBlockPos(), 0, compoundNBT);
    }

//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//        CompoundNBT nbt = pkt.getNbtCompound();
//        if(nbt.contains("world_id")) {
//            worldId = ResourceLocation.tryCreate(nbt.getString("world_id"));
//        }
//
//    }

    public String getTheWorldId() {
        return worldId == null ? "" : worldId.toString();
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putString("world_id", getTheWorldId());
        return compoundNBT;
    }

}
