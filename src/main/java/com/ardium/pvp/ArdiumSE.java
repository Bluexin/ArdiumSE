package com.ardium.pvp;

import com.ardium.pvp.common.CommonProxy;
import com.ardium.pvp.common.TabArdiumSE;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;

@Mod (modid = ArdiumSE.MOD_ID, name = "ArdiumSE", version = "Special Edition 0.2", guiFactory = ArdiumSE.GUI_FACTORY)
public class ArdiumSE {
    public static final String GUI_FACTORY = "com.ardium.pvp.client.gui.GuiFactoryArdiumSE";
    public static final String MOD_ID = "ardiumse";
    public static final CreativeTabs TAB_ARDIUM_SE = new TabArdiumSE ("ardiumSETab");
    private static final String COMMON_PROXY = "com.ardium.pvp.common.CommonProxy";
    private static final String CLIENT_PROXY = "com.ardium.pvp.client.ClientProxy";
    @Mod.Instance(value = ArdiumSE.MOD_ID)
    public static ArdiumSE instance;
    @SidedProxy(modId = MOD_ID, clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    private static CommonProxy commonProxy;

    @Mod.EventHandler
    public void preInitialization (final FMLPreInitializationEvent preInitializationEvent) {
        commonProxy.preInitialization (preInitializationEvent);
    }

    @Mod.EventHandler
    public void initialization (final FMLInitializationEvent initializationEvent) {
        commonProxy.initialization (initializationEvent);
    }

    @Mod.EventHandler
    public void postInitialization (final FMLPostInitializationEvent postInitializationEvent) {
        commonProxy.postInitialization (postInitializationEvent);
    }
}
