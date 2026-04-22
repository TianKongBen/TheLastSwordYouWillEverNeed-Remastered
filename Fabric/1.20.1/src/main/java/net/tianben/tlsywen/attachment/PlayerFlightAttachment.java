package net.tianben.tlsywen.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class PlayerFlightAttachment {
    public static final AttachmentType<Boolean> FLIGHT_ATTACHMENT =
            AttachmentRegistry.create(
                    new Identifier(MOD_ID, "flight_attachment")
            );

    private PlayerFlightAttachment() {}

    public static boolean hasFlight(@NotNull PlayerEntity playerEntity) {
        return playerEntity instanceof ServerPlayerEntity serverPlayerEntity &&
                serverPlayerEntity.getAttachedOrElse(FLIGHT_ATTACHMENT, false);
    }

    public static void setFlight(@NotNull PlayerEntity playerEntity, boolean enabled) {
        if (playerEntity instanceof ServerPlayerEntity serverPlayerEntity) {
            serverPlayerEntity.setAttached(FLIGHT_ATTACHMENT, enabled);
        }
    }
}