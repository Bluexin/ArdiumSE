package com.ardium.pvp.common.items.tools.oxium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemAxe;

public class ItemAxeOxium extends ItemAxe {
    public ItemAxeOxium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("oxiumAxe");
        this.setTextureName ("ardiumse:" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}