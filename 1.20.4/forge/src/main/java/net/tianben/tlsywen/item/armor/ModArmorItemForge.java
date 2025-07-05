package net.tianben.tlsywen.item.armor;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public final class ModArmorItemForge extends ModArmorItem {

    public ModArmorItemForge(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightAbility(event.player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightAbility(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onGameModeChange(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (shouldProcessEvent(event)) {
            updateFlightAbility(event.getEntity());
        }
    }

    private static boolean shouldProcessEvent(PlayerEvent event) {
        return !event.getEntity().level().isClientSide();
    }

    private static boolean shouldProcessEvent(TickEvent.PlayerTickEvent event) {
        return event.phase == TickEvent.Phase.END && !event.player.level().isClientSide();
    }

    private static void updateFlightAbility(@NotNull Player player) {
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        boolean shouldFly = isWearingFullSet(player);
        boolean canFly = player.getAbilities().mayfly;

        if (shouldFly != canFly) {
            player.getAbilities().mayfly = shouldFly;
            if (!shouldFly) {
                player.getAbilities().flying = false;
            }
            player.onUpdateAbilities();
        }
    }
}