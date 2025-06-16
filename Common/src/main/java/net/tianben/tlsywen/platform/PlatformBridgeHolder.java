package net.tianben.tlsywen.platform;

import org.jetbrains.annotations.NotNull;


public final class PlatformBridgeHolder {
    private static volatile PlatformBridge bridge;

    private PlatformBridgeHolder() {
        throw new UnsupportedOperationException("Utility class");
    }

    @NotNull
    public static PlatformBridge getBridge() {
        if (bridge == null) {
            throw new IllegalStateException("PlatformBridge not initialized");
        }
        return bridge;
    }

    public static void setBridge(@NotNull PlatformBridge bridge) {
        if (bridge == null) {
            throw new IllegalArgumentException("Bridge cannot be null");
        }
        PlatformBridgeHolder.bridge = bridge;
    }
}