package com.ardium.pvp.common;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.init.ItemsRegister;
import com.ardium.pvp.common.init.SmeletingRecipesRegister;
import com.ardium.pvp.common.init.TileEntitiesRegister;
import com.ardium.pvp.common.network.GUIHandlerArdiumSE;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void preInitialization (FMLPreInitializationEvent preInitializationEvent) {
        ItemsRegister.initialization ();
        BlocksRegister.initialization ();
        TileEntitiesRegister tileEntitiesRegister = new TileEntitiesRegister ();
        tileEntitiesRegister.initialization ();
        SmeletingRecipesRegister.register ();

    }

    public void initialization (FMLInitializationEvent initializationEvent) {
        // MinecraftForge.EVENT_BUS.register (new EventsHandlerArdiumSE ());
        // FMLCommonHandler.instance ().bus ().register (new EventsHandlerArdiumSE ());
        NetworkRegistry.INSTANCE.registerGuiHandler (ArdiumSE.instance, new GUIHandlerArdiumSE ());
    }

    public void postInitialization (FMLPostInitializationEvent postInitializationEvent) {
    }
}