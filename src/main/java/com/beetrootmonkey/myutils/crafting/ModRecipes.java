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
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.block.ModBlocks;
import com.beetrootmonkey.myutils.config.Config;
import com.beetrootmonkey.myutils.item.ModItems;

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

		removeRecipe(Items.BREAD);
		removeRecipe(ShapedRecipes.class, Blocks.TORCH, Items.COAL);

	}
	
	public static void removeRecipe(Object output) {
		removeRecipe(null, output);
	}

	public static void removeRecipe(Class clazz, Object output, Object... input) {
		ItemStack outputFilter = null;
		List<ItemStack> inputFilter = new ArrayList<ItemStack>();
		List<IRecipe> recipesToRemove = new ArrayList<IRecipe>();

		if (output instanceof Block) {
			Block block = (Block) output;
			outputFilter = new ItemStack(Item.getItemFromBlock(block), 0);
		} else if (output instanceof Item) {
			Item item = (Item) output;
			outputFilter = new ItemStack(item, 0);
		} else if (output instanceof ItemStack) {
			ItemStack stack = (ItemStack) output;
			outputFilter = stack;
		}

		if (outputFilter == null) {
			return;
		}

		for (Object in : input) {
			if (in instanceof Block) {
				Block block = (Block) in;
				inputFilter.add(new ItemStack(Item.getItemFromBlock(block), 0));
			} else if (in instanceof Item) {
				Item item = (Item) in;
				inputFilter.add(new ItemStack(item, 0));
			} else if (in instanceof ItemStack) {
				ItemStack item = (ItemStack) in;
				inputFilter.add(item);
			}
		}

		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			if (inputFilter.isEmpty()) {
				if (areItemStacksSame(outputFilter, recipe.getRecipeOutput())) {
					recipesToRemove.add(recipe);

				}

			} else {
				if(clazz.isInstance(recipe)) {
					if (clazz == ShapedRecipes.class) {
						ShapedRecipes r = (ShapedRecipes) recipe;
						// Check if output equals filter
						boolean validRecipe = areItemStacksSame(outputFilter, r.getRecipeOutput());

						if (validRecipe) {
							// Iterate through all filters
							for (ItemStack filter : inputFilter) {
								// For each filter:
								if (isItemStackInList(r.recipeItems, filter)) {
									validRecipe = false;
									break;
								}
							}
						}

						if (validRecipe) {
							recipesToRemove.add(recipe);
						}
					} else if (clazz == ShapelessRecipes.class) {
						ShapelessRecipes r = (ShapelessRecipes) recipe;
						// Check if output equals filter
						boolean validRecipe = areItemStacksSame(outputFilter, r.getRecipeOutput());

						if (validRecipe) {
							// Iterate through all filters
							for (ItemStack filter : inputFilter) {
								// For each filter:
								if (isItemStackInList(r.recipeItems, filter)) {
									validRecipe = false;
									break;
								}
							}
						}

						if (validRecipe) {
							recipesToRemove.add(recipe);
						}
					}
				}
			}
		}

		CraftingManager.getInstance().getRecipeList().removeAll(recipesToRemove);
		MyUtils.logger.info("Removed " + recipesToRemove.size() + " recipes with output " + outputFilter.getUnlocalizedName() + "!");
	}

	public static boolean isItemStackInList(ItemStack[] list, ItemStack item) {
		return isItemStackInList(Arrays.asList(list), item);
	}

	public static boolean isItemStackInList(List<ItemStack> list, ItemStack item) {
		for (ItemStack stack : list) {
			if (areItemStacksSame(stack, item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean areItemStacksSame(ItemStack stack1, ItemStack stack2) {
		return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem()
				&& (stack1.stackSize == 0 || stack2.stackSize == 0 || stack1.stackSize == stack2.stackSize)
				&& stack1.getMetadata() == stack2.getMetadata();
	}
}
