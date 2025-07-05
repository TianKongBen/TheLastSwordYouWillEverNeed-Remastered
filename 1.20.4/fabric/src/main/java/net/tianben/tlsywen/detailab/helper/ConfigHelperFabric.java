package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class ConfigHelperFabric {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelperFabric.class);
    private static final AtomicReference<Supplier<IClientHelperFabric>> CLIENT_CONFIG =
            new AtomicReference<>(() -> DefaultClient.INSTANCE);

    private ConfigHelperFabric() {}

    public static @NotNull ConfigHelperFabric get() {
        return InstanceHolder.INSTANCE;
    }

    @ApiStatus.Internal
    public static void registerClientConfig(Supplier<IClientHelperFabric> clientConfig) {
        LOGGER.debug("Registering client config: {}",
                clientConfig != null ? "custom" : "default");
        CLIENT_CONFIG.set(clientConfig != null ? clientConfig : () -> DefaultClient.INSTANCE);
    }

    public @NotNull IClientHelperFabric getClient() {
        return CLIENT_CONFIG.get().get();
    }

    private static class InstanceHolder {
        static final ConfigHelperFabric INSTANCE = new ConfigHelperFabric();
    }

    private static class DefaultClient implements IClientHelperFabric {
        static final DefaultClient INSTANCE = new DefaultClient();

        @Override
        public boolean forceDisableDetailArmorBarSupport() {
            return false;
        }

        @Override
        public boolean isModLoaded(@NotNull String modId) {
            return false;
        }
    }
}