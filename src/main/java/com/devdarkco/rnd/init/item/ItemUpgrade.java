package com.devdarkco.rnd.init.item;

import com.devdarkco.rnd.Rnd;
import com.devdarkco.rnd.util.EnumUtil;
import com.devdarkco.rnd.util.RarityHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUpgrade extends RndItem {

    public static final int BASIC = 0;
    public static final int BETTER = 1;
    public static final int POWER = 2;

    public ItemUpgrade(String name) {
        super(name, RarityHelper.Rarity.COMMON);
        setUnlocalizedName("upgrade");
        this.setHasSubtypes(true);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);

        return EnumActionResult.FAIL;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        return getUnlocalizedName() + "_" + EnumUtil.UpgradeType.values()[meta].getName();
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < EnumUtil.UpgradeType.values().length; ++i) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public void registerModels(int metadata) {
        for (int i = 0; i < EnumUtil.UpgradeType.values().length; i++) {
            Rnd.common.registerItemsRenderer(this, i, "inventory", EnumUtil.UpgradeType.values()[i].getName());
        }
    }
}
