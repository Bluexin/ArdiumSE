package com.ardium.pvp.common.entity.extendedproperties;

import com.ardium.pvp.ArdiumSE;
import com.ardium.pvp.common.inventory.ContainerArdiumWorkbench;
import com.ardium.pvp.common.tileentity.TileEntityArdiumWorkbench;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class ExtendedEntityPlayerProperties implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = ArdiumSE.MOD_ID + "_ExtendedEntityPlayer";
    public static final String KEY_CURRENT_ARDIUM = "CurrentArdium";

    private final EntityPlayer player;
    private int currentArdiumAmount;
    private ContainerArdiumWorkbench containerArdiumWorkbench;
    private TileEntityArdiumWorkbench tileEntityArdiumWorkbench;

    public ExtendedEntityPlayerProperties (EntityPlayer player) {
        this.player = player;
    }

    public static final void register (EntityPlayer player) {
        player.registerExtendedProperties (ExtendedEntityPlayerProperties.EXT_PROP_NAME,
                new ExtendedEntityPlayerProperties (player));
    }

    public static final ExtendedEntityPlayerProperties get (EntityPlayer player) {
        return (ExtendedEntityPlayerProperties) player.getExtendedProperties (EXT_PROP_NAME);
    }

    /**
     * Called when the entity that this class is attached to is saved.
     * Any custom entity data  that needs saving should be saved here.
     *
     * @param compound The compound to save to.
     */
    @Override
    public void saveNBTData (NBTTagCompound compound) {
        NBTTagCompound nbtProperties = new NBTTagCompound ();
        nbtProperties.setInteger (KEY_CURRENT_ARDIUM, this.currentArdiumAmount);
        compound.setTag (EXT_PROP_NAME, nbtProperties);
    }

    /**
     * Called when the entity that this class is attached to is loaded.
     * In order to hook into this, you will need to subscribe to the EntityConstructing event.
     * Otherwise, you will need to initialize manually.
     *
     * @param compound The compound to load from.
     */
    @Override
    public void loadNBTData (NBTTagCompound compound) {
        if ( compound.hasKey (EXT_PROP_NAME, Constants.NBT.TAG_COMPOUND) ) {
            NBTTagCompound nbtProperties = ((NBTTagCompound) compound.getTag (EXT_PROP_NAME));
            this.currentArdiumAmount = nbtProperties.getInteger (KEY_CURRENT_ARDIUM);
        }
    }

    /**
     * Used to initialize the extended properties with the entity that this is attached to, as well
     * as the world object.
     * Called automatically if you register with the EntityConstructing event.
     * May be called multiple times if the extended properties is moved over to a new entity.
     * Such as when a player switches dimension {Minecraft re-creates the player entity}
     *
     * @param entity The entity that this extended properties is attached to
     * @param world  The world in which the entity exists
     */
    @Override
    public void init (Entity entity, World world) {
    }
}
