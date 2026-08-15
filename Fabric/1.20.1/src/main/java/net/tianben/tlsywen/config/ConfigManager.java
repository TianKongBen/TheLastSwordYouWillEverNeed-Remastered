package net.tianben.tlsywen.config;

import net.fabricmc.loader.api.FabricLoader;

public final class ConfigManager {

    private static final boolean CLOTH_CONFIG_LOADED =
            FabricLoader.getInstance().isModLoaded("cloth-config");

    private static boolean enableCustomTab = true;
    private static boolean enableModItemGroups = true;

    private ConfigManager() {}

    public static void init() {
        if (CLOTH_CONFIG_LOADED) {
            ModConfig.register();
            syncFromClothConfig();
        }
    }

    public static boolean isEnableCustomTab() {
        if (CLOTH_CONFIG_LOADED) syncFromClothConfig();
        return enableCustomTab;
    }

    public static boolean isEnableModItemGroups() {
        if (CLOTH_CONFIG_LOADED) syncFromClothConfig();
        return enableModItemGroups;
    }

    private static void syncFromClothConfig() {
        var config = ModConfig.getInstance();
        enableCustomTab = config.enableCustomTab;
        enableModItemGroups = config.enableModItemGroups;
    }
}
