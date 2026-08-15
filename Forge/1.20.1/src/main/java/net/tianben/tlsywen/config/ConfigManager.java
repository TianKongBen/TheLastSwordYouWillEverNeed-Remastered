package net.tianben.tlsywen.config;

import net.minecraftforge.fml.ModList;

public final class ConfigManager {

    private static final String CLOTH_CONFIG_MOD_ID = "cloth_config";
    private static final boolean CLOTH_CONFIG_LOADED = ModList.get().isLoaded(CLOTH_CONFIG_MOD_ID);

    private static boolean enableCustomTab = true;
    private static boolean enableModItemGroups = true;

    private ConfigManager() {}

    public static void init() {
        if (CLOTH_CONFIG_LOADED) {
            ModConfig.register();
            syncFromClothConfig();
        }
    }

    public static boolean isClothConfigLoaded() {
        return CLOTH_CONFIG_LOADED;
    }

    public static boolean isEnableCustomTab() {
        if (CLOTH_CONFIG_LOADED) {
            syncFromClothConfig();
        }
        return enableCustomTab;
    }

    public static boolean isEnableModItemGroups() {
        if (CLOTH_CONFIG_LOADED) {
            syncFromClothConfig();
        }
        return enableModItemGroups;
    }

    private static void syncFromClothConfig() {
        var config = ModConfig.getInstance();
        enableCustomTab = config.enableCustomTab;
        enableModItemGroups = config.enableModItemGroups;
    }
}