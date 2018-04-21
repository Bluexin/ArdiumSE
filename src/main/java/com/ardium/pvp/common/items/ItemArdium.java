package com.ardium.pvp.common.items;

import com.ardium.pvp.ArdiumSE;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArdium extends Item {
    public ItemArdium() {
        this.setUnlocalizedName ("ardium");
        this.setTextureName ("ardiumse:" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }

    public boolean isBeaconPayment(ItemStack stack) {
        return stack.getItem () instanceof ItemArdium;
    }
}