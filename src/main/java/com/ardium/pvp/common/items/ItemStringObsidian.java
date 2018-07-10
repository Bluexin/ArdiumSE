package com.ardium.pvp.common.items;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.Item;

public class ItemStringObsidian extends Item {
    public ItemStringObsidian () {
        setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        setUnlocalizedName ("stringObsidian");
        setTextureName (ArdiumSE.MOD_ID + ":" + getUnlocalizedName ().substring (5));
    }
}
