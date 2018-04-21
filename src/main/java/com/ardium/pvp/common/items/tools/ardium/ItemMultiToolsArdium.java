package com.ardium.pvp.common.items.tools.ardium;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.items.tools.ItemMultiTools;

public class ItemMultiToolsArdium extends ItemMultiTools {
    public ItemMultiToolsArdium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("ardiumMultiTools");
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}