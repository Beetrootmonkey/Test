package com.beetrootmonkey.myutils.config;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.beetrootmonkey.myutils.Reference;

import net.minecraftforge.common.config.Configuration;

@Mod.EventBusSubscriber
public class ConfigLoader
{
	
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Reference.MOD_ID)) {
			reloadConfig();
		}
	}
	
	private static void reloadConfig() {		
		//boolean someConfigFlag = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigFlag", false).getBoolean(false);
		//Notice there is nothing that gets the value of this property so the expression results in a Property object.
        //Property someProperty = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigString", "nothing");
        
        // Here we add a comment to our new property.
        //someProperty.comment = "This value can be read as a string!";
        
        //String someConfigString = someProperty.getString();
				
		Config.config.setCategoryComment(Config.CATEGORY_DROPS, "If false, disables the alteration of this mob's drops.");
        Config.horseDrops = Config.config.get(Config.CATEGORY_DROPS, "Horse", true).getBoolean(true);
        Config.sheepDrops = Config.config.get(Config.CATEGORY_DROPS, "Sheep", true).getBoolean(true);
        Config.spiderDrops = Config.config.get(Config.CATEGORY_DROPS, "Spider", true).getBoolean(true);
        Config.squidDrops = Config.config.get(Config.CATEGORY_DROPS, "Squid", true).getBoolean(true);
        
        Config.config.setCategoryComment(Config.CATEGORY_RECIPES, "If false, disables this recipe.");
        Config.coalPellet = Config.config.get(Config.CATEGORY_RECIPES, "CoalPellet", true).getBoolean(true);
        Config.charcoalPellet = Config.config.get(Config.CATEGORY_RECIPES, "CharcoalPellet", true).getBoolean(true);
        Config.torch = Config.config.get(Config.CATEGORY_RECIPES, "Torch", true).getBoolean(true);
        Config.jellyCube = Config.config.get(Config.CATEGORY_RECIPES, "JellyCube", true).getBoolean(true);
        Config.gelatin = Config.config.get(Config.CATEGORY_RECIPES, "Gelatin", true).getBoolean(true);
        Config.altBread = Config.config.get(Config.CATEGORY_RECIPES, "AlternateBread", true).getBoolean(true);
        Config.easyPotions = Config.config.get(Config.CATEGORY_RECIPES, "easyPotions", false).getBoolean(false);
        Config.altCookie = Config.config.get(Config.CATEGORY_RECIPES, "AlternateCookie", true).getBoolean(true);

		if (Config.config.hasChanged()) {
			Config.config.save();
		}
	}
	
	public static void load(FMLPreInitializationEvent event)
	{
		Config.config = new Configuration(event.getSuggestedConfigurationFile());
		Config.config.load();
		reloadConfig();
	}
	
}
