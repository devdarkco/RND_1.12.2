package com.devdarkco.rnd.init;

import com.devdarkco.rnd.init.blocks.BlockPoweredDirt;
import com.devdarkco.rnd.init.blocks.RndBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class RndBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block RND_GENERATOR = new RndBlock(Material.ROCK, "generator");
    public static final Block POWERED_DIRT = new BlockPoweredDirt(Material.GROUND, "powered_dirt");
}
