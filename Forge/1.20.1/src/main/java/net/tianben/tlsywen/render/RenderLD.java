package net.tianben.tlsywen.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.mojang.math.Axis;

public class RenderLD extends EntityRenderer<LDEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/item/diamond.png");
    private static final RenderType LAYER = RenderType.entityCutout(TEXTURE);
    private static final float SCALE = 0.25f;
    private static final float ROTATION = 180.0f;

    public RenderLD(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull LDEntity ldEntity, float entityYaw, float partialTicks, PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight) {
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

        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 0.0F, 0, 0, 1);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 1.0F, 0, 1, 1);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 1.0F, 1, 1, 0);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 0.0F, 1, 0, 0);
    }

    private static void addVertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix,
                                  int light, float x, int y, int textureU, int textureV) {
        vertexConsumer.vertex(positionMatrix, x - 0.5f, (float)y - 0.25f, 0.0f)
                .color(255, 255, 255, 255)
                .uv(textureU, textureV)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .endVertex();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LDEntity ldEntity) {
        return TEXTURE;
    }
}