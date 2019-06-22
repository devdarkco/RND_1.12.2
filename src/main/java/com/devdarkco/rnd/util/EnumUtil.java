package com.devdarkco.rnd.util;

public class EnumUtil {

    public static enum UpgradeType {
        BASIC(0, "basic", 1),
        BETTER(1, "better", 2),
        POWER(2, "power", 3);

        private int metadata;
        private String name;
        private int upgradeValue;

        private UpgradeType(int metadata, String name, int upgradeValue){
            this.metadata = metadata;
            this.name = name;
            this.upgradeValue = upgradeValue;
        }

        public int getMetadata() {
            return metadata;
        }

        public String getName() {
            return name;
        }

        public int getUpgradeValue() {
            return upgradeValue;
        }
    }
}
