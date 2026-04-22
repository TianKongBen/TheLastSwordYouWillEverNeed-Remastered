package net.tianben.tlsywen;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public final class TheLastSwordYouWillEverNeedForge {
    public TheLastSwordYouWillEverNeedForge(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}