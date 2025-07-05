package net.tianben.tlsywen.util;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.NORMAL;
import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class FabricResourceManager {
    private static final String PACK_NAME = "classic_look";
    private static final ResourceLocation PACK_ID =
            new ResourceLocation(MOD_ID, PACK_NAME);

    private FabricResourceManager() {}

    public static void init() {
        FabricLoader.getInstance()
                .getModContainer(MOD_ID)
                .ifPresent(container ->
                        ResourceManagerHelper.registerBuiltinResourcePack(
                                PACK_ID,
                                container,
                                createPackTitle(),
                                NORMAL
                        ));
    }

    private static @NotNull Component createPackTitle() {
        return Component.translatable("resourcePack." + MOD_ID + "." + PACK_NAME + ".name");
    }
}