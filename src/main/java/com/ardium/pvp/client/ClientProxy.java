package com.ardium.pvp.client;

import com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.TileEntityArdiumWorkbenchSpecialRenderer;
import com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.inventory.TESRInventoryRenderer;
import com.ardium.pvp.common.CommonProxy;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    public static int renderInventoryTESRId;

    public void registerRenders() {
        renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId ();
        this.registerTileEntitySpecialRenderer ();
        this.registerISBRH ();
    }

    protected void registerTileEntitySpecialRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer (TileEntityArdiumWorkbench.class, new TileEntityArdiumWorkbenchSpecialRenderer ());
    }

    protected void registerISBRH() {
        RenderingRegistry.registerBlockHandler (new TESRInventoryRenderer ());
    }
}