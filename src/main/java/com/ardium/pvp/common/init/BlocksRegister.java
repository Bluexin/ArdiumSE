package com.ardium.pvp.common.init;

import com.ardium.pvp.common.blocks.BlockArdium;
import com.ardium.pvp.common.blocks.BlockContainerArdiumWorkbench;
import com.ardium.pvp.common.blocks.BlockOreArdium;
import com.ardium.pvp.common.blocks.BlockWebObsidian;
import com.ardium.pvp.common.blocks.stairs.BlockStairsObsidian;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockStairs;

public class BlocksRegister {
    public static BlockOreArdium oreArdium;
    public static BlockArdium blockArdium;
    public static Block webObsidian;
    private static BlockContainer blockArdiumWorkbench;
    private static BlockStairs blockStairsObsidian;

    public static void initialization () {
        oreArdium = new BlockOreArdium ();
        blockArdium = new BlockArdium ();
        blockArdiumWorkbench = new BlockContainerArdiumWorkbench ();
        blockStairsObsidian = new BlockStairsObsidian ();
        webObsidian = new BlockWebObsidian ();
        register ();
    }

    private static void register () {
        registerBlocks (oreArdium);
        registerBlocks (blockArdium);
        registerBlocks (blockArdiumWorkbench);
        registerBlocks (blockStairsObsidian);
        registerBlocks (webObsidian);
    }

    private static void registerBlocks (Block block) {
        GameRegistry.registerBlock (block, block.getUnlocalizedName ().substring (5));
    }
}