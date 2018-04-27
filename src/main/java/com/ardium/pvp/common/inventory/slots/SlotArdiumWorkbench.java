package com.ardium.pvp.common.inventory.slots;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static com.ardium.pvp.common.init.ItemsRegister.*;

public class SlotArdiumWorkbench extends Slot {
    public static final Set < Item > ALLOWED_ITEMS = new HashSet < Item > ();
    private final TileEntityArdiumWorkbench tileEntityArdiumWorkbench;

    public SlotArdiumWorkbench (TileEntityArdiumWorkbench tileEntityArdiumWorkbench, int slotIndex, int xPos, int yPos) {
        super (tileEntityArdiumWorkbench, slotIndex, xPos, yPos);
        this.tileEntityArdiumWorkbench = tileEntityArdiumWorkbench;
    }

    @Override
    public boolean isItemValid (ItemStack itemStack) {
        return ALLOWED_ITEMS.contains (itemStack.getItem ());
    }

    @Override
    public void onSlotChanged () {
        super.onSlotChanged ();
        tileEntityArdiumWorkbench.calculateArdiumStoredAmount ();
    }

    public void setAllowedItems () {
        ALLOWED_ITEMS.add (ardium);
        ALLOWED_ITEMS.add (ardiumHelmet);
        ALLOWED_ITEMS.add (ardiumChestplate);
        ALLOWED_ITEMS.add (ardiumLeggings);
        ALLOWED_ITEMS.add (ardiumBoots);
        ALLOWED_ITEMS.add (ardiumSword);
        ALLOWED_ITEMS.add (ardiumShovel);
        ALLOWED_ITEMS.add (ardiumPickaxe);
        ALLOWED_ITEMS.add (ardiumAxe);
        ALLOWED_ITEMS.add (ardiumMultiTools);
        ALLOWED_ITEMS.add (Item.getItemFromBlock (BlocksRegister.blockArdium));
        ALLOWED_ITEMS.add (Item.getItemFromBlock (BlocksRegister.oreArdium));
    }
}
