package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOreArdium extends Block {
    public BlockOreArdium() {
        super (Material.rock);
        this.setBlockName ("oreArdium");
        this.setBlockTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        this.setHardness (4.0F);
        this.setHarvestLevel ("pickaxe", 3, 0);
        this.setResistance (6.0F);
    }
}