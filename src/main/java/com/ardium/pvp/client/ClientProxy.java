package com.ardium.pvp.client;

import com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.TileEntityArdiumWorkbenchSpecialRenderer;
import com.ardium.pvp.client.renders.blocks.tileentitiesspecialrenderers.inventory.TESRInventoryRenderer;
import com.ardium.pvp.common.CommonProxy;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public static int renderTESRId;

    @Override
    public void preInitialization (FMLPreInitializationEvent preInitializationEvent) {
        super.preInitialization (preInitializationEvent);
        renderTESRId = RenderingRegistry.getNextAvailableRenderId ();
        ClientRegistry.bindTileEntitySpecialRenderer (TileEntityArdiumWorkbench.class, new TileEntityArdiumWorkbenchSpecialRenderer ());
        RenderingRegistry.registerBlockHandler (new TESRInventoryRenderer ());
    }
}