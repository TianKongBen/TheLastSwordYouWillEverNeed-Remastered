package net.tianben.tlsywen.client.render;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.NotNull;

public abstract class RenderLD extends EntityRenderer<LDEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation("textures/item/diamond.png");

    protected RenderLD(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LDEntity entity) {
        return TEXTURE;
    }
}