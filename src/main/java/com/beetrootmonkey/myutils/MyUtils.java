package com.beetrootmonkey.myutils;

import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.client.ModTab;
import com.beetrootmonkey.myutils.config.ConfigLoader;
import com.beetrootmonkey.myutils.crafting.ModRecipes;
import com.beetrootmonkey.myutils.drops.DropHandler;
import com.beetrootmonkey.myutils.item.ModItems;
import com.beetrootmonkey.myutils.item.fuel.FuelHandler;
import com.beetrootmonkey.myutils.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MC_VERSIONS, guiFactory = Reference.GUI_FACTORY)
public class MyUtils {

	public static final ModTab creativeTab = new ModTab();
	
	@SidedProxy(serverSide = "com.beetrootmonkey.myutils.proxy.CommonProxy", clientSide = "com.beetrootmonkey.myutils.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public static MyUtils instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigLoader.load(event);
		ModItems.init();
		ModBlocks.init();
		MinecraftForge.EVENT_BUS.register(new DropHandler());
		GameRegistry.registerFuelHandler(new FuelHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.addRecipes();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
