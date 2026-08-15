package net.tianben.tlsywen.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
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

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID)
public final class ResourceManager {

    private ResourceManager() {}

    private static final String PACK_NAME = "classic_look";
    private static final String MOD_ID = TheLastSwordYouWillEverNeed.MOD_ID;
    private static final ResourceLocation PACK_ID =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, PACK_NAME);

    private static final String I18N_PREFIX = "resourcePack." + MOD_ID + "." + PACK_NAME + ".";

    @SubscribeEvent
    public static void addResourcePack(@NotNull AddPackFindersEvent event) {
        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        var resourcePath = ModList.get()
                .getModFileById(MOD_ID)
                .getFile()
                .findResource("resourcepacks/" + PACK_NAME);

        var locationInfo = new PackLocationInfo(
                PACK_ID.toString(),
                Component.translatable(I18N_PREFIX + "name"),
                PackSource.BUILT_IN,
                Optional.empty()
        );

        Pack.ResourcesSupplier resourcesSupplier = new Pack.ResourcesSupplier() {
            @Override
            public @NotNull PackResources openPrimary(@NotNull PackLocationInfo info) {
                return new PathPackResources(info, resourcePath);
            }

            @Override
            public @NotNull PackResources openFull(@NotNull PackLocationInfo info, Pack.@NotNull Metadata metadata) {
                return new PathPackResources(info, resourcePath);
            }
        };

        var metadata = new Pack.Metadata(
                Component.translatable(I18N_PREFIX + "description"),
                PackCompatibility.COMPATIBLE,
                FeatureFlags.DEFAULT_FLAGS,
                List.of()
        );

        var selectionConfig = new PackSelectionConfig(false, Pack.Position.TOP, false);

        var pack = Pack.readMetaAndCreate(locationInfo, resourcesSupplier,
                PackType.CLIENT_RESOURCES, selectionConfig);
        if (pack != null) {
            event.addRepositorySource(consumer -> consumer.accept(pack));
        }
    }
}