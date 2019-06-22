package com.devdarkco.rnd.tabs;

import com.devdarkco.rnd.init.RndItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RndItemsTab extends CreativeTabs {

    public RndItemsTab() {
        super("rndItemsTab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(RndItems.ADVANCED_BONE_MEAL);
    }
}
