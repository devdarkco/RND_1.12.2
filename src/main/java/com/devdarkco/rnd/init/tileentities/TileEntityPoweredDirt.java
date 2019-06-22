package com.devdarkco.rnd.init.tileentities;

import com.devdarkco.rnd.util.ChatUtil;
import com.devdarkco.rnd.util.ParticleCreator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityPoweredDirt extends TileEntity implements ITickable {

    private boolean powered;
    private int currentSpeed = 1;
    private int maxSpeed = 7;

    private EntityPlayer player;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("powered", powered);
        compound.setInteger("current_speed", currentSpeed);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        powered = compound.getBoolean("powered");
        currentSpeed = compound.getInteger("current_speed");
        super.readFromNBT(compound);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void update() {
        isBeingPowered();
        if (isPowered()) {
            tickCrop(this.pos.up());
        }
    }

    /**
     * Update the crop depending on the currentSpeed;
     */
    private void tickCrop(BlockPos pos) {
        IBlockState cropState = this.world.getBlockState(pos);
        Block crop = cropState.getBlock();
        if (!world.isRemote) {
            if (crop instanceof BlockCrops) {
                if (crop.getTickRandomly()) {
                    if (this.world.getBlockState(pos) == cropState) {
                        for (int i = 0; i < getCurrentSpeed(); i++) {
                            crop.updateTick(this.world, pos, cropState, this.world.rand);
                        }
                    }
                }
            }
        }
    }

    public void setPowered(boolean state) {
        powered = state;
        markDirty();
    }

    public void isBeingPowered() {
        if (this.world.isBlockPowered(this.pos)) {
            this.setPowered(true);
            boolean particles = this.world.rand.nextBoolean();
            if (particles) {
                ParticleCreator.spawnParticle(EnumParticleTypes.REDSTONE, this.world, this.pos, 5, this.world.rand);
            }
        } else {
            this.setPowered(false);
        }
    }

    public boolean isPowered() {
        return powered;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    //Increase the speed is the currentSpeed is under the maxSpeed;
    public void increaseSpeed(int amount) {
        if(isUpgradable(amount)){
            currentSpeed = currentSpeed+amount;
        }
    }

    //Check if the newSpeed is under or equal to the maxSpeed than return a boolean
    public boolean isUpgradable(int amount){
        return (currentSpeed + amount) <= maxSpeed;
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }
}
