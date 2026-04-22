package net.tianben.tlsywen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mod(MOD_ID)
public class TheLastSwordYouWillEverNeedNeoForge {
    public TheLastSwordYouWillEverNeedNeoForge(IEventBus modEventBus, ModContainer modContainer) {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
