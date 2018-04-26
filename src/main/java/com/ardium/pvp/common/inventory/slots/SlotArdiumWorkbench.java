package com.ardium.pvp.common.inventory.slots;

import com.ardium.pvp.common.inventory.ContainerArdiumWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotArdiumWorkbench extends Slot {
    public SlotArdiumWorkbench (IInventory inventory, int slotIndex, int xPos, int yPos) {
        super (inventory, slotIndex, xPos, yPos);
    }

    @Override
    public boolean isItemValid (ItemStack itemStack) {
        return ContainerArdiumWorkbench.ALLOWED_ITEMS.contains (itemStack.getItem ());
    }
}
