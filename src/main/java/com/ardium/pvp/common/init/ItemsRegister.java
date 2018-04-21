package com.ardium.pvp.common.init;

import com.ardium.pvp.common.items.ItemArdium;
import com.ardium.pvp.common.items.armors.ItemArmorArdium;
import com.ardium.pvp.common.items.armors.ItemArmorOxium;
import com.ardium.pvp.common.items.tools.ardium.*;
import com.ardium.pvp.common.items.tools.oxium.ItemAxeOxium;
import com.ardium.pvp.common.items.tools.oxium.ItemPickaxeOxium;
import com.ardium.pvp.common.items.tools.oxium.ItemSpadeOxium;
import com.ardium.pvp.common.items.tools.oxium.ItemSwordOxium;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsRegister {
    private static final ToolMaterial ARDIUM_TOOLS_MATERIAL;
    private static final ArmorMaterial ARDIUM_ARMOR_MATERIAL;
    private static final ToolMaterial OXIUM_TOOLS_MATERIAL;
    private static final ArmorMaterial OXIUM_ARMOR_MATERIAL;
    public static Item ardium;
    public static Item ardiumHelmet;
    public static Item ardiumChestplate;
    public static Item ardiumLeggings;
    public static Item ardiumBoots;
    public static Item oxiumHelmet;
    public static Item oxiumChestplate;
    public static Item oxiumLeggings;
    public static Item oxiumBoots;
    public static Item ardiumSword;
    public static Item ardiumShovel;
    public static Item ardiumPickaxe;
    public static Item ardiumAxe;
    public static Item ardiumMultiTools;
    private static Item oxiumSword;
    private static Item oxiumShovel;
    private static Item oxiumAxe;
    private static Item oxiumPickaxe;

    static {
        ARDIUM_TOOLS_MATERIAL = EnumHelper.addToolMaterial ("ARDIUM_TOOLS_MATERIAL", 3, 4999, 30.0F, 6.0F, 35).setRepairItem (new ItemStack (ardium, 0, 1));
        ARDIUM_ARMOR_MATERIAL = EnumHelper.addArmorMaterial ("ARDIUM_ARMOR_MATERIAL", 100, new int[]{4, 9, 7, 4}, 20);
        OXIUM_TOOLS_MATERIAL = EnumHelper.addToolMaterial ("OXIUM_TOOLS_MATERIAL", 4, 5100, 31.0F, 7.0F, 36);
        OXIUM_ARMOR_MATERIAL = EnumHelper.addArmorMaterial ("OXIUM_ARMOR_MATERIAL", 350, new int[]{5, 10, 8, 5}, 30);
    }

    public static void initialization() {
        ardium = new ItemArdium ();
        ardiumSword = new ItemSwordArdium (ARDIUM_TOOLS_MATERIAL);
        ardiumShovel = new ItemSpadeArdium (ARDIUM_TOOLS_MATERIAL);
        ardiumPickaxe = new ItemPickaxeArdium (ARDIUM_TOOLS_MATERIAL);
        ardiumAxe = new ItemAxeArdium (ARDIUM_TOOLS_MATERIAL);
        ardiumMultiTools = new ItemMultiToolsArdium (ARDIUM_TOOLS_MATERIAL);
        ardiumHelmet = new ItemArmorArdium (ARDIUM_ARMOR_MATERIAL, 0);
        ardiumChestplate = new ItemArmorArdium (ARDIUM_ARMOR_MATERIAL, 1);
        ardiumLeggings = new ItemArmorArdium (ARDIUM_ARMOR_MATERIAL, 2);
        ardiumBoots = new ItemArmorArdium (ARDIUM_ARMOR_MATERIAL, 3);
        oxiumSword = new ItemSwordOxium (OXIUM_TOOLS_MATERIAL);
        oxiumShovel = new ItemSpadeOxium (OXIUM_TOOLS_MATERIAL);
        oxiumPickaxe = new ItemPickaxeOxium (OXIUM_TOOLS_MATERIAL);
        oxiumAxe = new ItemAxeOxium (OXIUM_TOOLS_MATERIAL);
        oxiumHelmet = new ItemArmorOxium (OXIUM_ARMOR_MATERIAL, 0);
        oxiumChestplate = new ItemArmorOxium (OXIUM_ARMOR_MATERIAL, 1);
        oxiumLeggings = new ItemArmorOxium (OXIUM_ARMOR_MATERIAL, 2);
        oxiumBoots = new ItemArmorOxium (OXIUM_ARMOR_MATERIAL, 3);
        register ();
    }

    private static void register() {
        registerItems (ardium);
        registerItems (ardiumSword);
        registerItems (ardiumShovel);
        registerItems (ardiumPickaxe);
        registerItems (ardiumAxe);
        registerItems (ardiumMultiTools);
        registerItems (ardiumHelmet);
        registerItems (ardiumChestplate);
        registerItems (ardiumLeggings);
        registerItems (ardiumBoots);
        registerItems (oxiumSword);
        registerItems (oxiumShovel);
        registerItems (oxiumPickaxe);
        registerItems (oxiumAxe);
        registerItems (oxiumHelmet);
        registerItems (oxiumChestplate);
        registerItems (oxiumLeggings);
        registerItems (oxiumBoots);
    }

    private static void registerItems(Item item) {
        GameRegistry.registerItem (item, item.getUnlocalizedName ().substring (5));
    }
}