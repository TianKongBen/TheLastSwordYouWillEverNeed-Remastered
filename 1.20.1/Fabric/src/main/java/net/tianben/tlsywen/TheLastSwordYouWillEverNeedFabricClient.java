package net.tianben.tlsywen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tianben.tlsywen.client.render.FabricRenderLD;
import net.tianben.tlsywen.detailab.DetailArmorBarFabric;
import net.tianben.tlsywen.detailab.helper.ConfigHelperFabric;
import net.tianben.tlsywen.detailab.helper.IClientHelperFabric;
import net.tianben.tlsywen.entity.ModEntitiesFabric;

public class TheLastSwordYouWillEverNeedFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntitiesFabric.LD, FabricRenderLD::new);
        //细节盔甲
        IClientHelperFabric helper = ConfigHelperFabric.get().getClient();
        if (helper.isModLoaded("detailab") && !helper.forceDisableDetailArmorBarSupport()) {
            DetailArmorBarFabric.register();
        }
    }
}
