package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface IClientHelperFabric {
    boolean forceDisableDetailArmorBarSupport();

    boolean isModLoaded(String modId);
}