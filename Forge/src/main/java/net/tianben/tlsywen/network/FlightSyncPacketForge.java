package net.tianben.tlsywen.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public record FlightSyncPacketForge(boolean canFly) {
    public static void encode(FlightSyncPacketForge msg, FriendlyByteBuf buffer) {
        buffer.writeBoolean(msg.canFly);
    }

    public static FlightSyncPacketForge decode(FriendlyByteBuf buffer) {
        return new FlightSyncPacketForge(buffer.readBoolean());
    }

    public static void handle(FlightSyncPacketForge msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            if (player != null) {
                player.getAbilities().mayfly = msg.canFly;
                if (!msg.canFly) {
                    player.getAbilities().flying = false;
                }
                player.onUpdateAbilities();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}