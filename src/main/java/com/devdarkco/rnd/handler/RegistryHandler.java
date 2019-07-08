package com.devdarkco.rnd.handler;

import com.devdarkco.rnd.init.RndBlocks;
import com.devdarkco.rnd.init.RndItems;
import com.devdarkco.rnd.init.tileentities.TileEntityGrowthPedestal;
import com.devdarkco.rnd.init.tileentities.TileEntityPoweredDirt;
import com.devdarkco.rnd.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
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
        GameRegistry.registerTileEntity(TileEntityGrowthPedestal.class, "rnd:growthPedestal");
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

    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event) {

        if (event.getName().equals(LootTableList.ENTITIES_WITCH)) {
            final LootPool pool = event.getTable().getPool("main");

            if(pool != null){
                pool.addEntry(new LootEntryItem(RndItems.KNOWLEDGE_FRAGMENT, 10, 0, new LootFunction[0], new LootCondition[0], "rnd:knowledge_fragment"));
            }
        }
    }
}
