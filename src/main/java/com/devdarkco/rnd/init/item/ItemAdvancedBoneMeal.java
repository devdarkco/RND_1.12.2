package com.devdarkco.rnd.init.item;

import com.devdarkco.rnd.config.RndConfigs;
import com.devdarkco.rnd.util.*;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;

public class ItemAdvancedBoneMeal extends RndItem {

    public ItemAdvancedBoneMeal(String name) {
        super(name, RarityHelper.Rarity.LEGENDARY);
        setMaxStackSize(64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        //Bone meal effect
        if (!WorldUtil.clientSide(worldIn)) {
            if (WorldUtil.getBlock(worldIn, pos) == Blocks.GRASS) {
                doBoneMealEffectOnGround(player.getHeldItem(hand), worldIn, pos);
                return EnumActionResult.SUCCESS;
            }
        }

        //Insta Grow
        Block crop = worldIn.getBlockState(pos).getBlock();
        if (crop instanceof BlockCrops) {
            fullyGrowCrop(crop, worldIn, pos, player, hand);
            return EnumActionResult.SUCCESS;
        } else if (crop instanceof BlockStem) {
            fullyGrowStem(crop, worldIn, pos, player, hand);
            return EnumActionResult.SUCCESS;
        } else if (crop instanceof BlockReed) {
            fullyGrowReed(crop, worldIn, pos, player, hand);
            return EnumActionResult.SUCCESS;
        } else if (crop instanceof BlockNetherWart) {
            fullyGrowNetherWart(crop, worldIn, pos, player, hand);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    private void fullyGrowCrop(Block crop, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand) {
        BlockCrops blockCrops = (BlockCrops) crop;
        if (blockCrops == Blocks.BEETROOTS) {
            if (worldIn.getBlockState(pos) != blockCrops.getDefaultState().withProperty(BlockBeetroot.BEETROOT_AGE, 3)) {
                IBlockState newState = blockCrops.getDefaultState().withProperty(BlockBeetroot.BEETROOT_AGE, 3);
                worldIn.setBlockState(pos, newState, 2);
                showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
            }
        } else {
            if (worldIn.getBlockState(pos) != blockCrops.getDefaultState().withProperty(BlockCrops.AGE, 7)) {
                IBlockState newState = blockCrops.getDefaultState().withProperty(BlockCrops.AGE, 7);
                worldIn.setBlockState(pos, newState, 2);
                showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
            }
        }
    }

    private void fullyGrowStem(Block crop, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand) {
        BlockStem stem = (BlockStem) crop;
        if (worldIn.getBlockState(pos) != stem.getDefaultState().withProperty(BlockStem.AGE, 7)) {
            IBlockState newState = stem.getDefaultState().withProperty(BlockStem.AGE, 7);
            worldIn.setBlockState(pos, newState, 2);
            showGrowParticles(worldIn, pos);
            if (!player.isCreative()) {
                player.getHeldItem(hand).shrink(1);
            }
        }
    }

    private void fullyGrowReed(Block crop, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand) {
        BlockReed reed = (BlockReed) crop;
        if (worldIn.getBlockState(pos.down()).getBlock() != Blocks.REEDS || worldIn.getBlockState(pos.up()) == Blocks.AIR) {
            if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
                showGrowParticles(worldIn, pos);
                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }
            }
            for (int i = 0; i < 3; i++) {
                BlockPos newPos = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
                worldIn.setBlockState(newPos, reed.getDefaultState(), 2);
            }
        }
    }

    private void fullyGrowNetherWart(Block crop, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand) {
        BlockNetherWart netherWart = (BlockNetherWart) crop;
        if (worldIn.getBlockState(pos) != netherWart.getDefaultState().withProperty(BlockNetherWart.AGE, 3)) {
            IBlockState newState = netherWart.getDefaultState().withProperty(BlockNetherWart.AGE, 3);
            worldIn.setBlockState(pos, newState, 2);
            showGrowParticles(worldIn, pos);
            if (!player.isCreative()) {
                player.getHeldItem(hand).shrink(1);
            }
        }
    }

    private void showGrowParticles(World world, BlockPos pos) {
        ParticleCreator.spawnParticle(EnumParticleTypes.SPELL, world, pos, 15, world.rand);
    }

    public static void doBoneMealEffectOnGround(ItemStack itemStack, World worldIn, BlockPos pos) {
        int range = worldIn.rand.nextInt(RndConfigs.advancedBoneMealRange);
        for (int x = -range; x < range; x++) {
            for (int z = -range; z < range; z++) {
                ItemDye.applyBonemeal(itemStack, worldIn, pos.add(x, 0, z));
            }
        }
    }
}
