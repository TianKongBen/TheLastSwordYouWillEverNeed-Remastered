package net.tianben.tlsywen.util;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.NORMAL;
import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class FabricResourceManager {

    private static final ResourceLocation MOD_RESOURCE_PACK_ID = new ResourceLocation(MOD_ID, "classic_look");

    //注册资源包
    public static void init() {
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
            ResourceManagerHelper.registerBuiltinResourcePack(MOD_RESOURCE_PACK_ID, modContainer,
                    Component.translatable("resourcePack.tlsywen.classic_look.name"), NORMAL));
    }
}
