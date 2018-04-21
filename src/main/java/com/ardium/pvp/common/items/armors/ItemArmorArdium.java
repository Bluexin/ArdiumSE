package com.ardium.pvp.common.items.armors;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.ItemsRegister;
import com.ardium.pvp.common.items.ItemArdium;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorArdium extends ItemArmor {
    public ItemArmorArdium(ArmorMaterial armorMaterial, int armorType) {
        super (armorMaterial, 0, armorType);
        switch (armorType) {
            case 0:
                this.setUnlocalizedName ("ardiumHelmet");
                break;
            case 1:
                this.setUnlocalizedName ("ardiumChestplate");
                break;
            case 2:
                this.setUnlocalizedName ("ardiumLeggings");
                break;
            case 3:
                this.setUnlocalizedName ("ardiumBoots");
                break;
            default:
                FMLLog.getLogger ().error ("Unlocalized name not found !");
                break;
        }

        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if ( player.getCurrentArmor (3) != null && player.getCurrentArmor (3).getItem () == ItemsRegister.ardiumHelmet ) {
            player.addPotionEffect (new PotionEffect (Potion.nightVision.getId (), 220, 0));
        }

        if ( player.getCurrentArmor (2) != null && player.getCurrentArmor (2).getItem () == ItemsRegister.ardiumChestplate ) {
            player.addPotionEffect (new PotionEffect (Potion.damageBoost.getId (), 1, 0));
        }

        if ( player.getCurrentArmor (1) != null && player.getCurrentArmor (1).getItem () == ItemsRegister.ardiumLeggings ) {
            player.addPotionEffect (new PotionEffect (Potion.digSpeed.getId (), 1, 0));
        }

        if ( player.getCurrentArmor (0) != null && player.getCurrentArmor (0).getItem () == ItemsRegister.ardiumBoots ) {
            player.addPotionEffect (new PotionEffect (Potion.moveSpeed.getId (), 1, 0, true));
        }

    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return stack.getItem () == ItemsRegister.ardiumLeggings ? "ardiumse:textures/models/armor/ardium_layer_2.png" : "ardiumse:textures/models/armor/ardium_layer_1.png";
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair) {
        return repair.getItem () instanceof ItemArdium;
    }
}
