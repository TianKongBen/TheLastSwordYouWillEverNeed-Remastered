package net.tianben.tlsywen.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.render.*;


@Environment(EnvType.CLIENT)
public class Renderers {
    public static void registerRenderers() {
        EntityRendererRegistry.register(ModEntities.LD, RenderLD::new);
    }
}
