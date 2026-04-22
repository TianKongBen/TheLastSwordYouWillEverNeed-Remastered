package net.tianben.tlsywen.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;

@Mod.EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class NeoForgeResourceManager {
    private static final String PACK_NAME = "classic_look";
    private static final ResourceLocation PACK_ID =
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, PACK_NAME);

    private NeoForgeResourceManager() {}

    @SubscribeEvent
    public static void addResourcePack(@NotNull AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            registerResourcePack(event);
        }
    }

    private static void registerResourcePack(@NotNull AddPackFindersEvent event) {
        Path resourcePath = getResourcePackPath();
        Pack pack = createResourcePack(resourcePath);
        event.addRepositorySource(consumer -> consumer.accept(pack));
    }

    private static @NotNull Path getResourcePackPath() {
        return ModList.get()
                .getModFileById(TheLastSwordYouWillEverNeed.MOD_ID)
                .getFile()
                .findResource("resourcepacks/" + PACK_NAME);
    }

    private static @NotNull Pack createResourcePack(@NotNull Path resourcePath) {
        return Pack.create(
                PACK_ID.toString(),
                createPackTitle(),
                false,
                new Pack.ResourcesSupplier() {
                    @Override
                    public PackResources openPrimary(String id) {
                        return new PathPackResources(id, resourcePath, false);
                    }

                    @Override
                    public PackResources openFull(String id, Pack.Info info) {
                        return new PathPackResources(id, resourcePath, false);
                    }
                },
                new Pack.Info(
                        createPackDescription(),
                        PackCompatibility.COMPATIBLE,
                        FeatureFlags.DEFAULT_FLAGS,
                        List.of(),
                        false
                ),
                Pack.Position.TOP,
                false,
                PackSource.BUILT_IN
        );
    }

    private static @NotNull Component createPackTitle() {
        return Component.translatable("resourcePack." + PACK_ID.getNamespace() + "." + PACK_NAME + ".name");
    }

    private static @NotNull Component createPackDescription() {
        return Component.translatable("resourcePack." + PACK_ID.getNamespace() + "." + PACK_NAME + ".description");
    }
}