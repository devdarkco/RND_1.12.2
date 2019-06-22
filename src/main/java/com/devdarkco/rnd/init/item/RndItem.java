package com.devdarkco.rnd.init.item;

import com.devdarkco.rnd.Rnd;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.interfaces.IHasModel;
import net.minecraft.item.Item;

public class RndItem extends Item implements IHasModel {

    public RndItem(String name) {
        setRegistryName(name);
        setCreativeTab(Rnd.rndItemsTab);

        RndItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Rnd.common.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public void registerModels(int metadata) {
        
    }
}
