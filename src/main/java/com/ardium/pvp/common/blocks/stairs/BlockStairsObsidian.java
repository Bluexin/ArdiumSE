package com.ardium.pvp.common.blocks.stairs;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;

public class BlockStairsObsidian extends BlockStairs {
    public BlockStairsObsidian () {
        super (Blocks.obsidian, 0);
        setBlockName ("blockStairsObsidian");
        setBlockTextureName ("minecraft:obsidian");
        setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        setHardness (50.0F);
        setResistance (2000.0F);
        setStepSound (soundTypePiston);
    }

    @Override
    public int getMobilityFlag () {
        return 2;
    }


}
