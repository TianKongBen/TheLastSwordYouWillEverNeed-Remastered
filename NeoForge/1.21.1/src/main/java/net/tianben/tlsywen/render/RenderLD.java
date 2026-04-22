package net.tianben.tlsywen.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tianben.tlsywen.entity.LDEntity;

@OnlyIn(Dist.CLIENT)
public class RenderLD extends EntityRenderer<LDEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/item/diamond.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);
    private static final float SCALE = 0.25f;
    private static final float ROTATION = 180.0f;

    public RenderLD(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(LDEntity ldEntity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        preparePose(poseStack);
        renderModel(poseStack, buffer, packedLight);
        poseStack.popPose();
        super.render(ldEntity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private void preparePose(PoseStack poseStack) {
        poseStack.scale(SCALE, SCALE, SCALE);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(ROTATION));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vertexConsumer = buffer.getBuffer(LAYER);

        addVertex(vertexConsumer, pose, packedLight, 0.0F, 0, 0, 1);
        addVertex(vertexConsumer, pose, packedLight, 1.0F, 0, 1, 1);
        addVertex(vertexConsumer, pose, packedLight, 1.0F, 1, 1, 0);
        addVertex(vertexConsumer, pose, packedLight, 0.0F, 1, 0, 0);
    }

    private static void addVertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, int packedLight,
                                  float x, int y, int u, int v) {
        vertexConsumer.addVertex(pose, x - 0.5F, (float) y - 0.25F, 0.0F)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(LDEntity entity) {
        return TEXTURE;
    }
}