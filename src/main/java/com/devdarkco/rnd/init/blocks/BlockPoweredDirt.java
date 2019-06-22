package com.devdarkco.rnd.init.blocks;

import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.init.tileentities.RndBlockTileEntity;
import com.devdarkco.rnd.init.tileentities.TileEntityPoweredDirt;
import com.devdarkco.rnd.interfaces.IUpgradable;
import com.devdarkco.rnd.util.ChatUtil;
import com.devdarkco.rnd.util.Debug;
import com.devdarkco.rnd.util.EnumUtil;
import com.devdarkco.rnd.util.WorldUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockPoweredDirt extends RndBlockTileEntity<TileEntityPoweredDirt> implements IUpgradable {

    //Copied base AABB from minecraft farmland
    private static final AxisAlignedBB COPIED_FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public BlockPoweredDirt(Material materialIn, String name) {
        super(materialIn, name);
        setTickRandomly(true);
        setHardness(0.5f);
        setHarvestLevel("wood", 0);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntityPoweredDirt tileEntityPoweredDirt = getTileEntity(worldIn, pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);
        TileEntityPoweredDirt tileEntityPoweredDirt = getTileEntity(worldIn, pos);
        if (!WorldUtil.clientSide(worldIn)) {
            if (heldItem.getItem() == RndItems.UPGRADE) {
                switch (heldItem.getMetadata()) {
                    case 0: // Basic
                        incrementSpeedIf(tileEntityPoweredDirt, heldItem, playerIn, EnumUtil.UpgradeType.BASIC.getUpgradeValue());
                        break;
                    case 1: // Better
                        incrementSpeedIf(tileEntityPoweredDirt, heldItem, playerIn, EnumUtil.UpgradeType.BETTER.getUpgradeValue());
                        break;
                    case 2: // Power
                        incrementSpeedIf(tileEntityPoweredDirt, heldItem, playerIn, EnumUtil.UpgradeType.POWER.getUpgradeValue());
                        break;
                }
            }
        }
        if(heldItem.getItem() == RndItems.DEBUG_STICK){
            Debug.l("CurrentSpeed: " + tileEntityPoweredDirt.getCurrentSpeed());
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    private void incrementSpeedIf(TileEntityPoweredDirt tileEntityPoweredDirt, ItemStack heldItem, EntityPlayer playerIn, int upgradeValue){
        if(tileEntityPoweredDirt.isUpgradable(upgradeValue)){
            if(!playerIn.isCreative()){
                heldItem.shrink(1);
            }
            tileEntityPoweredDirt.increaseSpeed(upgradeValue);
            ChatUtil.chatMessage(playerIn, "Added " + upgradeValue + " to speed; Current speed: " + tileEntityPoweredDirt.getCurrentSpeed());
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        return true;
    }

    @Override
    public Class<TileEntityPoweredDirt> getTileEntityClass() {
        return TileEntityPoweredDirt.class;
    }

    @Override
    public TileEntityPoweredDirt createTileEntity(World world, IBlockState state) {
        return new TileEntityPoweredDirt();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return COPIED_FARMLAND_AABB;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isUpgradable() {
        return true;
    }
}
