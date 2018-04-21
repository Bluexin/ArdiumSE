package com.ardium.pvp.common.init;

import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntitiesRegister {

    public void initialization() {
        TileEntityArdiumWorkbench tileEntityArdiumWorkbench = new TileEntityArdiumWorkbench ();
        register ();

    }

    private void register() {
        GameRegistry.registerTileEntity (TileEntityArdiumWorkbench.class, "TileEntityArdiumWorkbench");
    }
}