package com.ardium.pvp.client.gui;
/*
NEVER EVER TRUST USER INPUT, be careful with every packet or everything client side
 */



import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.inventory.ContainerArdiumWorkbench;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiArdiumWorkbench extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation (ArdiumSE.MOD_ID, "textures/gui/container/guiArdiumWorkbench.png");
    private TileEntityArdiumWorkbench tileEntityArdiumWorkbench;
    private IInventory playerInventory;
    private GuiButton guiButtonGetArdiumUnityBack;

    public GuiArdiumWorkbench(TileEntityArdiumWorkbench tileEntityArdiumWorkbench, InventoryPlayer inventoryPlayer) {
        super (new ContainerArdiumWorkbench (tileEntityArdiumWorkbench, inventoryPlayer));
        this.tileEntityArdiumWorkbench = tileEntityArdiumWorkbench;
        this.playerInventory = inventoryPlayer;
        this.allowUserInput = false;
        this.xSize = 230;
        this.ySize = 219;

    }

    @Override
    public boolean doesGuiPauseGame () {
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String tileEntityName = this.tileEntityArdiumWorkbench.hasCustomInventoryName ()
                ? EnumChatFormatting.BOLD + "" + EnumChatFormatting.ITALIC + "" + this.tileEntityArdiumWorkbench.getInventoryName ()
                : EnumChatFormatting.BOLD + "" + EnumChatFormatting.ITALIC + "" + I18n.format (this.tileEntityArdiumWorkbench.getInventoryName ());
        this.fontRendererObj.drawString (tileEntityName, (this.fontRendererObj.getStringWidth (tileEntityName)) / 8 - 4,
                8, 0x7401DF);

        String playerInventoryName = this.playerInventory.hasCustomInventoryName () ? this.playerInventory
                .getInventoryName () : I18n.format (this.playerInventory.getInventoryName (), true);
        this.fontRendererObj.drawString (playerInventoryName, (this.fontRendererObj.getStringWidth
                (playerInventoryName)) - 14, this.ySize - 94, 4210752);

        String numberOfArdiumStoredLabel = I18n.format ("gui.ardiumWorkbench.ardium_stored.label")
                + " " + tileEntityArdiumWorkbench.getArdiumStoredAmount ();
        this.fontRendererObj.drawString (numberOfArdiumStoredLabel
                , (this.fontRendererObj.getStringWidth (numberOfArdiumStoredLabel)) / 8 + 92
                , 20, 0x7401DF);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        GL11.glColor4f (1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager ().bindTexture (GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect (k, l, 0, 0, this.xSize, this.ySize);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui () {
        this.buttonList.clear ();
        this.buttonList.add (new GuiButton (0, (this.width - this.xSize) / 2 + 11, (this.height - this.ySize) / 2 +
                100, 20, 20, "1"));
        super.initGui ();

    }
}