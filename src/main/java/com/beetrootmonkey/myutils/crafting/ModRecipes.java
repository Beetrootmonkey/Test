package com.beetrootmonkey.myutils.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.config.Config;
import com.beetrootmonkey.myutils.item.ModItems;
import com.beetrootmonkey.myutils.lib.Recipes;

@EventBusSubscriber
public class ModRecipes {

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModItems.copperAxe), "##", "#S", " S", '#', ModItems.ingotCopper, 'S',
				Items.STICK);
		GameRegistry.addRecipe(new ItemStack(ModItems.helmetCopper), "###", "# #", '#', ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.chestplateCopper), "# #", "###", "###", '#',
				ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.leggingsCopper), "###", "# #", "# #", '#', ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.bootsCopper), "# #", "# #", '#', ModItems.ingotCopper);

		if (Config.fuelPellet) {
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pelletFuel, 8), new ItemStack(Items.COAL));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pelletFuel, 8), new ItemStack(Items.COAL, 1, 1));
		}

		// smelting recipes
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.6f);
		GameRegistry.addSmelting(ModItems.chunkCopper, new ItemStack(ModItems.ingotCopper), 0.6f);
		if (Config.gelatin) {
			GameRegistry.addSmelting(Items.BONE, new ItemStack(ModItems.gelatin), 0.35f);
		}
		if (Config.spiderDrops) {
			GameRegistry.addSmelting(Items.SPIDER_EYE, new ItemStack(ModItems.cookedSpiderEye), 0.35f);
		}
		if (Config.horseDrops) {
			GameRegistry.addSmelting(ModItems.rawHorse, new ItemStack(ModItems.cookedHorse), 0.35f);
		}
		if (Config.villagerDrops) {
			GameRegistry.addSmelting(ModItems.rawVillager, new ItemStack(ModItems.cookedVillager), 0.35f);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
//		List<ItemStack> list1 = OreDictionary.getOres("slimeball");
//		for (ItemStack itemStack : list1) {
//
//			System.out.println(itemStack);
//		}
//		List<IRecipe> list = findRecipes(Blocks.TORCH);
		
		Recipes.removeRecipes(Blocks.TORCH, Items.STICK);
		Recipes.removeRecipes(Blocks.TORCH, "stickWood");
		Recipes.removeRecipes("ingotIron");
		
		
	}
	
}