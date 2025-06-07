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

@Mod.EventBusSubscriber
public class ModArmorItemForge extends ModArmorItem {

    public ModArmorItemForge(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            updateFlightStatus(event.player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            updateFlightStatus(event.getEntity());
        }
    }

    // 监听游戏模式切换事件
    @SubscribeEvent
    public static void onGameModeChange(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            updateFlightStatus(event.getEntity());
        }
    }

    private static void updateFlightStatus(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        boolean shouldFly = isWearingFullSet(player);
        boolean canFly = player.getAbilities().mayfly;

        if (shouldFly != canFly) {
            player.getAbilities().mayfly = shouldFly;
            if (!shouldFly) {
                player.getAbilities().flying = false; // 强制取消飞行
            }
            player.onUpdateAbilities();

            if (player instanceof ServerPlayer serverPlayer) {
                NetworkHandlerForge.INSTANCE.send(
                        PacketDistributor.PLAYER.with(() -> serverPlayer),
                        new FlightSyncPacketForge(shouldFly)
                );
            }
        }
    }
}