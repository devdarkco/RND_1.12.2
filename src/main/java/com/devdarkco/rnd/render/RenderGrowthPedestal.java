package com.devdarkco.rnd.render;

import com.devdarkco.rnd.init.tileentities.TileEntityGrowthPedestal;
import com.devdarkco.rnd.util.RenderUtil;
import com.devdarkco.rnd.util.WorldUtil;
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

                String s = WorldUtil.isNightTime(te.getWorld()) ? "Sun is needed" : "Working...";
                RenderUtil.renderTextOnBlock(rendererDispatcher, te, s, 20, 0, 0xFFFFFF, (float) x+0.5D , (float) y+1D, (float) z+0.5D, false);

            }
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();


    }
}
