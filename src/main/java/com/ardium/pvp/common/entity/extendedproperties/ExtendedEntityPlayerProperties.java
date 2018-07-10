package com.ardium.pvp.common.entity.extendedproperties;

public class ExtendedEntityPlayerProperties /* implements IExtendedEntityProperties */ {
    /*

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


    @Override
    public void saveNBTData (NBTTagCompound compound) {
        NBTTagCompound nbtProperties = new NBTTagCompound ();
        nbtProperties.setInteger (KEY_CURRENT_ARDIUM, this.currentArdiumAmount);
        compound.setTag (EXT_PROP_NAME, nbtProperties);
    }


    @Override
    public void loadNBTData (NBTTagCompound compound) {
        if ( compound.hasKey (EXT_PROP_NAME, Constants.NBT.TAG_COMPOUND) ) {
            NBTTagCompound nbtProperties = ((NBTTagCompound) compound.getTag (EXT_PROP_NAME));
            this.currentArdiumAmount = nbtProperties.getInteger (KEY_CURRENT_ARDIUM);
        }
    }

    @Override
    public void init (Entity entity, World world) {
    }

    */
}
