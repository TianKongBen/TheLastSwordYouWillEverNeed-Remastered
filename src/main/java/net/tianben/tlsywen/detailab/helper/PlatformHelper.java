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
    public IClientHelper.Platform getPlatform() {
        return Platform.FABRIC;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isProduction() {
        return !FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
