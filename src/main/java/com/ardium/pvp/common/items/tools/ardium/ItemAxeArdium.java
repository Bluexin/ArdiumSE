package com.ardium.pvp.common.items.tools.ardium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemAxe;

public class ItemAxeArdium extends ItemAxe {
    public ItemAxeArdium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("ardiumAxe");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}