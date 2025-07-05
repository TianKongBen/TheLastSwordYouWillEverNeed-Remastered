package net.tianben.tlsywen.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import org.jetbrains.annotations.NotNull;

public final class PlayerFlightAttachmentFabric {
    public static final AttachmentType<Boolean> FLIGHT_ATTACHMENT =
            AttachmentRegistry.create(
                    new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, "flight_attachment")
            );

    private PlayerFlightAttachmentFabric() {}

    public static boolean hasFlight(@NotNull Player player) {
        return player instanceof ServerPlayer serverPlayer &&
                serverPlayer.getAttachedOrElse(FLIGHT_ATTACHMENT, false);
    }

    public static void setFlight(@NotNull Player player, boolean enabled) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.setAttached(FLIGHT_ATTACHMENT, enabled);
        }
    }
}