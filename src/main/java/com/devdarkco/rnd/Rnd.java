package com.devdarkco.rnd;

import com.devdarkco.rnd.config.RndConfigs;
import com.devdarkco.rnd.init.Registry;
import com.devdarkco.rnd.proxy.CommonProxy;
import com.devdarkco.rnd.structures.StructureGen;
import com.devdarkco.rnd.tabs.RndBlocksTab;
import com.devdarkco.rnd.tabs.RndItemsTab;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = RndConstants.MODID, name = RndConstants.NAME, version = RndConstants.VERSION, guiFactory = RndConstants.GUI_FACTORY)
public class Rnd {

    @Mod.Instance
    public static Rnd instance;
    @SidedProxy(clientSide = RndConstants.CLIENT_PROXY, serverSide = RndConstants.COMMON_PROXY)
    public static CommonProxy common;
    public static RndItemsTab rndItemsTab = new RndItemsTab();
    public static RndBlocksTab rndBlocksTab = new RndBlocksTab();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RndConfigs.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        RndConfigs.preInit();
        RndConfigs.clientPreInit();
        Registry.registry();
        GameRegistry.registerWorldGenerator(new StructureGen(), 2);
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
