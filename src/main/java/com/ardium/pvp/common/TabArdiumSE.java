package com.ardium.pvp.common;

import com.ardium.pvp.common.init.ItemsRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabArdiumSE extends CreativeTabs {
    public TabArdiumSE(String tabArdiumSE) {
        super (CreativeTabs.getNextID (), tabArdiumSE);
        this.setBackgroundImageName ("item_search.png");
    }

    public Item getTabIconItem() {
        return ItemsRegister.ardium;
    }

    public boolean hasSearchBar() {
        return true;
    }
}