package net.tianben.tlsywen.detailab.helper;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public final class PlatformHelperFabric implements IClientHelperFabric {
    private static final PlatformHelperFabric INSTANCE = new PlatformHelperFabric();

    private PlatformHelperFabric() {}

    public static @NotNull PlatformHelperFabric getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean forceDisableDetailArmorBarSupport() {
        return false;
    }

    @Override
    public boolean isModLoaded(@NotNull String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}