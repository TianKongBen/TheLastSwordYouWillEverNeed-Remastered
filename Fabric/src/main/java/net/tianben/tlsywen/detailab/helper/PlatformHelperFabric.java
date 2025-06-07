package net.tianben.tlsywen.detailab.helper;

import net.fabricmc.loader.api.FabricLoader;

public final class PlatformHelperFabric implements IClientHelperFabric {
    private static final PlatformHelperFabric INSTANCE = new PlatformHelperFabric();

    public static PlatformHelperFabric getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean forceDisableDetailArmorBarSupport() {
        return false;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}