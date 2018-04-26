package com.ardium.pvp.common.configuration;


public class ArdiumSEConfiguration {
    /*
    public static final String CATEGORY_MOD_GUI_IDS = "modGuiIDs";
    public static int ardiumWorkbenchGuiId;

    private static Configuration configuration;

    public static void commonPreInitialization () {
        File configurationFile = new File (Loader.instance ().getConfigDir (), "ArdiumSEConfiguration.cfg");
        configuration = new Configuration (configurationFile);
        syncFromFiles ();
    }

    public static void syncFromFiles () {
        syncConfiguration (true, true);
    }

    public static void syncFromFieds () {
        syncConfiguration (false, false);
    }

    public static void syncFromGui () {
        syncConfiguration (false, true);
    }

    private static void syncConfiguration (boolean isLoadedFromConfigurationFile, boolean isFieldsReadFromConfig) {
        if ( isLoadedFromConfigurationFile ) configuration.load ();

        Property propertyGuiArdiumWorkbenchID = configuration.get (CATEGORY_MOD_GUI_IDS, "gui_id_ardium_workbench", 0);
        propertyGuiArdiumWorkbenchID.setLanguageKey ("gui.config." + CATEGORY_MOD_GUI_IDS +
                ".gui_id_ardium_workbench.name");
        propertyGuiArdiumWorkbenchID.comment = I18n.format ("gui.config." + CATEGORY_MOD_GUI_IDS
                + ".gui_id_ardium_workbench.comment");
        propertyGuiArdiumWorkbenchID.setValue (0);

        List < String > propertyOrderModGuiIds = new ArrayList <> ();
        configuration.setCategoryPropertyOrder (CATEGORY_MOD_GUI_IDS, propertyOrderModGuiIds);

        if ( isFieldsReadFromConfig ) {
            ardiumWorkbenchGuiId = propertyGuiArdiumWorkbenchID.getInt ();
        }

        propertyGuiArdiumWorkbenchID.set (ardiumWorkbenchGuiId);

        if ( configuration.hasChanged () ) configuration.save ();
    }

    public static Configuration getConfiguration () {
        return configuration;
    }

    public static class ConfigurationEventHandler {
        @SubscribeEvent (priority = EventPriority.LOWEST)
        public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent configChangedEvent) {
            if ( configChangedEvent.modID.equalsIgnoreCase (ArdiumSE.MOD_ID) ) {
                ArdiumSEConfiguration.syncFromGui ();
            }
        }
    }
    */
}

