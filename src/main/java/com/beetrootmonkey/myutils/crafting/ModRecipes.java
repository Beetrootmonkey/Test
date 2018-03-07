package com.beetrootmonkey.myutils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.item.ModItems;

public class ModRecipes {

	public static void addRecipes() {
		// shaped crafting recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.copperAxe), "##", "#S",
		" S", '#', ModItems.ingotCopper, 'S', Items.STICK);

		// shapeless crafting recipes
		// GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cornSeed), new
		// ItemStack(ModItems.corn));

		// smelting recipes
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.6f);

	}
}
