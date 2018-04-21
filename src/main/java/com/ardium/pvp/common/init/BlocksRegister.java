package com.ardium.pvp.common.init;

import com.ardium.pvp.common.blocks.BlockArdium;
import com.ardium.pvp.common.blocks.BlockContainerArdiumWorkbench;
import com.ardium.pvp.common.blocks.BlockOreArdium;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlocksRegister {
    public static BlockOreArdium oreArdium;
    public static BlockArdium blockArdium;
    private static Block blockArdiumWorkbench;

    public static void initialization() {
        oreArdium = new BlockOreArdium ();
        blockArdium = new BlockArdium ();
        blockArdiumWorkbench = new BlockContainerArdiumWorkbench ();
        register ();
    }

    private static void register() {
        registerBlocks (oreArdium);
        registerBlocks (blockArdium);
        registerBlocks (blockArdiumWorkbench);
    }

    private static void registerBlocks(Block block) {
        GameRegistry.registerBlock (block, block.getUnlocalizedName ().substring (5));
    }
}