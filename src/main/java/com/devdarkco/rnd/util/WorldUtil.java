package com.devdarkco.rnd.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {

    public static Block getBlock(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock();
    }

    public static void updateBlockAt(World world, BlockPos pos, Block target){
        world.updateBlockTick(pos, target, 1, 1);
    }

    public static boolean clientSide(World world){
        return world.isRemote;
    }


}
