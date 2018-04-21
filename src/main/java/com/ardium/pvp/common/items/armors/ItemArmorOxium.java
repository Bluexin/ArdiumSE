package com.ardium.pvp.common.items.armors;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.init.ItemsRegister;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorOxium extends ItemArmor {
    public ItemArmorOxium(ArmorMaterial armorMaterial, int armorType) {
        super (armorMaterial, 0, armorType);
        switch (armorType) {
            case 0:
                this.setUnlocalizedName ("oxiumHelmet");
                break;
            case 1:
                this.setUnlocalizedName ("oxiumChestplate");
                break;
            case 2:
                this.setUnlocalizedName ("oxiumLeggings");
                break;
            case 3:
                this.setUnlocalizedName ("oxiumBoots");
                break;
            default:
                FMLLog.getLogger ().error ("Unlocalized name not found !");
                break;
        }

        this.setTextureName (ArdiumSE.MOD_ID + ":" + this.getUnlocalizedName ().substring (5));
        this.setCreativeTab (ArdiumSE.TAB_ARDIUM_SE);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return stack.getItem () == ItemsRegister.oxiumLeggings ? "ardiumse:textures/models/armor/oxium_layer_2.png" : "ardiumse:textures/models/armor/oxium_layer_1.png";
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if ( player.getCurrentArmor (3) != null && player.getCurrentArmor (3).getItem () == ItemsRegister.oxiumHelmet ) {
            player.addPotionEffect (new PotionEffect (Potion.nightVision.getId (), 220, 1));
        }

        if ( player.getCurrentArmor (2) != null && player.getCurrentArmor (2).getItem () == ItemsRegister.oxiumChestplate ) {
            player.addPotionEffect (new PotionEffect (Potion.damageBoost.getId (), 1, 1));
        }

        if ( player.getCurrentArmor (1) != null && player.getCurrentArmor (1).getItem () == ItemsRegister.oxiumLeggings ) {
            player.addPotionEffect (new PotionEffect (Potion.digSpeed.getId (), 1, 1));
        }

        if ( player.getCurrentArmor (0) != null && player.getCurrentArmor (0).getItem () == ItemsRegister.oxiumBoots ) {
            player.addPotionEffect (new PotionEffect (Potion.moveSpeed.getId (), 1, 1));
        }

        if ( player.getCurrentArmor (0) != null && player.getCurrentArmor (1) != null && player.getCurrentArmor (2) != null && player.getCurrentArmor (3) != null && player.getCurrentArmor (0).getItem () == ItemsRegister.oxiumHelmet && player.getCurrentArmor (1).getItem () == ItemsRegister.oxiumChestplate && player.getCurrentArmor (2).getItem () == ItemsRegister.oxiumLeggings && player.getCurrentArmor (3).getItem () == ItemsRegister.oxiumBoots ) {
            player.addPotionEffect (new PotionEffect (Potion.fireResistance.getId (), 1, 1));
        }

    }
}
