package net.tianben.tlsywen.util;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.NORMAL;
import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ResourceManager {
    private static final String PACK_NAME = "classic_look";
    private static final Identifier MOD_RESOURCE_PACK_ID =
            Identifier.of(MOD_ID, PACK_NAME);

    //注册资源包
    public static void init() {
        FabricLoader.getInstance()
                .getModContainer(MOD_ID)
                .ifPresent(modContainer ->
                        ResourceManagerHelper.registerBuiltinResourcePack(
                                MOD_RESOURCE_PACK_ID,
                                modContainer,
                                createPackTitle(),
                                NORMAL
                        ));
    }

    private static Text createPackTitle() {
        return Text.translatable("resourcePack." + MOD_ID + "." + PACK_NAME + ".name");
    }
}
