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
        if ( !world.isRemote && !player.isSneaking () ) {
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
            if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
                NBTTagCompound tag = itemStack.stackTagCompound;
                if (tag != null) {
                    NBTTagCompound teTag = tag.getCompoundTag("TileEntityData");
                    tileEntity.readFromNBT(teTag);
                    tileEntity.xCoord = x;
                    tileEntity.yCoord = y;
                    tileEntity.zCoord = z;
                }
                if ( itemStack.hasDisplayName () ) {
                    (( TileEntityArdiumWorkbench ) tileEntity).setArdiumWorkbenchContainerCustomName (itemStack.getDisplayName ());
                }
            }
        }
    }

    @Override
    public ArrayList <ItemStack> getDrops (World world, int x, int y, int z, int metadata, int fortune) {
        if ( world.getBlock (x, y, z) instanceof BlockContainerArdiumWorkbench && world.getBlockMetadata (x, y, z) == 0 ) {
            TileEntity tileEntity = world.getTileEntity (x, y, z);
            if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
                TileEntityArdiumWorkbench tileEntityArdiumWorkbench = (( TileEntityArdiumWorkbench ) tileEntity);

                ArrayList <ItemStack> items = new ArrayList <ItemStack> ();

                int count = quantityDropped (metadata, fortune, world.rand);
                for (int i = 0; i < count; i++) {
                    Item item = getItemDropped (metadata, world.rand, fortune);
                    if ( item != null ) {
                        ItemStack blockToDrop = new ItemStack (item, 1, damageDropped (metadata));
                        NBTTagCompound blockNBTTagCompound = new NBTTagCompound ();
                        NBTTagCompound tileEntityNBTTagCompound = new NBTTagCompound ();
                        tileEntityArdiumWorkbench.writeToNBT (tileEntityNBTTagCompound);
                        tileEntityNBTTagCompound.removeTag ("x");
                        tileEntityNBTTagCompound.removeTag ("y");
                        tileEntityNBTTagCompound.removeTag ("z");
                        blockNBTTagCompound.setTag ("TileEntityData", tileEntityNBTTagCompound);
                        blockToDrop.stackTagCompound = blockNBTTagCompound;
                        items.add (blockToDrop);
                    }
                }
                return items;
            }
        }
        return super.getDrops (world, x, y, z, metadata, fortune);
    }

    @Override
    public TileEntity createTileEntity (World world, int metadata) {
        return metadata == 0 ? new TileEntityArdiumWorkbench () : null;
    }

    @Override
    public TileEntity createNewTileEntity (World world, int metadata) {
        return createTileEntity (world, metadata);
    }
}