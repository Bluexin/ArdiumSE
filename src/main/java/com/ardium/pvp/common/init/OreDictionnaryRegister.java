package com.ardium.pvp.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class OreDictionnaryRegister {

    public static void registerOres () {
        //Vanilla Items
        OreDictionary.registerOre ("slimeball", Items.slime_ball);
        OreDictionary.registerOre ("slime", Items.slime_ball);
        OreDictionary.registerOre ("stickWood", Items.stick);
        OreDictionary.registerOre ("woodStick", Items.stick);
        OreDictionary.registerOre ("string", Items.string);
        //Vanilla Blocks
        OreDictionary.registerOre ("blockObsidian", Blocks.obsidian);
        OreDictionary.registerOre ("obsidian", Blocks.obsidian);
        OreDictionary.registerOre ("obsidianBlock", Blocks.obsidian);
        //Modded Items
        OreDictionary.registerOre ("ingotArdium", ItemsRegister.ardium);
        OreDictionary.registerOre ("ardiumIngot", ItemsRegister.ardium);
        OreDictionary.registerOre ("ardium", ItemsRegister.ardium);
        OreDictionary.registerOre ("stringObsidian", ItemsRegister.stringObsidian);
        OreDictionary.registerOre ("obsidianString", ItemsRegister.stringObsidian);
        //Modded Blocks
        OreDictionary.registerOre ("blockArdium", BlocksRegister.blockArdium);
        OreDictionary.registerOre ("ardiumBlock", BlocksRegister.blockArdium);

        registerShapedOreRecipes ();
        registerShapelessOreRecipes ();
    }

    private static void registerShapedOreRecipes () {
        //Modded Shaped Ore Items Recipes
        GameRegistry.addRecipe (new ShapedOreRecipe (new ItemStack (ItemsRegister.stringObsidian, 5), "SOS", "OSO", "SOS",
                'S', "string", 'O', "obsidian"));
        GameRegistry.addRecipe (new ShapedOreRecipe (new ItemStack (ItemsRegister.stringObsidian, 4), "OSO", "SOS",
                "OSO", 'S', "string", 'O', "obsidian"));
        GameRegistry.addRecipe (new ShapedOreRecipe (new ItemStack (ItemsRegister.stringObsidian, 5), "SOS", "OSO", "SOS",
                'S', "string", 'O', "stickObsidian"));
        GameRegistry.addRecipe (new ShapedOreRecipe (new ItemStack (ItemsRegister.stringObsidian, 4), "OSO", "SOS", "OSO",
                'S', "string", 'O', "stickObsidian"));
        GameRegistry.addRecipe (new ShapedOreRecipe (ItemsRegister.ardiumSword, " A ", " A ", " S ", 'A', "ingotArdium", 'S', "stickWood"));
        GameRegistry.addRecipe (new ShapedOreRecipe (ItemsRegister.ardiumShovel, " A ", " S ", " S ", 'A', "ingotArdium", 'S', "stickWood"));
        GameRegistry.addRecipe (new ShapedOreRecipe (ItemsRegister.ardiumPickaxe, "AAA", " S ", " S ", 'A', "ingotArdium", 'S', "woodStick"));
        GameRegistry.addRecipe (new ShapedOreRecipe (ItemsRegister.ardiumAxe, "AA ", "AS ", " S ", 'A', "ingotArdium", 'S', "woodStick"));
        //Modded Shaped Ore Blocks Recipes
        GameRegistry.addRecipe (new ShapedOreRecipe (BlocksRegister.webObsidian, "OSO", "SOS", "OSO",
                'O', "blockObsidian", ""));
    }

    private static void registerShapelessOreRecipes () {
        //Modded Items Shapeless Ore Recipes
        GameRegistry.addRecipe (new ShapelessOreRecipe (new ItemStack (ItemsRegister.ardium, 9), "blockArdium"));
        //Modded Blocks Shapeless Ore Recipes
        GameRegistry.addRecipe (new ShapelessOreRecipe (BlocksRegister.blockArdium,
                "ingotArdium", "ingotArdium", "ingotArdium",
                "ingotArdium", "ingotArdium", "ingotArdium",
                "ingotArdium", "ingotArdium", "ingotArdium"));
    }
}
