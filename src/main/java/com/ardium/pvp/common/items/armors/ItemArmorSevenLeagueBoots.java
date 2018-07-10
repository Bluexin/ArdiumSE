package com.ardium.pvp.common.items.armors;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.ItemsRegister;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;

public class ItemArmorSevenLeagueBoots extends ItemArmor {
    public ItemArmorSevenLeagueBoots (ArmorMaterial armorMaterial, int armorType) {
        super (armorMaterial, 0, armorType);
        if ( armorType == 3 ) {
            setUnlocalizedName ("sevenLeagueBoots");
        } else {
            FMLLog.getLogger ().error ("Unlocalized name not found !");
        }
        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }

    @Override
    public String getArmorTexture (ItemStack stack, Entity entity, int slot, String type) {
        return stack.getItem () == ItemsRegister.sevenLeagueBoots ?
                ArdiumSE.MOD_ID + ":textures/models/armor/seven_league_boots_layer_1.png" : null;
    }

    @Override
    public void onArmorTick (World world, EntityPlayer player, ItemStack itemStack) {
        if ( player.getCurrentArmor (0) != null
                && player.getCurrentArmor (0).getItem () != null
                && player.getCurrentArmor (0).getItem () == ItemsRegister.sevenLeagueBoots ) {
            player.addPotionEffect (new PotionEffect (Potion.moveSpeed.getId (), 1, 6));
            player.addPotionEffect (new PotionEffect (Potion.jump.getId (), 1, 2));
            player.stepHeight = 1F;
            player.fallDistance = 0.0F;

            int playerX = MathHelper.floor_double (player.posX);
            int playerY = MathHelper.floor_double (player.boundingBox.minY - player.getYOffset ());
            int playerZ = MathHelper.floor_double (player.posZ);

            Block blockBellow = player.worldObj.getBlock (playerX, playerY, playerZ);

            boolean isLiquidBellow = (blockBellow.getMaterial ().isLiquid ())
                    || (blockBellow instanceof BlockLiquid)
                    || (blockBellow instanceof BlockFluidBase
                    || (blockBellow instanceof IFluidBlock)
                    && !(blockBellow instanceof BlockAir)
                    && !(blockBellow.getMaterial () == Material.air)
                    && !(blockBellow.isOpaqueCube ()));
            if ( isLiquidBellow && player.motionY < 0
                    && !player.isSneaking ()
                    && !player.isRiding ()
                    && !player.onGround
                    && !player.capabilities.isFlying ) {
                player.posY -= player.motionY;
                player.motionY = 0.0D;
                player.onGround = true;
            }
        }
    }

    @Override
    public String getItemStackDisplayName (ItemStack itemStack) {
        return EnumChatFormatting.GOLD + super.getItemStackDisplayName (itemStack) + EnumChatFormatting.RESET;
    }
}
