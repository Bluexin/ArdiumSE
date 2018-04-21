package com.ardium.pvp.common.guihandler;

import com.ardium.pvp.client.gui.GuiArdiumWorkbench;
import com.ardium.pvp.common.containers.ContainerArdiumWorkbench;
import com.ardium.pvp.common.tileentities.TileEntityArdiumWorkbench;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIHandlerArdiumSE implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity (x, y, z);
        if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
            return new ContainerArdiumWorkbench ((TileEntityArdiumWorkbench) tileEntity, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity (x, y, z);
        if ( tileEntity instanceof TileEntityArdiumWorkbench ) {
            return new GuiArdiumWorkbench ((TileEntityArdiumWorkbench) tileEntity, player.inventory);
        }
        return null;
    }
}