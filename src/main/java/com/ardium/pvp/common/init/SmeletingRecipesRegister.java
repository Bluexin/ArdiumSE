package com.ardium.pvp.common.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SmeletingRecipesRegister {
    public SmeletingRecipesRegister () {
    }

    public static void register () {
        smeltingRegister (BlocksRegister.oreArdium, ItemsRegister.ardium, 1.1F);
        smeltingRegister (BlocksRegister.blockArdium, ItemsRegister.ardium, 9, 9.9F);
    }

    private static void smeltingRegister (Block block, Item item, float xp) {
        FurnaceRecipes.smelting ().func_151393_a (block, new ItemStack (item, 1, 0), xp);
    }

    private static void smeltingRegister (Block block, Item item, int outputItemNumber, float xp) {
        FurnaceRecipes.smelting ().func_151393_a (block, new ItemStack (item, outputItemNumber, 0), xp);
    }

    /*
    private static void smeltingRegister(Block block, Item item, int outputItemNumber, int metadata, float xp) {
        FurnaceRecipes.smelting ().func_151393_a (block, new ItemStack (item, outputItemNumber, metadata), xp);
    }
    */
}