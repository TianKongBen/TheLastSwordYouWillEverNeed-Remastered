package net.tianben.tlsywen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.render.RenderLD;

public class TheLastSwordYouWillEverNeedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //注册投掷物
        EntityRendererRegistry.register(ModEntities.LD, RenderLD::new);
    }
}
