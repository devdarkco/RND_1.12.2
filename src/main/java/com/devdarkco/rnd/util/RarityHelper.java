package com.devdarkco.rnd.util;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

public class RarityHelper {

    public enum Rarity {
        LEGENDARY(TextFormatting.GOLD, "legendary"),
        RARE(TextFormatting.DARK_BLUE, "rare"),
        COMMON(TextFormatting.WHITE, "common");

        private TextFormatting color;
        private String rarity;

        Rarity(TextFormatting color, String rarity){
            this.color = color;
            this.rarity = rarity;
        }

        public TextFormatting getColor() {
            return color;
        }

        public String getRarity() {
            return rarity;
        }
    }

    public static IRarity setRarity(Rarity rarity){
        return new IRarity() {
            @Override
            public TextFormatting getColor() {
                return rarity.getColor();
            }

            @Override
            public String getName() {
                return rarity.getRarity();
            }
        };
    }
}
