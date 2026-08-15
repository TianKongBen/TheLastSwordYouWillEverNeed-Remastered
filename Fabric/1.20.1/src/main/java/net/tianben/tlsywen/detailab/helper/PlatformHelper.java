package net.tianben.tlsywen.detailab.helper;

import net.fabricmc.loader.api.FabricLoader;

public final class PlatformHelper implements IClientHelper {

    private static final PlatformHelper INSTANCE = new PlatformHelper();

    private PlatformHelper() {}

    public static PlatformHelper getInstance() {
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
