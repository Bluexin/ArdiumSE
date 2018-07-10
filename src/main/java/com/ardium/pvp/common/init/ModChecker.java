package com.ardium.pvp.common.init;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

public class ModChecker {
    public static boolean isArdiumSELoaded;
    public static boolean isBotaniaLoaded;
    public static boolean isCoFHCoreLoaded;
    public static boolean isExtraUtilitiesLoaded;
    public static boolean isEnhancedVanillaLoaded;
    public static boolean isIronChestsLoaded;
    public static boolean isInventoryTweaksLoaded;
    public static boolean isMagicalCropsLoaded;
    public static boolean isMantleLoaded;
    public static boolean isNotEnoughItemsLoaded;
    public static boolean isOpenModsLibLoaded;
    public static boolean isOpenBlocksLoaded;
    public static boolean isOptifineLoaded;
    public static boolean isTinkersConstructLoaded;
    public static boolean isRandomThingsLoaded;
    public static boolean isStorageDrawersLoaded;
    public static boolean isWailaLoaded;

    public ModChecker () {
        isArdiumSELoaded = Loader.isModLoaded ("ardiumse");
        isBotaniaLoaded = Loader.isModLoaded ("botania");
        isCoFHCoreLoaded = Loader.isModLoaded ("CoFHCore");
        isExtraUtilitiesLoaded = Loader.isModLoaded ("ExtraUtilities");
        isEnhancedVanillaLoaded = Loader.isModLoaded ("enhancedvanilla");
        isIronChestsLoaded = Loader.isModLoaded ("IronChest");
        isMantleLoaded = Loader.isModLoaded ("Mantle");
        isNotEnoughItemsLoaded = Loader.isModLoaded ("NotEnoughItems");
        isInventoryTweaksLoaded = Loader.isModLoaded ("inventorytweaks");
        isOpenModsLibLoaded = Loader.isModLoaded ("OpenMods");
        isOpenBlocksLoaded = Loader.isModLoaded ("OpenBlocks");
        isOptifineLoaded = Loader.isModLoaded ("Optifine");
        isTinkersConstructLoaded = Loader.isModLoaded ("TConstruct");
        isRandomThingsLoaded = Loader.isModLoaded ("RandomThings");
        isStorageDrawersLoaded = Loader.isModLoaded ("StorageDrawers");
        isWailaLoaded = Loader.isModLoaded ("Waila");
    }

    public static void checkIfModsAreLoaded () {

        if ( isArdiumSELoaded ) {
            FMLLog.getLogger ().info ("Ardium : Special Edition Detected and Loaded !");
        }

        if ( isBotaniaLoaded ) {
            FMLLog.getLogger ().info ("Botania Detected and Loaded !");
        }

        if ( isCoFHCoreLoaded ) {
            FMLLog.getLogger ().info ("CoFHCore Detected and Loaded !");
        }

        if ( isExtraUtilitiesLoaded ) {
            FMLLog.getLogger ().info ("Extra Utilities Detected and Loaded !");
        }

        if ( isEnhancedVanillaLoaded ) {
            FMLLog.getLogger ().info ("Enhanced Vanilla Detected and Loaded !");
        }

        if ( isIronChestsLoaded ) {
            FMLLog.getLogger ().info ("IronChest Detected and Loaded !");
        }

        if ( isInventoryTweaksLoaded ) {
            FMLLog.getLogger ().info ("InventoryTweaks Detected and Loaded");
        }

        if ( isMantleLoaded ) {
            FMLLog.getLogger ().info ("Mantle Detected and Loaded !");

            if ( isTinkersConstructLoaded ) {
                FMLLog.getLogger ().info ("Tinkers' Construct Detected and Loaded !");
            }
        }

        if ( isMagicalCropsLoaded ) {
            FMLLog.getLogger ().info ("MagicalCrops Detected and Loaded !");
        }

        if ( isOpenModsLibLoaded ) {
            FMLLog.getLogger ().info ("OpenMods Lib Detected and Loaded !");
            if ( isOpenBlocksLoaded ) {
                FMLLog.getLogger ().info ("OpenBlocks Detected and Loaded !");
            }
        }

        if ( isOptifineLoaded ) {
            FMLLog.getLogger ().info ("Optifine Detected and Loaded !");
        }

        if ( isRandomThingsLoaded ) {
            FMLLog.getLogger ().info ("RandomThings (Mod) Detected and Loaded !");
        }

        if ( isStorageDrawersLoaded ) {
            FMLLog.getLogger ().info ("StorageDrawers Detected and Loaded !");
        }

        if ( isWailaLoaded ) {
            FMLLog.getLogger ().info ("W.A.I.L.A. (What Am I Looking At) Detected and Loaded !");
        }
    }
}