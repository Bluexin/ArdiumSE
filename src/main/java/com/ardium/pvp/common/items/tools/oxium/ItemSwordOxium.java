package com.ardium.pvp.common.items.tools.oxium;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.blocks.BlockWebObsidian;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSwordOxium extends ItemSword {
    public ItemSwordOxium (ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("oxiumSword");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }

    @Override
    public float func_150893_a (ItemStack itemStack, Block block) {
        if ( block instanceof BlockWeb || block instanceof BlockWebObsidian ) {
            return 15.0F;
        } else {
            Material material = block.getMaterial ();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }

}