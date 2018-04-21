package com.ardium.pvp.common.items.tools.ardium;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.ItemSpade;

public class ItemSpadeArdium extends ItemSpade {
    public ItemSpadeArdium(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.setUnlocalizedName ("ardiumShovel");
        this.setTextureName ("ardiumse:" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }
}
