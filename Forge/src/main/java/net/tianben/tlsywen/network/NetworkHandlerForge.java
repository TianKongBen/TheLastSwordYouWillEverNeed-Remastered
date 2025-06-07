package net.tianben.tlsywen.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandlerForge {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation("tlsywen", "main"),
        () -> "1.0",
        "1.0"::equals,
        "1.0"::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, 
            FlightSyncPacketForge.class,
            FlightSyncPacketForge::encode,
            FlightSyncPacketForge::decode,
            FlightSyncPacketForge::handle
        );
    }
}