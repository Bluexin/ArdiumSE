package com.ardium.pvp.common.items.tools.ardium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemSword;

public class ItemSwordArdium extends ItemSword {
    public ItemSwordArdium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("ardiumSword");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}