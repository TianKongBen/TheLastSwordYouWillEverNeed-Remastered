package net.tianben.tlsywen.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.tianben.tlsywen.entity.LDEntity;

@Environment(EnvType.CLIENT)
public final class FabricRenderLD extends RenderLD {
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE);
    private static final float SCALE = 0.25f;
    private static final float ROTATION = 180.0f;

    public FabricRenderLD(EntityRendererProvider.Context context) {
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
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(ROTATION));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vertexConsumer = buffer.getBuffer(RENDER_TYPE);

        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 0.0F, 0, 0, 1);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 1.0F, 0, 1, 1);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 1.0F, 1, 1, 0);
        addVertex(vertexConsumer, pose.pose(), pose.normal(), packedLight, 0.0F, 1, 0, 0);
    }

    private static void addVertex(VertexConsumer consumer, Matrix4f pose, Matrix3f normal,
                                  int light, float x, int y, int u, int v) {
        consumer.vertex(pose, x - 0.5F, y - 0.25F, 0.0F)
                .color(255, 255, 255, 255)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }
}