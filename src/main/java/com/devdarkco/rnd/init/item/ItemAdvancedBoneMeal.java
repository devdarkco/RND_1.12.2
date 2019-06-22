package com.devdarkco.rnd.init.item;

import com.devdarkco.rnd.Rnd;
import com.devdarkco.rnd.config.RndConfigs;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.util.ParticleCreator;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAdvancedBoneMeal extends RndItem {

    public ItemAdvancedBoneMeal(String name) {
        super(name);
        setMaxStackSize(64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block growable = worldIn.getBlockState(pos).getBlock();

        if(!worldIn.isRemote){
            doBoneMealEffectOnGround(player.getHeldItem(hand), worldIn, pos);

            return EnumActionResult.SUCCESS;

        }

        if (growable instanceof BlockCrops) {
            BlockCrops blockCrops = (BlockCrops) growable;
            if (blockCrops == Blocks.BEETROOTS) {
                if (worldIn.getBlockState(pos) != blockCrops.getDefaultState().withProperty(BlockBeetroot.BEETROOT_AGE, 3)) {
                    IBlockState newState = blockCrops.getDefaultState().withProperty(BlockBeetroot.BEETROOT_AGE, 3);
                    worldIn.setBlockState(pos, newState, 2);
                    showGrowParticles(worldIn, pos);
                    if (!player.isCreative()) {
                        player.getHeldItem(hand).shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                }
            } else {
                if (worldIn.getBlockState(pos) != blockCrops.getDefaultState().withProperty(BlockCrops.AGE, 7)) {
                    IBlockState newState = blockCrops.getDefaultState().withProperty(BlockCrops.AGE, 7);
                    worldIn.setBlockState(pos, newState, 2);
                    showGrowParticles(worldIn, pos);
                    if (!player.isCreative()) {
                        player.getHeldItem(hand).shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                }
            }
        }

        if (growable instanceof BlockStem) {
            BlockStem stem = (BlockStem) growable;
            if (worldIn.getBlockState(pos) != stem.getDefaultState().withProperty(BlockStem.AGE, 7)) {
                IBlockState newState = stem.getDefaultState().withProperty(BlockStem.AGE, 7);
                worldIn.setBlockState(pos, newState, 2);
                showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
                return EnumActionResult.SUCCESS;
            }
        }

        if (growable instanceof BlockReed) {
            BlockReed reed = (BlockReed) growable;
            if (worldIn.getBlockState(pos.down()).getBlock() != Blocks.REEDS || worldIn.getBlockState(pos.up()) == Blocks.AIR) {
                for (int i = 0; i < 3; i++) {
                    BlockPos newPos = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
                    worldIn.setBlockState(newPos, reed.getDefaultState(), 2);
                }
                if (worldIn.getBlockState(pos.up()) == Blocks.AIR)
                    showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
            }
            return EnumActionResult.SUCCESS;
        }

        if (growable instanceof BlockNetherWart) {
            BlockNetherWart netherWart = (BlockNetherWart) growable;
            if (worldIn.getBlockState(pos) != netherWart.getDefaultState().withProperty(BlockNetherWart.AGE, 3)) {
                IBlockState newState = netherWart.getDefaultState().withProperty(BlockNetherWart.AGE, 3);
                worldIn.setBlockState(pos, newState, 2);
                showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    private void showGrowParticles(World world, BlockPos pos) {
        ParticleCreator.spawnParticle(EnumParticleTypes.SPELL, world, pos, 15, world.rand);
    }

    public static void doBoneMealEffectOnGround(ItemStack itemStack, World worldIn, BlockPos pos){
        int range = worldIn.rand.nextInt(RndConfigs.advancedBoneMealRange);
        for(int x = -range; x < range; x++) {
            for (int z = -range; z < range; z++) {
                ItemDye.applyBonemeal(itemStack, worldIn, pos.add(x, 0, z));
            }
        }
    }
}
