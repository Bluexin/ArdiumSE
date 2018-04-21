package com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.client.models.blocks.ModelArdiumWorkbench;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityArdiumWorkbenchSpecialRenderer extends TileEntitySpecialRenderer {

    public static final ResourceLocation ARDIUM_WORKBENCH_RESOURCE_LOCATION
            = new ResourceLocation (ArdiumSE.MOD_ID, "textures/models/blocks/ArdiumWorkbenchTESRModel.png");
    public static final ModelArdiumWorkbench MODEL_ARDIUM_WORKBENCH = new ModelArdiumWorkbench ();

    public TileEntityArdiumWorkbenchSpecialRenderer() {
        this.func_147497_a (TileEntityRendererDispatcher.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialRenderTick) {
        this.renderTileEntityArdiumWorkbenchAt (((TileEntityArdiumWorkbench) tileEntity), x, y, z, partialRenderTick);
    }

    private void renderTileEntityArdiumWorkbenchAt(TileEntityArdiumWorkbench tileEntityArdiumWorkbench,
                                                   double x, double y, double z, float partialRenderTick) {
        GL11.glPushMatrix ();
        GL11.glTranslated (x + 0.5D, y + 1.4D, z + 0.5D);
        GL11.glRotated (180D, 0D, 0D, 1.0D);
        GL11.glScaled (0.1, 0.09275, 0.1); //0.0625, 0.09275
        this.bindTexture (ARDIUM_WORKBENCH_RESOURCE_LOCATION);
        MODEL_ARDIUM_WORKBENCH.render (null, 0F, 0F, 0F, 0F, 0F, 0.625F);
        GL11.glPopMatrix ();
    }
}
