package com.ardium.pvp.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesRegister {
    public static void registerRecipes () {
        registerShapedCraftingRecipes ();
        registerShapelessCraftingRecipes ();
    }

    private static void registerShapedCraftingRecipes () {
        //Modded Items Shaped Crafting Recipes
        GameRegistry.addShapedRecipe (new ItemStack (ItemsRegister.ardiumMultiTools), "SPA", " W ", " W ",
                'S', ItemsRegister.ardiumAxe, 'P', ItemsRegister.ardiumPickaxe, 'A', ItemsRegister.ardiumAxe, 'W', Items.stick);
        GameRegistry.addShapedRecipe (new ItemStack (ItemsRegister.ardiumHelmet), "AAA", "A A", 'A', ItemsRegister.ardium);
        GameRegistry.addShapedRecipe (new ItemStack (ItemsRegister.ardiumChestplate), "A A", "AAA", "AAA", 'A', ItemsRegister.ardium);
        GameRegistry.addShapedRecipe (new ItemStack (ItemsRegister.ardiumLeggings), "AAA", "A A", "A A", 'A', ItemsRegister.ardium);
        GameRegistry.addShapedRecipe (new ItemStack (ItemsRegister.ardiumBoots), "A A", "A A", 'A', ItemsRegister.ardium);
        GameRegistry.addRecipe (new ItemStack (ItemsRegister.sevenLeagueBoots), "PAP", "GBG", "PAP",
                'P', new ItemStack (Items.potionitem, 1, 8226) /*Speed II Potion*/,
                'A', ItemsRegister.ardium,
                'G', Blocks.gold_block,
                'B', Items.leather_boots);
    }

    private static void registerShapelessCraftingRecipes () {
    }
}