package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.client.ClientProxy;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockContainerArdiumWorkbench extends BlockContainer {

    public BlockContainerArdiumWorkbench () {
        super (Material.iron);
        setBlockBounds (0.0F, 0.0F, 0.0F, 1.0F, 0.58F, 1.0F);
        setBlockName ("blockArdiumWorkbench");
        setBlockTextureName (ArdiumSE.MOD_ID + ":" + getUnlocalizedName ().substring (5));
        setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        setHardness (5.0F);
        setHarvestLevel ("pickaxe", 3, 0);
        setResistance (10.0F);
        setLightOpacity (0);
        setStepSound (soundTypeMetal);
    }

    @Override
    public boolean hasTileEntity (int metadata) {
        return metadata == 0;
    }

    @Override
    public boolean isOpaqueCube () {
        return false;
    }

    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if ( !world.isRemote ) {
            //Add a configurable GUI ID later, preferably from an Enum with all the GUI IDs
            player.openGui (ArdiumSE.instance, 0 /*ArdiumSEConfiguration.ardiumWorkbenchGuiId*/, world, x, y, z);
        }
        return true;
    }

    @Override
    public boolean removedByPlayer (World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if ( willHarvest ) return true;
        return super.removedByPlayer (world, player, x, y, z, willHarvest);
    }

    @Override
    public boolean renderAsNormalBlock () {
        return false;
    }

    @Override
    public int getRenderType () {
        return ClientProxy.renderTESRId;
    }

    @Override
    public void harvestBlock (World world, EntityPlayer player, int x, int y, int z, int metadata) {
        super.harvestBlock (world, player, x, y, z, metadata);
        world.setBlockToAir (x, y, z);
    }

    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        if ( itemStack.getItemDamage () == 0 ) {
            TileEntity tileEntity = world.getTileEntity (x, y, z);
            if ( tileEntity instanceof TileEntityArdiumWorkbench && itemStack.hasDisplayName () ) {
                ((TileEntityArdiumWorkbench) tileEntity).setArdiumWorkbenchContainerCustomName (itemStack.getDisplayName ());
            }
        }
    }

    @Override
    public ArrayList < ItemStack > getDrops (World world, int x, int y, int z, int metadata, int fortune) {
        TileEntity tileEntity = world.getTileEntity (x, y, z);
        if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
            TileEntityArdiumWorkbench tileEntityArdiumWorkbench = ((TileEntityArdiumWorkbench) tileEntity);

            ArrayList < ItemStack > itemStacks = new ArrayList < ItemStack > ();
            int quantityDropped = this.quantityDropped (metadata, fortune, world.rand);

            for (int i = 0; i < quantityDropped; ++i) {
                Item item = this.getItemDropped (metadata, world.rand, fortune);
                if ( item != null ) {
                    ItemStack blockToDrop = new ItemStack (item, 1, this.damageDropped (metadata));

                    if ( !blockToDrop.hasTagCompound () ) {
                        blockToDrop.setTagCompound (new NBTTagCompound ());
                        blockToDrop.stackTagCompound = blockToDrop.getTagCompound ();
                    }

                    blockToDrop.getTagCompound ().setInteger (TileEntityArdiumWorkbench.KEY_ARDIUM_STORED_AMOUNT, tileEntityArdiumWorkbench.getArdiumStoredAmount ());

                    NBTTagList nbtTagList = new NBTTagList ();
                    for (int index = 0; index < tileEntityArdiumWorkbench.ardiumWorkbenchContent.length; ++index) {
                        NBTTagCompound nbtTagSlot = new NBTTagCompound ();
                        nbtTagSlot.setByte ("Slot", ((byte) index));
                        if ( tileEntityArdiumWorkbench.ardiumWorkbenchContent != null
                                && tileEntityArdiumWorkbench.ardiumWorkbenchContent[index] != null
                                && tileEntityArdiumWorkbench.ardiumWorkbenchContainerCustomName != null ) {
                            System.out.println (tileEntityArdiumWorkbench.ardiumWorkbenchContent[index].writeToNBT (nbtTagSlot));
                            tileEntityArdiumWorkbench.ardiumWorkbenchContent[index].writeToNBT (nbtTagSlot);
                        }
                        nbtTagList.appendTag (nbtTagSlot);
                    }

                    blockToDrop.getTagCompound ().setTag ("Items", nbtTagList);
                    blockToDrop.getTagCompound ().setString (TileEntityArdiumWorkbench
                            .KEY_ARDIUM_CONTAINER_CUSTOM_NAME, tileEntityArdiumWorkbench.getInventoryName ());
                    itemStacks.add (blockToDrop);
                }
            }
            return itemStacks;
        }
        return super.getDrops (world, x, y, z, metadata, fortune);
    }

    @Override
    public TileEntity createNewTileEntity (World world, int metadata) {
        return new TileEntityArdiumWorkbench ();
    }
}