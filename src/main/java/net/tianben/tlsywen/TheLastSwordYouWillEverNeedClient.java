package net.tianben.tlsywen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.tianben.tlsywen.detailab.helper.ConfigHelper;
import net.tianben.tlsywen.detailab.DetailArmorBar;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.registry.Renderers;
//import net.tianben.tlsywen.render.CustomTabRenderer;

public class TheLastSwordYouWillEverNeedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //注册投掷物
        EntityRendererRegistry.register(ModEntities.LD, FlyingItemEntityRenderer::new);
        //渲染
        Renderers.registerRenderers();
        //细节盔甲
        if (TheLastSwordYouWillEverNeed.getPlatformHelper().isModLoaded("detailab")
                && !ConfigHelper.get().getClient().forceDisableDetailArmorBarSupport())
        {
            DetailArmorBar.register();
        }

//        CustomTabRenderer.registerEvents();
    }
}
