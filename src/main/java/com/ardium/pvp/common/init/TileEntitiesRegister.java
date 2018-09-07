package com.ardium.pvp.common.init;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import cpw.mods.fml.common.registry.GameRegistry;

public final class TileEntitiesRegister {

    public void initialization () {
        TileEntityArdiumWorkbench tileEntityArdiumWorkbench = new TileEntityArdiumWorkbench ();
        register ();

    }

    private void register () {
        GameRegistry.registerTileEntity (TileEntityArdiumWorkbench.class,
                ArdiumSE.MOD_ID + ":TileEntityArdiumWorkbench");
    }
}