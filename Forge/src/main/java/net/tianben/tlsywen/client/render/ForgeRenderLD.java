package net.tianben.tlsywen.client.render;

import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.tianben.tlsywen.entity.ModEntitiesForge;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tianben.tlsywen.entity.LDEntity;

@OnlyIn(Dist.CLIENT)
public class ForgeRenderLD extends RenderLD {
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public ForgeRenderLD(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(LDEntity entity, float yaw, float partialTicks, PoseStack poseStack,
                     MultiBufferSource buffer, int light) {
        if (!entity.getType().equals(ModEntitiesForge.LD.get())) {
            return;
        }
        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        VertexConsumer vertexconsumer = buffer.getBuffer(LAYER);
        
        vertex(vertexconsumer, matrix4f, matrix3f, light, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, light, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, light, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, light, 0.0F, 1, 0, 0);
        
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f pose, Matrix3f normal, 
                             int light, float x, int y, int u, int v) {
        consumer.vertex(pose, x - 0.5F, (float)y - 0.25F, 0.0F)
               .color(255, 255, 255, 255)
               .uv(u, v)
               .overlayCoords(OverlayTexture.NO_OVERLAY)
               .uv2(light)
               .normal(normal, 0.0F, 1.0F, 0.0F)
               .endVertex();
    }
}