package com.ardium.pvp.common.tileentity;

import com.ardium.pvp.common.blocks.BlockContainerArdiumWorkbench;
import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.init.ItemsRegister;
import com.ardium.pvp.common.inventory.slots.SlotArdiumWorkbench;
import com.ardium.pvp.common.items.ItemArdium;
import com.ardium.pvp.common.items.armors.ItemArmorArdium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityArdiumWorkbench extends TileEntity implements IInventory {
    public static final String KEY_ARDIUM_STORED_AMOUNT = "ArdiumStoredAmount";
    private int ardiumStoredAmount;
    //private int tickCounter;
    private ItemStack[] ardiumWorkbenchContent = new ItemStack[1];
    private String ardiumWorkbenchContainerCustomName;

    @Override
    public boolean hasCustomInventoryName () {
        return this.ardiumWorkbenchContainerCustomName != null
                && !(this.ardiumWorkbenchContainerCustomName.isEmpty ());
    }

    @Override
    public boolean isItemValidForSlot (int slotIndex, ItemStack itemStack) {
        itemStack = this.ardiumWorkbenchContent[slotIndex];
        return SlotArdiumWorkbench.ALLOWED_ITEMS.contains (itemStack.getItem ());
    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity (this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityArdiumWorkbench
                && entityPlayer.getDistanceSq (
                (double) this.xCoord + 0.5D,
                (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 128.0D;
    }

    public void calculateArdiumStoredAmount () {

        if ( this.ardiumWorkbenchContent[0] == null || this.ardiumWorkbenchContent[0].stackSize < 0 ) return;

        if ( this.ardiumWorkbenchContent[0].getItem () instanceof ItemArdium ) {
            setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize);
        } else if ( this.ardiumWorkbenchContent[0].getItem () instanceof ItemArmorArdium ) {
            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumHelmet ) {
                setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 5);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumChestplate ) {
                setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 8);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumLeggings ) {
                setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 7);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumBoots ) {
                setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 4);
            }
        } else if ( this.ardiumWorkbenchContent[0].getItem () == Item.getItemFromBlock (BlocksRegister.blockArdium) ) {
            setArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 9);
        } else {
            return;
        }
        decrStackSize (0, this.ardiumWorkbenchContent[0].stackSize);
        worldObj.notifyBlockChange (xCoord, yCoord, zCoord, new BlockContainerArdiumWorkbench ());
    }

    @Override
    public void closeInventory () {

    }

    @Override
    public void openInventory () {

    }

    @Override
    public void readFromNBT (NBTTagCompound nbtTagCompound) {

        if ( nbtTagCompound.hasKey ("ArdiumWorkbenchContainerCustomName", Constants.NBT.TAG_STRING) ) {
            this.ardiumWorkbenchContainerCustomName = nbtTagCompound.getString ("ArdiumWorkbenchContainerCustomName");
        }

        NBTTagList nbttaglist = nbtTagCompound.getTagList ("Items", Constants.NBT.TAG_COMPOUND);
        this.ardiumWorkbenchContent = new ItemStack[this.getSizeInventory ()];

        for (int i = 0; i < nbttaglist.tagCount (); ++i) {
            NBTTagCompound nbtTag = nbttaglist.getCompoundTagAt (i);
            int j = nbtTag.getByte ("Slot") & 255;
            if ( j < this.ardiumWorkbenchContent.length ) {
                this.ardiumWorkbenchContent[j] = ItemStack.loadItemStackFromNBT (nbtTag);
            }
        }

        if ( nbtTagCompound.hasKey (KEY_ARDIUM_STORED_AMOUNT, Constants.NBT.TAG_INT) ) {
            ardiumStoredAmount = nbtTagCompound.getInteger (KEY_ARDIUM_STORED_AMOUNT);
        }
        super.readFromNBT (nbtTagCompound);
        System.out.println (nbtTagCompound.toString ());
    }

    public void setArdiumWorkbenchContainerCustomName (String ardiumWorkbenchContainerCustomName) {
        this.ardiumWorkbenchContainerCustomName = ardiumWorkbenchContainerCustomName;
    }

    @Override
    public void setInventorySlotContents (int slotIndex, ItemStack itemStack) {
        this.ardiumWorkbenchContent[slotIndex] = itemStack;
        if ( itemStack != null && itemStack.stackSize > this.getInventoryStackLimit () ) {
            itemStack.stackSize = this.getInventoryStackLimit ();
        }
        this.markDirty ();
    }

    @Override
    public void writeToNBT (NBTTagCompound nbtTagCompound) {
        if ( this.hasCustomInventoryName () ) {
            nbtTagCompound.setString ("ArdiumWorkbenchContainerCustomName", this.ardiumWorkbenchContainerCustomName);
        }

        NBTTagList nbttaglist = new NBTTagList ();

        for (int i = 0; i < this.ardiumWorkbenchContent.length; ++i) {
            if ( this.ardiumWorkbenchContent[i] != null ) {
                NBTTagCompound nbtTag = new NBTTagCompound ();
                nbtTag.setByte ("Slot", (byte) i);
                this.ardiumWorkbenchContent[i].writeToNBT (nbtTag);
                nbttaglist.appendTag (nbtTag);
            }
        }
        nbtTagCompound.setTag ("Items", nbttaglist);

        nbtTagCompound.setInteger (KEY_ARDIUM_STORED_AMOUNT, ardiumStoredAmount);
        System.out.println ("Ardium Stored Amount : " + nbtTagCompound.getInteger (KEY_ARDIUM_STORED_AMOUNT));
        super.writeToNBT (nbtTagCompound);
        System.out.println (nbtTagCompound.toString ());
    }

    /*
    @Override
    public void updateEntity () {
        calculateArdiumStoredAmount ();
        super.updateEntity ();
    }
    */

    @Override
    public int getInventoryStackLimit () {
        return 64;
    }

    @Override
    public int getSizeInventory () {
        return this.ardiumWorkbenchContent.length;
    }

    public int getArdiumStoredAmount () {
        return ardiumStoredAmount;
    }

    public void setArdiumStoredAmount (int ardiumStoredAmount) {
        this.ardiumStoredAmount += ardiumStoredAmount;
    }

    public ItemStack[] getArdiumWorkbenchContent () {
        return ardiumWorkbenchContent;
    }

    @Override
    public ItemStack getStackInSlot (int slotIndex) {
        return this.ardiumWorkbenchContent[slotIndex];
    }

    @Override
    public ItemStack decrStackSize (int slotIndex, int amount) {
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

    public ItemStack getStackInSlotOnClosing (int slotIndex) {
        if ( this.ardiumWorkbenchContent[slotIndex] != null ) {
            ItemStack itemstack = this.ardiumWorkbenchContent[slotIndex];
            this.ardiumWorkbenchContent[slotIndex] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName () {
        return this.hasCustomInventoryName () ? this.ardiumWorkbenchContainerCustomName
                : "container.ardiumWorkbench.name";
    }
}