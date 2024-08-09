package net.tianben.tlsywen.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.render.*;


@Environment(EnvType.CLIENT)
public class Renderers {
    public static void registerRenderers() {
        EntityRendererRegistry.register(ModEntities.LDlv1, RenderLDlv1::new);
        EntityRendererRegistry.register(ModEntities.LDlv2, RenderLDlv2::new);
        EntityRendererRegistry.register(ModEntities.LDlv3, RenderLDlv3::new);
        EntityRendererRegistry.register(ModEntities.LDlv4, RenderLDlv4::new);
        EntityRendererRegistry.register(ModEntities.LDlv5, RenderLDlv5::new);
        EntityRendererRegistry.register(ModEntities.LDlv6, RenderLDlv6::new);
        EntityRendererRegistry.register(ModEntities.LDlv7, RenderLDlv7::new);
        EntityRendererRegistry.register(ModEntities.LDlv8, RenderLDlv8::new);
        EntityRendererRegistry.register(ModEntities.LDlv9, RenderLDlv9::new);
        EntityRendererRegistry.register(ModEntities.LDlv10, RenderLDlv10::new);
        EntityRendererRegistry.register(ModEntities.ReallyLD, RenderReallyLD::new);
    }
}
