package net.tianben.tlsywen.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeResourceManager {
    private static final String PACK_NAME = "classic_look";
    private static final ResourceLocation PACK_ID = new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, PACK_NAME);
    private static final Component PACK_TITLE = Component.translatable("resourcePack.tlsywen.classic_look.name");
    private static final Component PACK_DESC = Component.translatable("resourcePack.tlsywen.classic_look.description");

    @SubscribeEvent
    public static void addResourcePack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get()
                    .getModFileById(TheLastSwordYouWillEverNeed.MOD_ID)
                    .getFile()
                    .findResource("resourcepacks/" + PACK_NAME);

            Pack pack = Pack.create(
                    PACK_ID.toString(),
                    PACK_TITLE,
                    false,
                    p -> new net.minecraftforge.resource.PathPackResources(
                                                p,
                            false,
                            resourcePath
                                        ),
                    new Pack.Info(
                            PACK_DESC,
                            15,
                            FeatureFlags.DEFAULT_FLAGS
                    ),
                    PackType.CLIENT_RESOURCES,
                    Pack.Position.TOP,
                    false,
                    PackSource.BUILT_IN
            );

            event.addRepositorySource(consumer -> consumer.accept(pack));
        }
    }
}