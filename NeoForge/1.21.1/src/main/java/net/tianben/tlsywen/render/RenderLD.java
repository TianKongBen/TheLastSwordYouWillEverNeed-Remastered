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

    private static final ResourceLocation TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/item/diamond.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    private static final float SCALE = 0.25F;
    private static final float ROTATION = 180.0F;

    /**
     * 一个 1×1 的四边形（两个三角形共 4 个顶点）。
     * 每个顶点：{@code x, y, u, v}，其中 x/y 是单位坐标（0 或 1），u/v 是纹理坐标。
     * 索引顺序构成逆时针的正面朝向相机的面。
     */
    private static final float[][] QUAD_VERTICES = {
            {0.0F, 0.0F, 0.0F, 1.0F},
            {1.0F, 0.0F, 1.0F, 1.0F},
            {1.0F, 1.0F, 1.0F, 0.0F},
            {0.0F, 1.0F, 0.0F, 0.0F},
    };

    public RenderLD(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(LDEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        preparePose(poseStack);
        renderModel(poseStack, buffer, packedLight);
        poseStack.popPose();

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private void preparePose(PoseStack poseStack) {
        poseStack.scale(SCALE, SCALE, SCALE);
        poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(ROTATION));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        var pose = poseStack.last();
        var vertexConsumer = buffer.getBuffer(LAYER);

        for (var v : QUAD_VERTICES) {
            addVertex(vertexConsumer, pose, packedLight, v[0], v[1], v[2], v[3]);
        }
    }

    private static void addVertex(VertexConsumer vertexConsumer, PoseStack.Pose pose,
                                  int packedLight,
                                  float x, float y, float u, float v) {
        vertexConsumer.addVertex(pose, x - 0.5F, y - 0.25F, 0.0F)
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