package com.ardium.pvp.common.inventory;

import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArdiumWorkbench extends Container {
    private final TileEntityArdiumWorkbench tileEntityArdiumWorkbench;

    public ContainerArdiumWorkbench (final TileEntityArdiumWorkbench tileEntityArdiumWorkbench, InventoryPlayer inventoryPlayer) {
        this.tileEntityArdiumWorkbench = tileEntityArdiumWorkbench;
        tileEntityArdiumWorkbench.openInventory ();
        tileEntityArdiumWorkbench.setAllowedItems ();
        this.addSlotToContainer (new Slot (tileEntityArdiumWorkbench, 0, 187, 33) {
            @Override
            public boolean isItemValid (ItemStack itemStack) {
                return TileEntityArdiumWorkbench.ALLOWED_ITEMS.contains (itemStack.getItem ());
            }
        });
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
            if ( !(TileEntityArdiumWorkbench.ALLOWED_ITEMS.contains (itemStackInSlot.getItem ())) ) {
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

    @Override
    public void detectAndSendChanges () {
        super.detectAndSendChanges ();
    }
}