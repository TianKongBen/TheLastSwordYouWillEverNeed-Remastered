package net.tianben.tlsywen.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class NetworkHandlerForge {
    private static final String PROTOCOL_VERSION = "1.0";
    private static final ResourceLocation CHANNEL_ID =
            new ResourceLocation("tlsywen", "main");

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            CHANNEL_ID,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private NetworkHandlerForge() {}

    public static void register() {
        int packetId = 0;
        INSTANCE.registerMessage(
                packetId,
                FlightSyncPacketForge.class,
                FlightSyncPacketForge::encode,
                FlightSyncPacketForge::decode,
                FlightSyncPacketForge::handle
        );
    }
}