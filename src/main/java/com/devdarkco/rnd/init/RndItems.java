package com.devdarkco.rnd.init;

import com.devdarkco.rnd.init.item.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RndItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item DEBUG_STICK = new ItemDebugStick("debug_stick");
    public static final Item ADVANCED_BONE_MEAL = new ItemAdvancedBoneMeal("advanced_bone_meal");
    public static final Item UPGRADE = new ItemUpgrade("upgrade");
    public static final Item KNOWLEDGE_FRAGMENT = new ItemKnowledgeFragment("knowledge_fragment");
}
