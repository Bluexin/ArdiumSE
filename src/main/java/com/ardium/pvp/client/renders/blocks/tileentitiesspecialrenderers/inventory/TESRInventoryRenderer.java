package com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.inventory;

import com.ardium.pvp.client.ClientProxy;
import com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.TileEntityArdiumWorkbenchSpecialRenderer;
import com.ardium.pvp.common.blocks.BlockContainerArdiumWorkbench;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if ( block instanceof BlockContainerArdiumWorkbench && metadata == 0 ) {
            GL11.glPushMatrix ();
            GL11.glRotated (180.0D, 0.0D, 0.0D, 1.0D);
            GL11.glRotated (180.0D, 0.0D, 1.0D, 0.0D);
            GL11.glTranslated (0.0D, -0.9D, 0.0D);
            GL11.glScaled (0.1D, 0.09275D, 0.1D);
            Minecraft.getMinecraft ().getTextureManager ().bindTexture (TileEntityArdiumWorkbenchSpecialRenderer.ARDIUM_WORKBENCH_RESOURCE_LOCATION);
            TileEntityArdiumWorkbenchSpecialRenderer.MODEL_ARDIUM_WORKBENCH.render (null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
            GL11.glPopMatrix ();
        }

    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ClientProxy.renderInventoryTESRId;
    }
}
