package com.ardium.pvp.common.blocks;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockWebObsidian extends Block {
    public BlockWebObsidian () {
        super (Material.piston);
        setBlockName ("webObsidian");
        setBlockTextureName (ArdiumSE.MOD_ID + ":" + getUnlocalizedName ().substring (5));
        setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
        setLightOpacity (1);
        setHardness (50.0F);
        setResistance (2000.0F);
        setStepSound (soundTypePiston);
    }

    @Override
    public boolean canHarvestBlock (EntityPlayer player, int metadata) {
        ItemStack stack = player.inventory.getCurrentItem ();
        if ( stack == null ) {
            return player.canHarvestBlock (this);
        } else {
            return stack.getItem () instanceof ItemShears || stack.getItem () instanceof ItemSword;
        }
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    @Override
    protected boolean canSilkHarvest () {
        return true;
    }


    @Override
    public boolean getBlocksMovement (IBlockAccess iBlockAccess, int x, int y, int z) {
        return iBlockAccess.getBlock (x, y, z) instanceof BlockWebObsidian;
    }

    @Override
    public boolean isOpaqueCube () {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock () {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType () {
        return 1;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity) {
        entity.setInWeb ();
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Item getItemDropped (int fortune, Random random, int metadata) {
        return ItemsRegister.stringObsidian;
    }

    @Override
    public MapColor getMapColor (int mapColorId) {
        return MapColor.obsidianColor;
    }
}
