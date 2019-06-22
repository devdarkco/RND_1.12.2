package com.devdarkco.rnd.init.blocks;

import com.devdarkco.rnd.Rnd;
import com.devdarkco.rnd.init.RndBlocks;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.interfaces.IHasModel;
import com.devdarkco.rnd.tabs.RndBlocksTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class RndBlock extends Block implements IHasModel {

    public RndBlock(Material materialIn, String name) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Rnd.rndBlocksTab);

        RndBlocks.BLOCKS.add(this);
        RndItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Rnd.common.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public void registerModels(int metadata) {

    }
}
