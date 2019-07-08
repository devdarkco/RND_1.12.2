package com.devdarkco.rnd.render;

import com.devdarkco.rnd.init.tileentities.TileEntityGrowthPedestal;
import com.devdarkco.rnd.util.Debug;
import com.devdarkco.rnd.util.WorldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import java.awt.*;

public class RenderGrowthPedestal extends TileEntitySpecialRenderer<TileEntityGrowthPedestal> {

    @Override
    public void render(TileEntityGrowthPedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.pushMatrix();
            {
                GlStateManager.depthMask(false);
                GlStateManager.translate(0.5, 1, 0.5);
                GlStateManager.rotate(180F, 0, 1, 0);
                GlStateManager.translate(0.0675, 0.005, -0.032);
                GlStateManager.translate(-6.5 * 0.0625, -3.5 * 0.0625, 3.01 * 0.0625);
                GlStateManager.scale(0.010416667F, -0.010416667F, 0.010416667F);
                GlStateManager.glNormal3f(0.0F, 0.0F, -0.010416667F);
                String s = WorldUtil.isNightTime(te.getWorld()) ? "Sun is needed" : "Working...";
                Debug.l("String: " + s + " Boolean: " + WorldUtil.isNightTime(te.getWorld()));
                Minecraft.getMinecraft().fontRenderer.drawString(s, 0, 0, Color.RED.getRGB());
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.depthMask(true);
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();


    }
}
