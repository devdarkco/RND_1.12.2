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

        Property propertyAdvancedBoneMealRange = config.get(CATEGORY_NAME_ITEMS, "advanced_bone_meal_range", 5);
        propertyAdvancedBoneMealRange.setLanguageKey("gui.config.items.advancedBoneMeal.name");
        propertyAdvancedBoneMealRange.setComment(I18n.format("gui.config.items.advancedBoneMeal.comment"));
        propertyAdvancedBoneMealRange.setMinValue(1);

        List<String> propertyOrderItems = new ArrayList<String>();
        propertyOrderItems.add(propertyAdvancedBoneMealRange.getName());
        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderItems);

        if(readFieldsFromConfig) {
            advancedBoneMealRange = propertyAdvancedBoneMealRange.getInt();
        }

        propertyAdvancedBoneMealRange.set(advancedBoneMealRange);

        if(config.hasChanged())
            config.save();
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