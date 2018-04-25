package com.ardium.pvp.common.events;

import com.ardium.pvp.common.entity.extendedproperties.ExtendedEntityPlayerProperties;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class EventsHandlerArdiumSE {
    @SubscribeEvent
    public void onEntityConstructing (EntityEvent.EntityConstructing entityConstructingEvent) {
        if ( entityConstructingEvent.entity instanceof EntityPlayer
                && ExtendedEntityPlayerProperties.get ((EntityPlayer) entityConstructingEvent.entity) == null ) {
            ExtendedEntityPlayerProperties.register (((EntityPlayer) entityConstructingEvent.entity));
        }
    }

    @SubscribeEvent
    public void onClonePlayer (PlayerEvent.Clone playerCloneEvent) {
        if ( playerCloneEvent.wasDeath ) {
            NBTTagCompound compound = new NBTTagCompound ();
            ExtendedEntityPlayerProperties.get (playerCloneEvent.original).saveNBTData (compound);
            ExtendedEntityPlayerProperties.get (playerCloneEvent.entityPlayer).loadNBTData (compound);
        }
    }

    @SubscribeEvent
    public void onPlayerOpensContainer (PlayerOpenContainerEvent openContainerEvent) {
    }
}
