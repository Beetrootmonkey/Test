package com.beetrootmonkey.myutils.proxy;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.Reference;
import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.config.ConfigLoader;
import com.beetrootmonkey.myutils.crafting.ModRecipes;
import com.beetrootmonkey.myutils.drops.DropHandlerEntity;
import com.beetrootmonkey.myutils.item.ModItems;
import com.beetrootmonkey.myutils.item.fuel.FuelHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(Reference.MOD_ID + ":" + id, "inventory"));
	}
}
