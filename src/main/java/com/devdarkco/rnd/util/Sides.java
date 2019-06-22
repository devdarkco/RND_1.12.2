package com.devdarkco.rnd.util;

import net.minecraft.util.math.BlockPos;

public class Sides {

    public static BlockPos down(float x, float y, float z){
        return new BlockPos(x, y-1, z);
    }

    public static BlockPos up(float x, float y, float z){
        return new BlockPos(x, y+1, z);
    }
}
