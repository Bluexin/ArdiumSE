package com.ardium.pvp.common.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHelper {

    public static boolean equalsIngoreStackSize (ItemStack itemStack1, ItemStack itemStack2) {
        if ( itemStack1 != null && itemStack2 != null ) {
            if ( Item.getIdFromItem (itemStack1.getItem ()) - Item.getIdFromItem (itemStack2.getItem ()) == 0
                    && Item.getIdFromItem (itemStack1.getItem ()) > 0
                    && Item.getIdFromItem (itemStack2.getItem ()) > 0 ) {
                if ( itemStack1.getItem () == itemStack2.getItem () ) {
                    if ( itemStack1.getItemDamage () == itemStack2.getItemDamage () ) {
                        if ( itemStack1.hasTagCompound () && itemStack2.hasTagCompound () ) {
                            if ( ItemStack.areItemStackTagsEqual (itemStack1, itemStack2) ) {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
