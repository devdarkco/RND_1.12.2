package com.devdarkco.rnd.handler;

import com.devdarkco.rnd.init.RndBlocks;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.init.tileentities.TileEntityPoweredDirt;
import com.devdarkco.rnd.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(RndItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(RndBlocks.BLOCKS.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TileEntityPoweredDirt.class, "rnd:poweredDirt");
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        for(Item item : RndItems.ITEMS){
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModels();
                ((IHasModel)item).registerModels(item.getMetadata(item.getDefaultInstance()));
            }
        }

        for(Block block : RndBlocks.BLOCKS){
            if(block instanceof IHasModel){
                ((IHasModel)block).registerModels();
            }
        }
    }
}
