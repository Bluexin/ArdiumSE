package com.ardium.pvp.common.items.tools.oxium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemSpade;

public class ItemSpadeOxium extends ItemSpade {
    public ItemSpadeOxium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("oxiumShovel");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}