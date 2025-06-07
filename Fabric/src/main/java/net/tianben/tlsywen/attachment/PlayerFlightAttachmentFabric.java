package net.tianben.tlsywen.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

public class PlayerFlightAttachmentFabric {
    public static final AttachmentType<Boolean> FLIGHT_ATTACHMENT =
            AttachmentRegistry.create(
                    new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, "flight_attachment")
            );

    public static boolean hasFlight(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return serverPlayer.getAttachedOrElse(FLIGHT_ATTACHMENT, false);
        }
        return false;
    }

    public static void setFlight(Player player, boolean enabled) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.setAttached(FLIGHT_ATTACHMENT, enabled);
        }
    }
}