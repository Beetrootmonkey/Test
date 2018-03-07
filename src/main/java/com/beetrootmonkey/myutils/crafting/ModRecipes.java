package com.beetrootmonkey.myutils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.item.ModItems;

public class ModRecipes {

	public static void addRecipes() {
		// shaped crafting recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.copperAxe), "##", "#S", " S", '#', ModItems.ingotCopper, 'S',
				Items.STICK);
		GameRegistry.addRecipe(new ItemStack(ModItems.helmetCopper), "###", "# #", '#', ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.chestplateCopper), "# #", "###", "###", '#', ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.leggingsCopper), "###", "# #", "# #", '#', ModItems.ingotCopper);
		GameRegistry.addRecipe(new ItemStack(ModItems.bootsCopper), "# #", "# #", '#', ModItems.ingotCopper);

		// shapeless crafting recipes
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pelletFuel, 8), new ItemStack(Items.COAL));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pelletFuel, 8), new ItemStack(Items.COAL, 1, 1));

		// smelting recipes
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.6f);
		GameRegistry.addSmelting(ModItems.chunkCopper, new ItemStack(ModItems.ingotCopper), 0.6f);
		GameRegistry.addSmelting(Items.BONE, new ItemStack(ModItems.gelatin), 0.35f);
		GameRegistry.addSmelting(Items.SPIDER_EYE, new ItemStack(ModItems.cookedSpiderEye), 0.35f);
		GameRegistry.addSmelting(ModItems.rawHorse, new ItemStack(ModItems.cookedHorse), 0.35f);
		GameRegistry.addSmelting(ModItems.rawVillager, new ItemStack(ModItems.cookedVillager), 0.35f);

	}
}
