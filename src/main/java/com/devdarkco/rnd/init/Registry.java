package com.devdarkco.rnd.init;

import com.devdarkco.rnd.init.item.ItemAdvancedBoneMeal;
import com.devdarkco.rnd.init.tileentities.TileEntityGrowthPedestal;
import com.devdarkco.rnd.render.RenderGrowthPedestal;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Registry {

    public static void registry() {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RndItems.ADVANCED_BONE_MEAL, new Bootstrap.BehaviorDispenseOptional() {
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                this.successful = true;
                World world = source.getWorld();
                BlockPos blockpos = source.getBlockPos().offset((EnumFacing) source.getBlockState().getValue(BlockDispenser.FACING));
                ItemAdvancedBoneMeal.doBoneMealEffectOnGround(RndItems.ADVANCED_BONE_MEAL.getDefaultInstance(), world, blockpos);
                return super.dispenseStack(source, stack);
            }
        });

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrowthPedestal.class, new RenderGrowthPedestal());
    }
}
