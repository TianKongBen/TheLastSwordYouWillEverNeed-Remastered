package net.tianben.tlsywen.attachment;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class PlayerFlightAttachment {

    private PlayerFlightAttachment() {}

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> FLIGHT_ATTACHMENT =
            ATTACHMENTS.register("flight_attachment",
                    () -> AttachmentType.builder(() -> Boolean.FALSE).build());

    public static boolean hasFlight(@NotNull Player player) {
        return player instanceof ServerPlayer serverPlayer
                && Boolean.TRUE.equals(serverPlayer.getData(FLIGHT_ATTACHMENT));
    }

    public static void setFlight(@NotNull Player player, boolean enabled) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.setData(FLIGHT_ATTACHMENT, enabled);
        }
    }

    public static void register(IEventBus bus) {
        ATTACHMENTS.register(bus);
    }
}