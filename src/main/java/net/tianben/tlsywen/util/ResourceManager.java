package net.tianben.tlsywen.util;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.NORMAL;
import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ResourceManager {

    private static final Identifier MOD_RESOURCE_PACK_ID = new Identifier(MOD_ID, "classic_look");

    //注册资源包
    public static void init() {
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
            ResourceManagerHelper.registerBuiltinResourcePack(MOD_RESOURCE_PACK_ID, modContainer,
                    Text.translatable("resourcePack.tlsywen.classic_look.name"), NORMAL));
    }
}
