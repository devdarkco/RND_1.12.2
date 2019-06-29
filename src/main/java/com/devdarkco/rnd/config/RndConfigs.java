package com.devdarkco.rnd.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.devdarkco.rnd.RndConstants;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RndConfigs {

    private static Configuration config = null;

    public static final String CATEGORY_NAME_BLOCKS = "blocks";
    public static final String CATEGORY_NAME_ITEMS = "items";

    //Blocks/TileEntity
    public static int poweredDirtMaxSpeed;

    //Items
    public static int advancedBoneMealRange;

    public static void preInit() {
        File configFile = new File(Loader.instance().getConfigDir(), "RndConfigs.cfg");
        config = new Configuration(configFile);
        syncFromFiles();
    }

    public static Configuration getConfig() {
        return config;
    }


    public static void clientPreInit() {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static void syncFromFiles() {
        syncConfig(true, true);
    }

    public static void syncFromGui() {
        syncConfig(false, true);
    }

    public static void syncFromFields() {
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
        if(loadFromConfigFile)
            config.load();

        setItemsConfigs(readFieldsFromConfig);
        setBlocksConfigs(readFieldsFromConfig);

        if(config.hasChanged())
            config.save();
    }

    private static void setBlocksConfigs(boolean readFieldsFromConfig) {
        Property propertyPoweredDirtMaxSpeed = config.get(CATEGORY_NAME_BLOCKS, "powered_dirt_max_speed", 7);
        propertyPoweredDirtMaxSpeed.setLanguageKey("gui.config.blocks.poweredDirt.name");
        propertyPoweredDirtMaxSpeed.setComment(I18n.format("gui.config.blocks.poweredDirt.comment"));
        propertyPoweredDirtMaxSpeed.setMinValue(1);

        List<String> propertyOrderBlocks = new ArrayList<String>();
        propertyOrderBlocks.add(propertyPoweredDirtMaxSpeed.getName());
        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderBlocks);

        if(readFieldsFromConfig) {
            poweredDirtMaxSpeed = propertyPoweredDirtMaxSpeed.getInt();
        }

        propertyPoweredDirtMaxSpeed.set(poweredDirtMaxSpeed);
    }

    public static void setItemsConfigs(boolean readFieldsFromConfig){
        Property propertyAdvancedBoneMealRange = config.get(CATEGORY_NAME_ITEMS, "advanced_bone_meal_range", 5);
        propertyAdvancedBoneMealRange.setLanguageKey("gui.config.items.advancedBoneMeal.name");
        propertyAdvancedBoneMealRange.setComment(I18n.format("gui.config.items.advancedBoneMeal.comment"));
        propertyAdvancedBoneMealRange.setMinValue(1);
        propertyAdvancedBoneMealRange.setMaxValue(100);

        List<String> propertyOrderItems = new ArrayList<String>();
        propertyOrderItems.add(propertyAdvancedBoneMealRange.getName());
        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderItems);

        if(readFieldsFromConfig) {
            advancedBoneMealRange = propertyAdvancedBoneMealRange.getInt();
        }

        propertyAdvancedBoneMealRange.set(advancedBoneMealRange);
    }

    public static class ConfigEventHandler {

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(RndConstants.MODID)) {
                syncFromGui();
            }
        }

    }

}