package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.IBlockAccess;

public class BlockArdium extends BlockCompressed {
    public BlockArdium() {
        super (MapColor.purpleColor);
        this.setBlockName ("blockArdium");
        this.setBlockTextureName ("ardiumse:" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        this.setHarvestLevel ("pickaxe", 3);
    }

    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return worldObj.getBlock (x, y, z) instanceof BlockArdium;
    }
}