package com.ardium.pvp.common.tileentities;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.items.ItemArdium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityArdiumWorkbench extends TileEntity implements IInventory {
    private ItemStack[] ardiumWorkbenchContent = new ItemStack[1];
    private String ardiumWorkbenchContainerCustomName;

    @Override
    public boolean hasCustomInventoryName() {
        return this.ardiumWorkbenchContainerCustomName != null
                && !(this.ardiumWorkbenchContainerCustomName.isEmpty ()
                && !("".equalsIgnoreCase (ardiumWorkbenchContainerCustomName)));
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        itemStack = this.ardiumWorkbenchContent[slotIndex];
        return itemStack != null
                && itemStack.getItem () == Item.getItemFromBlock (BlocksRegister.blockArdium)
                && itemStack.getItem () instanceof ItemArdium;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity (this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityArdiumWorkbench
                && entityPlayer.getDistanceSq (
                (double) this.xCoord + 0.5D,
                (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 128.0D;
    }

    @Override
    public void closeInventory() {
    }

    public void setArdiumWorkbenchContainerCustomName(String ardiumWorkbenchContainerCustomName) {
        this.ardiumWorkbenchContainerCustomName = ardiumWorkbenchContainerCustomName;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        this.ardiumWorkbenchContent[slotIndex] = itemStack;
        if ( itemStack != null && itemStack.stackSize > this.getInventoryStackLimit () ) {
            itemStack.stackSize = this.getInventoryStackLimit ();
        }
        this.markDirty ();
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT (nbtTagCompound);

        if ( nbtTagCompound.hasKey ("ArdiumWorkbenchContainerCustomName", Constants.NBT.TAG_STRING) ) {
            this.ardiumWorkbenchContainerCustomName = nbtTagCompound.getString ("ArdiumWorkbenchContainerCustomName");
        }

        NBTTagList nbttaglist = nbtTagCompound.getTagList ("Items", Constants.NBT.TAG_COMPOUND);
        this.ardiumWorkbenchContent = new ItemStack[this.getSizeInventory ()];

        for (int i = 0; i < nbttaglist.tagCount (); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt (i);
            int j = nbttagcompound1.getByte ("Slot") & 255;

            if ( j < this.ardiumWorkbenchContent.length ) {
                this.ardiumWorkbenchContent[j] = ItemStack.loadItemStackFromNBT (nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT (nbtTagCompound);
        if ( this.hasCustomInventoryName () ) {
            nbtTagCompound.setString ("ArdiumWorkbenchContainerCustomName", this.ardiumWorkbenchContainerCustomName);
        }

        NBTTagList nbttaglist = new NBTTagList ();

        for (int i = 0; i < this.ardiumWorkbenchContent.length; ++i) {
            if ( this.ardiumWorkbenchContent[i] != null ) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound ();
                nbttagcompound1.setByte ("Slot", (byte) i);
                this.ardiumWorkbenchContent[i].writeToNBT (nbttagcompound1);
                nbttaglist.appendTag (nbttagcompound1);
            }
        }

        nbtTagCompound.setTag ("Items", nbttaglist);
    }

    @Override
    public int getSizeInventory() {
        return this.ardiumWorkbenchContent.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return this.ardiumWorkbenchContent[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount) {
        if ( this.ardiumWorkbenchContent[slotIndex] != null ) {
            ItemStack itemstack;
            if ( this.ardiumWorkbenchContent[slotIndex].stackSize <= amount ) {
                itemstack = this.ardiumWorkbenchContent[slotIndex];
                this.ardiumWorkbenchContent[slotIndex] = null;
                this.markDirty ();
                return itemstack;
            } else {
                itemstack = this.ardiumWorkbenchContent[slotIndex].splitStack (amount);

                if ( this.ardiumWorkbenchContent[slotIndex].stackSize == 0 ) {
                    this.ardiumWorkbenchContent[slotIndex] = null;
                }

                this.markDirty ();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if ( this.ardiumWorkbenchContent[slotIndex] != null ) {
            ItemStack itemstack = this.ardiumWorkbenchContent[slotIndex];
            this.ardiumWorkbenchContent[slotIndex] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName () ? this.ardiumWorkbenchContainerCustomName
                : "container.ardiumWorkbench.name";
    }


    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

}