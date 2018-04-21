package com.ardium.pvp.common.items.tools.oxium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemSword;

public class ItemSwordOxium extends ItemSword {
    public ItemSwordOxium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("oxiumSword");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}