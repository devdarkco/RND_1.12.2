package com.devdarkco.rnd.tabs;

import com.devdarkco.rnd.init.RndBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RndBlocksTab extends CreativeTabs {

    public RndBlocksTab() {
        super("rndBlocksTab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(RndBlocks.POWERED_DIRT));
    }
}
