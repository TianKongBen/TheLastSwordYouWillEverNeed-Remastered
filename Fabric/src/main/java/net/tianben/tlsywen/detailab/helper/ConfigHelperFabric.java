package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public final class ConfigHelperFabric {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelperFabric.class);

    private static Supplier<IClientHelperFabric> clientConfig;
    private static final ConfigHelperFabric INSTANCE = new ConfigHelperFabric();
    private static final Client CLIENT = new Client();

    static {
        clientConfig = () -> CLIENT;
    }

    public static ConfigHelperFabric get() {
        return INSTANCE;
    }

    @ApiStatus.Internal
    public static void registerClientConfig(Supplier<IClientHelperFabric> clientConfig) {
        LOGGER.debug("Registering client config: {}",
                clientConfig != null ? "custom" : "default");
        ConfigHelperFabric.clientConfig = clientConfig != null ? clientConfig : () -> CLIENT;
    }

    private ConfigHelperFabric() {}

    public IClientHelperFabric getClient() {
        return clientConfig.get();
    }

    public static final class Client implements IClientHelperFabric {
        @Override
        public boolean forceDisableDetailArmorBarSupport() {
            return false;
        }

        @Override
        public boolean isModLoaded(String modId) {
            return false;
        }
    }
}