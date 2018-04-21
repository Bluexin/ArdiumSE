package com.ardium.pvp.common.items.tools;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import java.util.Set;

public class ItemMultiTools extends ItemPickaxe {
    private final Set effectiveAgainst;

    protected ItemMultiTools(ToolMaterial toolMaterial) {
        super (toolMaterial);
        this.effectiveAgainst = Sets.newHashSet (Block.blockRegistry);
        this.setCreativeTab (CreativeTabs.tabTools);
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of ("pickaxe", "spade", "axe", "hoe");
    }

    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return this.effectiveAgainst.contains (block) || super.canHarvestBlock (block, stack);
    }

    public float func_150893_a(ItemStack stack, Block block) {
        return this.effectiveAgainst.contains (block) ? this.efficiencyOnProperMaterial : super.func_150893_a (stack, block);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if ( !player.canPlayerEdit (x, y, z, side, stack) ) {
            return false;
        } else {
            UseHoeEvent useHoeEvent = new UseHoeEvent (player, stack, world, x, y, z);
            if ( MinecraftForge.EVENT_BUS.post (useHoeEvent) ) {
                return false;
            } else if ( useHoeEvent.getResult () == Result.ALLOW ) {
                stack.damageItem (1, player);
                return true;
            } else {
                Block block = world.getBlock (x, y, z);
                if ( side == 0 || !world.getBlock (x, y + 1, z).isAir (world, x, y + 1, z) || block != Blocks.grass && block != Blocks.dirt ) {
                    return false;
                } else {
                    Block farmland = Blocks.farmland;
                    world.playSoundEffect ((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), farmland.stepSound.getStepResourcePath (), (farmland.stepSound.getVolume () + 1.0F) / 2.0F, farmland.stepSound.getPitch () * 0.8F);
                    if ( world.isRemote ) {
                        return true;
                    } else {
                        for (int loopOne = 0; loopOne < 2; ++loopOne) {
                            for (int loopTwo = 0; loopTwo < 2; ++loopTwo) {
                                if ( world.getBlock (x + loopOne, y, z + loopTwo) instanceof BlockGrass || world.getBlock (x + loopOne, y, z + loopTwo) instanceof BlockDirt ) {
                                    world.setBlock (x + loopOne, y, z + loopTwo, farmland);
                                }

                                if ( world.getBlock (x - loopOne, y, z - loopTwo) instanceof BlockGrass || world.getBlock (x - loopOne, y, z - loopTwo) instanceof BlockDirt ) {
                                    world.setBlock (x - loopOne, y, z - loopTwo, farmland);
                                }

                                if ( world.getBlock (x - loopOne, y, z + loopTwo) instanceof BlockGrass || world.getBlock (x - loopOne, y, z + loopTwo) instanceof BlockDirt ) {
                                    world.setBlock (x - loopOne, y, z + loopTwo, farmland);
                                }

                                if ( world.getBlock (x + loopOne, y, z - loopTwo) instanceof BlockGrass || world.getBlock (x + loopOne, y, z - loopTwo) instanceof BlockDirt ) {
                                    world.setBlock (x + loopOne, y, z - loopTwo, farmland);
                                }

                                if ( world.getBlock (x + loopTwo, y, z + loopOne) instanceof BlockGrass || world.getBlock (x + loopTwo, y, z + loopOne) instanceof BlockDirt ) {
                                    world.setBlock (x + loopTwo, y, z + loopOne, farmland);
                                }

                                if ( world.getBlock (x - loopTwo, y, z - loopOne) instanceof BlockGrass || world.getBlock (x - loopTwo, y, z - loopOne) instanceof BlockDirt ) {
                                    world.setBlock (x - loopTwo, y, z - loopOne, farmland);
                                }

                                stack.damageItem (1, player);
                            }
                        }

                        return true;
                    }
                }
            }
        }
    }
}
