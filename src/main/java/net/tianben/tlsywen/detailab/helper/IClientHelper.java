package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface IClientHelper {
    boolean forceDisableDetailArmorBarSupport();

    Platform getPlatform();

    boolean isModLoaded(String modId);

    boolean isProduction();

    enum Platform {
        FABRIC
    }
}
