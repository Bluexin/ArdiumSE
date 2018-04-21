package com.ardium.pvp.common.items.tools.ardium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeArdium extends ItemPickaxe {
    public ItemPickaxeArdium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("ardiumPickaxe");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}