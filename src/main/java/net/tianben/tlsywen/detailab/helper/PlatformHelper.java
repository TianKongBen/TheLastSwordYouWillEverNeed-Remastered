package net.tianben.tlsywen.detailab.helper;

import net.fabricmc.loader.api.FabricLoader;

public final class PlatformHelper implements IClientHelper {
    private static IClientHelper instance;

    public static IClientHelper getInstance() {
        if (instance == null) {
            instance = new PlatformHelper();
        }
        return instance;
    }

    private PlatformHelper() {
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
