package net.tianben.tlsywen.attachment;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public final class PlayerFlightAttachment {
    public static final Capability<IFlightCapability> FLIGHT_CAPABILITY = 
            CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerFlightAttachment() {}

    public static boolean hasFlight(@NotNull Player player) {
        return player.getCapability(FLIGHT_CAPABILITY).map(IFlightCapability::hasFlight).orElse(false);
    }

    public static void setFlight(@NotNull Player player, boolean enabled) {
        player.getCapability(FLIGHT_CAPABILITY).ifPresent(cap -> cap.setFlight(enabled));
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<net.minecraft.world.entity.Entity> event) {
        if (event.getObject() instanceof Player) {
            FlightCapabilityProvider provider = new FlightCapabilityProvider();
            event.addCapability(new ResourceLocation(MOD_ID, "flight"), provider);
        }
    }

    public interface IFlightCapability {
        boolean hasFlight();
        void setFlight(boolean enabled);
    }

    public static class FlightCapabilityImpl implements IFlightCapability {
        private boolean hasFlight = false;

        @Override
        public boolean hasFlight() {
            return hasFlight;
        }

        @Override
        public void setFlight(boolean enabled) {
            this.hasFlight = enabled;
        }

        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("hasFlight", hasFlight);
            return tag;
        }

        public void deserializeNBT(CompoundTag tag) {
            hasFlight = tag.getBoolean("hasFlight");
        }
    }

    public static class FlightCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
        private final FlightCapabilityImpl instance = new FlightCapabilityImpl();
        private final LazyOptional<IFlightCapability> lazyOptional = LazyOptional.of(() -> instance);

        @Override
        public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return FLIGHT_CAPABILITY.orEmpty(cap, lazyOptional.cast());
        }

        @Override
        public CompoundTag serializeNBT() {
            return instance.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            instance.deserializeNBT(nbt);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(FLIGHT_CAPABILITY).ifPresent(oldCap -> {
                event.getEntity().getCapability(FLIGHT_CAPABILITY).ifPresent(newCap -> {
                    newCap.setFlight(oldCap.hasFlight());
                });
            });
        }
    }
}