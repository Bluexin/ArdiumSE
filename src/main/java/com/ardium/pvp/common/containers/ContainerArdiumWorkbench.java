package com.ardium.pvp.common.containers;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.items.ItemArdium;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerArdiumWorkbench extends Container {
    private final TileEntityArdiumWorkbench tileEntityArdiumWorkbench;

    public ContainerArdiumWorkbench(TileEntityArdiumWorkbench tileEntity, InventoryPlayer inventoryPlayer) {
        this.tileEntityArdiumWorkbench = tileEntity;
        tileEntity.openInventory ();
        /*
        guiArdiumWorkbench
        this.addSlotToContainer (new Slot (tileEntity, 0, 205, 31) {
            @Override
            public boolean isItemValid(ItemStack itemStack) {
                return itemStack.getItem () instanceof ItemArdium
                        || itemStack.getItem () == Item.getItemFromBlock (BlocksRegister.blockArdium);
            }
        });
        */

        /* guiArdiumWorkbench2 */
        this.addSlotToContainer (new Slot (tileEntity, 0, 187, 33) {


            @Override
            public boolean isItemValid(ItemStack itemStack) {
                return itemStack != null && itemStack.getItem () != null &&
                        (itemStack.getItem () instanceof ItemArdium
                                || itemStack.getItem () == Item.getItemFromBlock (BlocksRegister.blockArdium));
            }
        });
        this.bindPlayerInventory (inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileEntityArdiumWorkbench.isUseableByPlayer (player);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        /*
        private int numRows = inventoryPlayer.getSizeInventory() / 9; => Number of player inventory lines available
        I want 3 lines
         */
        //int i = -18;
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
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed (player);
        this.tileEntityArdiumWorkbench.closeInventory ();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = ((Slot) this.inventorySlots.get (slotIndex));
        if ( slot != null && slot.getHasStack () ) {
            if ( slotIndex < this.tileEntityArdiumWorkbench.getSizeInventory () ) {
                ItemStack itemStackInSlot = slot.getStack ();
                itemStack = itemStackInSlot.copy ();
                if ( !this.mergeItemStack (itemStackInSlot, this.tileEntityArdiumWorkbench.getSizeInventory (), this.inventorySlots.size (),
                        true) ) {
                    return null;
                } else if ( !this.mergeItemStack (itemStackInSlot, 0, this.tileEntityArdiumWorkbench.getSizeInventory (), false) ) {
                    return null;
                }
                if ( itemStackInSlot.stackSize == 0 ) {
                    slot.putStack (null);
                } else {
                    slot.onSlotChanged ();
                }
            }
        }
        return itemStack;
    }
}