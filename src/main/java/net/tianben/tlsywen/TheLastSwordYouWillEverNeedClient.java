package net.tianben.tlsywen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.tianben.tlsywen.detailab.helper.ConfigHelper;
import net.tianben.tlsywen.detailab.DetailArmorBar;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.registry.Renderers;

public class TheLastSwordYouWillEverNeedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //注册投掷物
        EntityRendererRegistry.register(ModEntities.LDlv1, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv2, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv3, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv4, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv5, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv6, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv7, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv8, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv9, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LDlv10, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ReallyLD, FlyingItemEntityRenderer::new);
        //渲染
        Renderers.registerRenderers();
        //细节盔甲
        if (TheLastSwordYouWillEverNeed.getPlatformHelper().isModLoaded("detailab")
                && !ConfigHelper.get().getClient().forceDisableDetailArmorBarSupport())
        {
            DetailArmorBar.register();
        }

    }
}
