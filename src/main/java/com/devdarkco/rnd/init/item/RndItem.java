package com.devdarkco.rnd.init.item;

import com.devdarkco.rnd.Rnd;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.interfaces.IHasModel;
import com.devdarkco.rnd.util.EnumUtil;
import com.devdarkco.rnd.util.RarityHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

public class RndItem extends Item implements IHasModel {

    public RarityHelper.Rarity itemRarity;

    public RndItem(String name, RarityHelper.Rarity itemRarity) {
        setRegistryName(name);
        setCreativeTab(Rnd.rndItemsTab);
        setUnlocalizedName(name);

        RndItems.ITEMS.add(this);

        this.itemRarity = itemRarity;
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return RarityHelper.setRarity(this.itemRarity);
    }

    public RarityHelper.Rarity getItemRarity() {
        return itemRarity;
    }

    public void setItemRarity(RarityHelper.Rarity itemRarity) {
        this.itemRarity = itemRarity;
    }

    @Override
    public void registerModels() {
        Rnd.common.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public void registerModels(int metadata) {
        
    }
}
