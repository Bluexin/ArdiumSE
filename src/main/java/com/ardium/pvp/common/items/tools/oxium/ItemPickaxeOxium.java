package com.ardium.pvp.common.items.tools.oxium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeOxium extends ItemPickaxe {
    public ItemPickaxeOxium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("oxiumPickaxe");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}