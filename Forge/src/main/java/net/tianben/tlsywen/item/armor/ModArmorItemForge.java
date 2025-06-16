package net.tianben.tlsywen.item.armor;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.tianben.tlsywen.network.FlightSyncPacketForge;
import net.tianben.tlsywen.network.NetworkHandlerForge;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public final class ModArmorItemForge extends ModArmorItem {
    public ModArmorItemForge(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightStatus(event.player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightStatus(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onGameModeChange(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightStatus(event.getEntity());
        }
    }

    private static boolean shouldProcessEvent(PlayerEvent event) {
        return !event.getEntity().level().isClientSide();
    }

    private static boolean shouldProcessEvent(TickEvent.PlayerTickEvent event) {
        return event.phase == TickEvent.Phase.END && !event.player.level().isClientSide();
    }

    private static void updateFlightStatus(@NotNull Player player) {
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        boolean shouldFly = isWearingFullSet(player);
        boolean canFly = player.getAbilities().mayfly;

        if (shouldFly != canFly) {
            updatePlayerFlightAbility(player, shouldFly);
            syncFlightStatus(player, shouldFly);
        }
    }

    private static void updatePlayerFlightAbility(@NotNull Player player, boolean enabled) {
        player.getAbilities().mayfly = enabled;
        if (!enabled) {
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }

    private static void syncFlightStatus(@NotNull Player player, boolean status) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHandlerForge.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                    new FlightSyncPacketForge(status)
            );
        }
    }
}