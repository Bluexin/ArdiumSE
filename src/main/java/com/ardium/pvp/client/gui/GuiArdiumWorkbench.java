package com.ardium.pvp.client.gui;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.containers.ContainerArdiumWorkbench;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiArdiumWorkbench extends GuiContainer {
    /*
    private static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation (ArdiumSE.MOD_ID, "textures/gui/container/guiArdiumWorkbench.png");
    */
    private static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation (ArdiumSE.MOD_ID, "textures/gui/container/guiArdiumWorkbench2.png");
    private TileEntityArdiumWorkbench tileEntityArdiumWorkbench;
    private IInventory playerInventory;

    public GuiArdiumWorkbench(TileEntityArdiumWorkbench tileEntityArdiumWorkbench, InventoryPlayer inventoryPlayer) {
        super (new ContainerArdiumWorkbench (tileEntityArdiumWorkbench, inventoryPlayer));
        this.tileEntityArdiumWorkbench = tileEntityArdiumWorkbench;
        this.playerInventory = inventoryPlayer;
        this.allowUserInput = false;
        this.xSize = 230;
        this.ySize = 219;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String tileEntityName = this.tileEntityArdiumWorkbench.hasCustomInventoryName () ? this.tileEntityArdiumWorkbench.getInventoryName () : I18n.format (this.tileEntityArdiumWorkbench.getInventoryName ());
        this.fontRendererObj.drawString (tileEntityName, (this.xSize - this.fontRendererObj.getStringWidth (tileEntityName)) / 2, 6, 0);
        String playerInventoryName = this.playerInventory.hasCustomInventoryName () ? this.playerInventory
                .getInventoryName () : I18n.format (this.playerInventory.getInventoryName (), true);
        this.fontRendererObj.drawString (playerInventoryName, (this.xSize - this.fontRendererObj.getStringWidth (playerInventoryName)) / 2, this.ySize - 96, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        GL11.glColor4f (1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager ().bindTexture (GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect (k, l, 0, 0, this.xSize, this.ySize);
    }
}