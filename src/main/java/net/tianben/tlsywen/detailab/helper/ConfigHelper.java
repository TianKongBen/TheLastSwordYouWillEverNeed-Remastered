package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public final class ConfigHelper {
    private static Supplier<IClientHelper> clientConfig;
    private static ConfigHelper helper;

    public static ConfigHelper get() {
        if (ConfigHelper.helper == null) {
            ConfigHelper.helper = new ConfigHelper();
        }
        return ConfigHelper.helper;
    }

    @ApiStatus.Internal
    public static void registerClientConfig(Supplier<IClientHelper> clientConfig) {
        if (ConfigHelper.clientConfig == null) {
            ConfigHelper.clientConfig = clientConfig;
        }
    }

    private ConfigHelper() {
    }

    public IClientHelper getClient() {
        return ConfigHelper.clientConfig.get();
    }

    public static final Client CLIENT;

    static {
        CLIENT = new Client();
    }

    public static class Client implements IClientHelper {
        @Override
        public boolean forceDisableDetailArmorBarSupport() {
            return false;
        }

        @Override
        public Platform getPlatform() {
            return null;
        }

        @Override
        public boolean isModLoaded(String modId) {
            return false;
        }

        @Override
        public boolean isProduction() {
            return false;
        }
    }
}
