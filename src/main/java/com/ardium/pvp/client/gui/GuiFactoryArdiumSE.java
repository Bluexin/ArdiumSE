package com.ardium.pvp.client.gui;

//import static com.ardium.pvp.common.configuration.ArdiumSEConfiguration.CATEGORY_MOD_GUI_IDS;

public class GuiFactoryArdiumSE /* implements IModGuiFactory */ {
    /*
    @Override
    public void initialize (Minecraft minecraftInstance) {

    }

    @Override
    public Class < ? extends GuiScreen > mainConfigGuiClass () {
        return GuiConfigArdiumSE.class;
    }


    @Override
    public Set < RuntimeOptionCategoryElement > runtimeGuiCategories () {
        return null;
    }


    @Override
    public RuntimeOptionGuiHandler getHandlerFor (RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class GuiConfigArdiumSE extends GuiConfig {
        public GuiConfigArdiumSE (GuiScreen parentScreen) {
            super (parentScreen,
                    getConfigElements (), ArdiumSE.MOD_ID, true, true
                    , I18n.format ("gui.config.main.title"));
        }

        private static List < IConfigElement > getConfigElements () {
            List < IConfigElement > list = new ArrayList < IConfigElement > ();
            list.add (new DummyConfigElement.DummyCategoryElement (I18n.format ("gui.config." + CATEGORY_MOD_GUI_IDS + ".gui_id_ardium_workbench.name")
                    , "gui.config." + CATEGORY_MOD_GUI_IDS + ".gui_id_ardium_workbench.name"
                    , CategoryEntryModGuiIds.class));
            return list;
        }

        private static class CategoryEntryModGuiIds extends GuiConfigEntries.CategoryEntry {
            public CategoryEntryModGuiIds (GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
                super (owningScreen, owningEntryList, configElement);
            }


            @Override
            protected GuiScreen buildChildScreen () {
                Configuration configuration = ArdiumSEConfiguration.getConfiguration ();
                ConfigElement categoryModGuiIds = new ConfigElement (configuration.getCategory (ArdiumSEConfiguration.CATEGORY_MOD_GUI_IDS));
                List < IConfigElement > propertiesOnScreen = categoryModGuiIds.getChildElements ();
                String windowTitle = I18n.format (I18n.format ("gui.config." + CATEGORY_MOD_GUI_IDS));
                return new GuiConfig (owningScreen, propertiesOnScreen, owningScreen.modID,
                        this.configElement.requiresWorldRestart () || this.owningScreen.allRequireWorldRestart,
                        this.configElement.requiresMcRestart () || this.owningScreen.allRequireMcRestart, windowTitle);
            }
        }
    }
    */
}
