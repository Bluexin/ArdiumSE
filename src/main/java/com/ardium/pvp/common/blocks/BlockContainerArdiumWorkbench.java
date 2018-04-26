package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.client.ClientProxy;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerArdiumWorkbench extends BlockContainer {

    public BlockContainerArdiumWorkbench() {
        super (Material.iron);
        setBlockName ("blockArdiumWorkbench");
        setBlockTextureName (ArdiumSE.MOD_ID + ":" + getUnlocalizedName ().substring (5));
        setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        setHardness (5.0F);
        setHarvestLevel ("pickaxe", 3, 0);
        setResistance (10.0F);
        setLightLevel (0.5F);
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return world.getBlockMetadata (x, y, z) == 0;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return metadata == 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if ( !world.isRemote ) {
            //Add a configurable GUI ID later
            player.openGui (ArdiumSE.instance, 0 /*ArdiumSEConfiguration.ardiumWorkbenchGuiId */, world, x, y, z);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileEntity = world.getTileEntity (x, y, z);
        if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
            for (int i1 = 0; i1 < ((TileEntityArdiumWorkbench) tileEntity).getSizeInventory (); ++i1) {
                ItemStack itemstack = ((TileEntityArdiumWorkbench) tileEntity).getStackInSlot (i1);

                if ( itemstack != null ) {
                    float f = world.rand.nextFloat () * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat () * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = world.rand.nextFloat () * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld (entityitem)) {
                        int j1 = world.rand.nextInt (21) + 10;

                        if ( j1 > itemstack.stackSize ) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem (world, (double) ((float) x + f),
                                (double) ((float) y + f1),
                                (double) ((float) z + f2),
                                new ItemStack (itemstack.getItem (), j1, itemstack.getItemDamage ()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) world.rand.nextGaussian () * f3);
                        entityitem.motionY = (double) ((float) world.rand.nextGaussian () * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) world.rand.nextGaussian () * f3);

                        if ( itemstack.hasTagCompound () ) {
                            entityitem.getEntityItem ().setTagCompound ((NBTTagCompound) itemstack.getTagCompound ().copy ());
                        }
                    }
                }
            }
        }
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        if ( itemStack.getItemDamage () == 0 ) {
            TileEntity tileEntity = world.getTileEntity (x, y, z);
            if ( tileEntity instanceof TileEntityArdiumWorkbench && itemStack.hasDisplayName () ) {
                ((TileEntityArdiumWorkbench) tileEntity).setArdiumWorkbenchContainerCustomName (itemStack.getDisplayName ());
            }
        }
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return ClientProxy.renderInventoryTESRId;
    }

    @Override
    public Block setLightLevel(float lightLevel) {
        lightLevel = 0F;
        return super.setLightLevel (lightLevel);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        TileEntityArdiumWorkbench tileEntityArdiumWorkbench = new TileEntityArdiumWorkbench ();
        return metadata == 0 ? tileEntityArdiumWorkbench : null;
    }
}