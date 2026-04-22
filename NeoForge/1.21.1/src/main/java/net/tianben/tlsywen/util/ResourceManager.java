package net.tianben.tlsywen.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID)
public class ResourceManager {
    private static final String PACK_NAME = "classic_look";
    private static final ResourceLocation PACK_ID =
            ResourceLocation.fromNamespaceAndPath(TheLastSwordYouWillEverNeed.MOD_ID, PACK_NAME);

    @SubscribeEvent
    public static void addResourcePack(@NotNull AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get()
                    .getModFileById(TheLastSwordYouWillEverNeed.MOD_ID)
                    .getFile()
                    .findResource("resourcepacks/" + PACK_NAME);

            PackLocationInfo locationInfo = new PackLocationInfo(
                    PACK_ID.toString(),
                    Component.translatable("resourcePack." + PACK_ID.getNamespace() + "." + PACK_NAME + ".name"),
                    PackSource.BUILT_IN,
                    Optional.empty()
            );

            Pack.ResourcesSupplier resourcesSupplier = new Pack.ResourcesSupplier() {
                @Override
                public @NotNull PackResources openPrimary(@NotNull PackLocationInfo packLocationInfo) {
                    return new PathPackResources(packLocationInfo, resourcePath);
                }

                @Override
                public @NotNull PackResources openFull(@NotNull PackLocationInfo packLocationInfo, Pack.@NotNull Metadata metadata) {
                    return new PathPackResources(packLocationInfo, resourcePath);
                }
            };

            Pack.Metadata metadata = new Pack.Metadata(
                    Component.translatable("resourcePack." + PACK_ID.getNamespace() + "." + PACK_NAME + ".description"),
                    PackCompatibility.COMPATIBLE,
                    FeatureFlags.DEFAULT_FLAGS,
                    List.of()
            );

            PackSelectionConfig selectionConfig = new PackSelectionConfig(false, Pack.Position.TOP, false);

            Pack pack = Pack.readMetaAndCreate(locationInfo, resourcesSupplier, PackType.CLIENT_RESOURCES, selectionConfig);
            if (pack != null) {
                event.addRepositorySource(consumer -> consumer.accept(pack));
            }
        }
    }
}