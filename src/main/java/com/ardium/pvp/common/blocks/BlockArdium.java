package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.IBlockAccess;

public class BlockArdium extends BlockCompressed {

    public BlockArdium() {
        super (MapColor.purpleColor);
        this.setBlockName ("blockArdium");
        this.setBlockTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        this.setHarvestLevel ("pickaxe", 3);
    }

    @Override
    public boolean isBeaconBase (IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return world.getBlock (x, y, z) instanceof BlockArdium;
    }
}