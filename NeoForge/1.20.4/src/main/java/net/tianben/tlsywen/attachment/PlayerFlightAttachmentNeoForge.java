package net.tianben.tlsywen.attachment;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class PlayerFlightAttachmentNeoForge {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> FLIGHT_ATTACHMENT =
            ATTACHMENTS.register("flight_attachment",
                    () -> AttachmentType.builder(() -> false).build()
            );

    public static boolean hasFlight(@NotNull Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return serverPlayer.getData(FLIGHT_ATTACHMENT);
        }
        return false;
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