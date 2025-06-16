package net.tianben.tlsywen.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record FlightSyncPacketForge(boolean canFly) {
    public static void encode(@NotNull FlightSyncPacketForge msg, @NotNull FriendlyByteBuf buffer) {
        buffer.writeBoolean(msg.canFly);
    }

    public static @NotNull FlightSyncPacketForge decode(@NotNull FriendlyByteBuf buffer) {
        return new FlightSyncPacketForge(buffer.readBoolean());
    }

    public static void handle(@NotNull FlightSyncPacketForge msg, @NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null && !player.isCreative() && !player.isSpectator()) {
                updatePlayerFlightAbility(player, msg.canFly);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private static void updatePlayerFlightAbility(@NotNull ServerPlayer player, boolean canFly) {
        player.getAbilities().mayfly = canFly;
        if (!canFly) {
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }
}