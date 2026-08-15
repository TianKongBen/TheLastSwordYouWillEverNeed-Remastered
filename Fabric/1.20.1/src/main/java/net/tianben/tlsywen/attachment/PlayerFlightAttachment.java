package net.tianben.tlsywen.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@SuppressWarnings("UnstableApiUsage")
public final class PlayerFlightAttachment {

    public static final AttachmentType<Boolean> FLIGHT_ATTACHMENT =
            AttachmentRegistry.create(new Identifier(MOD_ID, "flight_attachment"));

    private PlayerFlightAttachment() {}

    public static boolean hasFlight(PlayerEntity player) {
        return player instanceof ServerPlayerEntity sp && sp.getAttachedOrElse(FLIGHT_ATTACHMENT, false);
    }

    public static void setFlight(PlayerEntity player, boolean enabled) {
        if (player instanceof ServerPlayerEntity sp) {
            sp.setAttached(FLIGHT_ATTACHMENT, enabled);
        }
    }
}
