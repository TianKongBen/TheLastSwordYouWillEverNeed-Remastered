package net.tianben.tlsywen.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.tianben.tlsywen.entity.LDEntity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@Environment(value=EnvType.CLIENT)
public class RenderLD extends EntityRenderer<LDEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/item/diamond.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutout(TEXTURE);
    private static final float SCALE = 0.25f;
    private static final float ROTATION = 180.0f;

    public RenderLD(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(LDEntity ldEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        preparePose(matrixStack);
        renderModel(matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
        super.render(ldEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private void preparePose(MatrixStack matrixStack) {
        matrixStack.scale(SCALE, SCALE, SCALE);
        matrixStack.multiply(dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ROTATION));
    }

    private void renderModel(MatrixStack poseStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        MatrixStack.Entry entry = poseStack.peek();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);

        addVertex(vertexConsumer, entry.getPositionMatrix(), entry.getNormalMatrix(), i, 0.0F, 0, 0, 1);
        addVertex(vertexConsumer, entry.getPositionMatrix(), entry.getNormalMatrix(), i, 1.0F, 0, 1, 1);
        addVertex(vertexConsumer, entry.getPositionMatrix(), entry.getNormalMatrix(), i, 1.0F, 1, 1, 0);
        addVertex(vertexConsumer, entry.getPositionMatrix(), entry.getNormalMatrix(), i, 0.0F, 1, 0, 0);
    }

    private static void addVertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix,
                                  int light, float x, int y, int textureU, int textureV) {
        vertexConsumer.vertex(positionMatrix, x - 0.5f, (float)y - 0.25f, 0.0f)
                .color(255, 255, 255, 255)
                .texture(textureU, textureV)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }

    @Override
    public Identifier getTexture(LDEntity ldEntity) {
        return TEXTURE;
    }
}