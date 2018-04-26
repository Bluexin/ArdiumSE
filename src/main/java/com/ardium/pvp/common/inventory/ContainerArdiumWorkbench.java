package com.ardium.pvp.common.inventory;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.init.ItemsRegister;
import com.ardium.pvp.common.inventory.slots.SlotArdiumWorkbench;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static com.ardium.pvp.common.init.ItemsRegister.ardium;

public class ContainerArdiumWorkbench extends Container {
    public static final Set < Item > ALLOWED_ITEMS = new HashSet < Item > ();
    private final TileEntityArdiumWorkbench tileEntityArdiumWorkbench;

    public ContainerArdiumWorkbench (TileEntityArdiumWorkbench tileEntityArdiumWorkbench, InventoryPlayer inventoryPlayer) {
        this.tileEntityArdiumWorkbench = tileEntityArdiumWorkbench;
        tileEntityArdiumWorkbench.openInventory ();
        setAllowedItems ();
        this.addSlotToContainer (new SlotArdiumWorkbench (tileEntityArdiumWorkbench, 0, 187, 33));
        this.bindPlayerInventory (inventoryPlayer);
    }

    @Override
    public boolean canInteractWith (EntityPlayer player) {
        return this.tileEntityArdiumWorkbench.isUseableByPlayer (player);
    }

    private void bindPlayerInventory (InventoryPlayer inventoryPlayer) {
        int j;
        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer (new Slot (inventoryPlayer, k + j * 9 + 9, 36 + k * 18, 137 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer (new Slot (inventoryPlayer, j, 36 + j * 18, 195));
        }
    }

    private void setAllowedItems () {
        ALLOWED_ITEMS.add (ardium);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumHelmet);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumChestplate);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumLeggings);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumBoots);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumSword);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumShovel);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumPickaxe);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumAxe);
        ALLOWED_ITEMS.add (ItemsRegister.ardiumMultiTools);
        ALLOWED_ITEMS.add (Item.getItemFromBlock (BlocksRegister.blockArdium));
        ALLOWED_ITEMS.add (Item.getItemFromBlock (BlocksRegister.oreArdium));
    }

    @Override
    public void onContainerClosed (EntityPlayer player) {
        super.onContainerClosed (player);
        this.tileEntityArdiumWorkbench.closeInventory ();
    }


    @Override
    public ItemStack transferStackInSlot (EntityPlayer player, int slotIndex) {
        ItemStack itemStackResult = null;
        Slot slot = ((Slot) this.inventorySlots.get (slotIndex));

        if ( slot != null && slot.getHasStack () ) {
            ItemStack itemStackInSlot = slot.getStack (); // The ItemStack we are clicking on
            itemStackResult = itemStackInSlot.copy (); //The ItemStack placed in the slot
            if ( !(ALLOWED_ITEMS.contains (itemStackInSlot.getItem ())) ) {
                return null;
            }

            if ( slotIndex < this.tileEntityArdiumWorkbench.getSizeInventory () ) {
                if ( !(this.mergeItemStack (itemStackInSlot, tileEntityArdiumWorkbench.getSizeInventory (),
                        this.inventorySlots.size (), false)) ) {
                    return null;
                }
            } else if ( !this.mergeItemStack (itemStackInSlot, 0, this.tileEntityArdiumWorkbench.getSizeInventory (), false) ) {
                System.out.println ("Inventory is full !");
                return null;
            }

            if ( itemStackInSlot.stackSize <= 0 ) {
                slot.putStack (null);
            } else {
                slot.onSlotChanged ();
            }
        }
        return itemStackResult;
    }
}