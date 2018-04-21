package com.ardium.pvp.common.containers;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.init.ItemsRegister;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class ContainerArdiumWorkbench extends Container {
    private final TileEntityArdiumWorkbench tileEntityArdiumWorkbench;
    private Set<Item> allowedItems = new HashSet<> ();

    public ContainerArdiumWorkbench(TileEntityArdiumWorkbench tileEntity, InventoryPlayer inventoryPlayer) {
        this.tileEntityArdiumWorkbench = tileEntity;
        tileEntity.openInventory ();
        setAllowedItems ();
        this.addSlotToContainer (new Slot (tileEntity, 0, 187, 33) {
            @Override
            public boolean isItemValid(ItemStack itemStack) {
                return allowedItems.contains (itemStack.getItem ());
            }
        });
        this.bindPlayerInventory (inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileEntityArdiumWorkbench.isUseableByPlayer (player);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
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

    private void setAllowedItems() {
        allowedItems.add (ItemsRegister.ardium);
        allowedItems.add (ItemsRegister.ardiumHelmet);
        allowedItems.add (ItemsRegister.ardiumChestplate);
        allowedItems.add (ItemsRegister.ardiumLeggings);
        allowedItems.add (ItemsRegister.ardiumBoots);
        allowedItems.add (ItemsRegister.ardiumSword);
        allowedItems.add (ItemsRegister.ardiumShovel);
        allowedItems.add (ItemsRegister.ardiumPickaxe);
        allowedItems.add (ItemsRegister.ardiumAxe);
        allowedItems.add (ItemsRegister.ardiumMultiTools);
        allowedItems.add (Item.getItemFromBlock (BlocksRegister.blockArdium));
        allowedItems.add (Item.getItemFromBlock (BlocksRegister.oreArdium));
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed (player);
        this.tileEntityArdiumWorkbench.closeInventory ();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = ((Slot) this.inventorySlots.get (slotIndex));
        if ( slot != null && slot.getHasStack () ) {
            ItemStack itemStack1 = slot.getStack ();
            itemStack = itemStack1.copy ();
            if ( !(allowedItems.contains (itemStack1.getItem ())) ) {
                return null;
            }

            if ( slotIndex < this.tileEntityArdiumWorkbench.getSizeInventory () ) {
                if ( !(this.mergeItemStack (itemStack1, tileEntityArdiumWorkbench.getSizeInventory (),
                        this.inventorySlots.size (), false)) ) {
                    return null;
                }
            } else if ( !this.mergeItemStack (itemStack1, 0, this.tileEntityArdiumWorkbench.getSizeInventory (), false) ) {
                return null;
            }

            if ( itemStack1.stackSize == 0 ) {
                slot.putStack (null);
            } else {
                slot.onSlotChanged ();
            }
        }
        return itemStack;
    }
}