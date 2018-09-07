package com.ardium.pvp.common.tileentity;

import com.ardium.pvp.common.init.BlocksRegister;
import com.ardium.pvp.common.init.ItemsRegister;
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

import java.util.HashSet;
import java.util.Set;

import static com.ardium.pvp.common.init.ItemsRegister.*;

public class TileEntityArdiumWorkbench extends TileEntity implements IInventory {
    public static final String KEY_ARDIUM_STORED_AMOUNT = "ArdiumStoredAmount";
    public static final Set <Item> ALLOWED_ITEMS = new HashSet <Item> ();
    public static final String KEY_ARDIUM_CONTAINER_CUSTOM_NAME = "ArdiumWorkbenchContainerCustomName";
    public int ardiumStoredAmount;
    public String ardiumWorkbenchContainerCustomName;
    public ItemStack[] ardiumWorkbenchContent = new ItemStack[1];

    @Override
    public boolean hasCustomInventoryName () {
        return this.ardiumWorkbenchContainerCustomName != null
                && !(this.ardiumWorkbenchContainerCustomName.isEmpty ());
    }

    @Override
    public boolean isItemValidForSlot (int slotIndex, ItemStack itemStack) {
        if ( itemStack != null && itemStack.getItem () != null && ALLOWED_ITEMS.contains (itemStack.getItem ()) ) {
            itemStack = this.ardiumWorkbenchContent[slotIndex];
            return true;
        }
        return false;
    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity (this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityArdiumWorkbench
                && entityPlayer.getDistanceSq (
                ( double ) this.xCoord + 0.5D,
                ( double ) this.yCoord + 0.5D,
                ( double ) this.zCoord + 0.5D) <= 128.0D;
    }

    public int getArdiumStoredAmount () {
        return ardiumStoredAmount;
    }

    /**
     * this is a method which defines which items are allowed to go into the ardium workbench to prevent the player from
     * putting random/unintended items inside and avoid possible problems
     */
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

    @Override
    public int getInventoryStackLimit () {
        return 64;
    }

    @Override
    public int getSizeInventory () {
        return this.ardiumWorkbenchContent.length;
    }

    private void addCurrentArdiumStoredAmount (int amount) {
        this.ardiumStoredAmount += amount;
    }

    public void calculateArdiumStoredAmount () {

        if ( this.ardiumWorkbenchContent[0] == null
                || this.ardiumWorkbenchContent[0].getItem () == null
                || this.ardiumWorkbenchContent[0].stackSize <= 0 ) {
            return;
        }

        if ( this.ardiumWorkbenchContent[0].getItem () instanceof ItemArdium ) {
            addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize);
        } else if ( this.ardiumWorkbenchContent[0].getItem () instanceof ItemArmorArdium ) {
            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumHelmet ) {
                addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 5);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumChestplate ) {
                addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 8);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumLeggings ) {
                addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 7);
            }

            if ( this.ardiumWorkbenchContent[0].getItem () == ItemsRegister.ardiumBoots ) {
                addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 4);
            }
        } else if ( this.ardiumWorkbenchContent[0].getItem () == Item.getItemFromBlock (BlocksRegister.blockArdium) ) {
            addCurrentArdiumStoredAmount (this.ardiumWorkbenchContent[0].stackSize * 9);
        }
    }

    @Override
    public void closeInventory () {

    }

    @Override
    public void openInventory () {

    }

    @Override
    public void readFromNBT (NBTTagCompound nbtTagCompound) {
        super.readFromNBT (nbtTagCompound);
        if ( nbtTagCompound.hasKey (KEY_ARDIUM_CONTAINER_CUSTOM_NAME, Constants.NBT.TAG_STRING) ) {
            this.ardiumWorkbenchContainerCustomName = nbtTagCompound.getString (KEY_ARDIUM_CONTAINER_CUSTOM_NAME);
        }

        NBTTagList nbtTagList = nbtTagCompound.getTagList ("Items", Constants.NBT.TAG_COMPOUND);
        this.ardiumWorkbenchContent = new ItemStack[this.getSizeInventory ()];
        for (int i = 0; i < nbtTagList.tagCount (); ++i) {
            NBTTagCompound nbtTagCompoundInSlot = nbtTagList.getCompoundTagAt (i);
            int itemStackSlotIndex = nbtTagCompoundInSlot.getByte ("Slot") & 255;

            if ( itemStackSlotIndex < this.ardiumWorkbenchContent.length && this.ardiumWorkbenchContent[itemStackSlotIndex] != null ) {
                this.ardiumWorkbenchContent[itemStackSlotIndex] = ItemStack.loadItemStackFromNBT (nbtTagCompoundInSlot);
            }
        }

        if ( nbtTagCompound.hasKey (KEY_ARDIUM_STORED_AMOUNT, Constants.NBT.TAG_INT) ) {
            this.ardiumStoredAmount = nbtTagCompound.getInteger (KEY_ARDIUM_STORED_AMOUNT);
        }
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
    public void updateEntity () {
        if ( ardiumWorkbenchContent[0] != null && ardiumWorkbenchContent[0].getItem () != null ) {
            if ( isItemValidForSlot (0, ardiumWorkbenchContent[0]) ) {
                calculateArdiumStoredAmount ();
                System.out.println (ardiumStoredAmount);
                //decrStackSize (0, ardiumWorkbenchContent[0].stackSize);
            }
        }
    }

    @Override
    public void writeToNBT (NBTTagCompound nbtTagCompound) {
        super.writeToNBT (nbtTagCompound);
        if ( this.hasCustomInventoryName () ) {
            nbtTagCompound.setString (KEY_ARDIUM_CONTAINER_CUSTOM_NAME, this.ardiumWorkbenchContainerCustomName);
        }
        NBTTagList nbtTagList = new NBTTagList ();
        for (int i = 0; i < ardiumWorkbenchContent.length; ++i) {
            NBTTagCompound nbtTagSlot = new NBTTagCompound ();
            nbtTagSlot.setByte ("Slot", (( byte ) i));
            if ( this.ardiumWorkbenchContent[i] != null ) this.ardiumWorkbenchContent[i].writeToNBT (nbtTagSlot);
            nbtTagList.appendTag (nbtTagSlot);
        }
        nbtTagCompound.setTag ("Items", nbtTagList);
        if ( ardiumStoredAmount > 0 ) nbtTagCompound.setInteger (KEY_ARDIUM_STORED_AMOUNT, this.ardiumStoredAmount);
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
                if ( this.ardiumWorkbenchContent[slotIndex].stackSize <= 0 ) {
                    this.ardiumWorkbenchContent[slotIndex] = null;
                }
                this.markDirty ();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlot (int slotIndex) {
        return this.ardiumWorkbenchContent[slotIndex];
    }

    @Override
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
        return this.hasCustomInventoryName () ? this.ardiumWorkbenchContainerCustomName : "container.ardiumWorkbench.name";
    }
}