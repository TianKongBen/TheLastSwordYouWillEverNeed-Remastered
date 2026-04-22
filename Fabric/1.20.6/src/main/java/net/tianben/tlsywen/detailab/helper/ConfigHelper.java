package net.tianben.tlsywen.detailab.helper;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class ConfigHelper {
    private static final AtomicReference<Supplier<IClientHelper>> CLIENT_CONFIG =
            new AtomicReference<>(() -> DefaultClient.INSTANCE);

    private ConfigHelper() {}

    public static @NotNull ConfigHelper get() {
        return InstanceHolder.INSTANCE;
    }

    @ApiStatus.Internal
    public static void registerClientConfig(Supplier<IClientHelper> clientConfig) {
        CLIENT_CONFIG.set(clientConfig != null ? clientConfig : () -> DefaultClient.INSTANCE);
    }

    public @NotNull IClientHelper getClient() {
        return CLIENT_CONFIG.get().get();
    }

    private static class InstanceHolder {
        static final ConfigHelper INSTANCE = new ConfigHelper();
    }

    private static class DefaultClient implements IClientHelper {
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
