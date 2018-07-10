package com.ardium.pvp.common;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.*;
import com.ardium.pvp.common.network.GUIHandlerArdiumSE;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void preInitialization (FMLPreInitializationEvent preInitializationEvent) {
        ItemsRegister.initialization ();
        BlocksRegister.initialization ();
        ModChecker.checkIfModsAreLoaded ();
    }

    public void initialization (FMLInitializationEvent initializationEvent) {
        RecipesRegister.registerRecipes ();
        OreDictionnaryRegister.registerOres ();
        SmeletingRecipesRegister.register ();
        TileEntitiesRegister tileEntitiesRegister = new TileEntitiesRegister ();
        tileEntitiesRegister.initialization ();
        NetworkRegistry.INSTANCE.registerGuiHandler (ArdiumSE.instance, new GUIHandlerArdiumSE ());
    }

    public void postInitialization (FMLPostInitializationEvent postInitializationEvent) {
    }
}