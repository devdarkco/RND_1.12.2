package com.devdarkco.rnd.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class RenderUtil {

    public static void renderTextOnBlock(TileEntityRendererDispatcher rendererDispatcher, TileEntity te, String str, int maxDistance, int verticalShift, int color, double x, double y, double z, boolean isThirdPersonFrontal) {
        Entity entity = rendererDispatcher.entity;
        double teDistanceFromPlayer = te.getDistanceSq(entity.posX, entity.posY, entity.posZ);

        if (teDistanceFromPlayer <= (double) (maxDistance * maxDistance)) {
            float yaw = rendererDispatcher.entityYaw;
            float pitch = rendererDispatcher.entityPitch;
            renderLikeNameTag(Minecraft.getMinecraft().fontRenderer, str, verticalShift, color, (float) x, (float) y, (float) z, yaw, pitch, isThirdPersonFrontal);
        }
    }

    private static void renderLikeNameTag(FontRenderer fontRendererIn, String str, int verticalShift, int color, float x, float y, float z, float viewerYaw, float viewerPitch, boolean isThirdPersonFrontal) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float) (isThirdPersonFrontal ? -1 : 1) * viewerPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-0.025F, -0.025F, 0.025F);
        fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, color);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.popMatrix();
    }
}
