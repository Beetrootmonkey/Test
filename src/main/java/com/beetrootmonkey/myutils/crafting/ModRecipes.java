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
		GameRegistry.addRecipe(new ItemStack(ModBlocks.gold_brick), "##", "##", '#', Items.GOLD_INGOT);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLD_INGOT, 4), ModBlocks.gold_brick);

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
		removeRecipe(ShapedRecipes.class, Blocks.TORCH, "stickWood");

	}

	public static void removeRecipe(Object output, Object... input) {
		if (!isItem(output)) {
			return;
		}
		List<ItemStack> inputFilter = new ArrayList<ItemStack>();
		List<IRecipe> recipesToRemove = new ArrayList<IRecipe>();

		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			if (inputFilter.isEmpty()) {
				if (equals(output, recipe.getRecipeOutput())) {
					recipesToRemove.add(recipe);
				}

			} else {
				boolean validRecipe = equals(output, recipe.getRecipeOutput());
				if (validRecipe) {
					for (Object objIn : input) {
						if (!isIngredient(recipe, objIn)) {
							validRecipe = false;
							break;
						}
					}
					if (validRecipe) {
						recipesToRemove.add(recipe);
					}
				}
			}
		}

		CraftingManager.getInstance().getRecipeList().removeAll(recipesToRemove);
		if (output instanceof String) {
			MyUtils.logger.info("Removed " + recipesToRemove.size() + " recipes with output " + (String) output + "!");
		} else {
			MyUtils.logger.info("Removed " + recipesToRemove.size() + " recipes with output "
					+ convertToStack(output).getUnlocalizedName() + "!");
		}

	}

	public static boolean isObjectInList(Object[] list, Object item) {
		return isItemStackInList(Arrays.asList(list), item);
	}
	
	public static boolean isObjectInList(List<Object> list, Object item) {
		for (Object stack : list) {
			if(isItem(stack) ) {
				if (equals(stack, item)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isItemStackInList(List<ItemStack> list, Object item) {
		for (Object stack : list) {
			if(isItem(stack) ) {
				if (equals(stack, item)) {
					return true;
				}
			}
		}
		return false;
	}
	

	public static boolean isIngredient(IRecipe recipe, Object item) {
		if (recipe instanceof ShapedRecipes) {
			if (!isObjectInList(((ShapedRecipes) recipe).recipeItems, item)) {
				return false;
			}
			return true;
		} else if (recipe instanceof ShapelessRecipes) {
			if (!isItemStackInList(((ShapelessRecipes) recipe).recipeItems, item)) {
				return false;
			}
			return true;
		} else if (recipe instanceof ShapedOreRecipe) {
			if (!isObjectInList(((ShapedOreRecipe) recipe).getInput(), item)) {
				return false;
			}
			return true;
		} else if (recipe instanceof ShapelessOreRecipe) {
			if (!isObjectInList(((ShapelessOreRecipe) recipe).getInput(), item)) {
				return false;
			}
			return true;
		}

		return false;
	}

	public static ItemStack convertToStack(Object object) {
		if (object instanceof Block) {
			return new ItemStack(Item.getItemFromBlock((Block) object), 0);
		} else if (object instanceof Item) {
			return new ItemStack((Item) object, 0);
		} else if (object instanceof ItemStack) {
			return (ItemStack) object;
		}
		return null;
	}

	public static boolean isItem(Object object) {
		return object instanceof String || object instanceof Item || object instanceof ItemStack
				|| object instanceof Block;
	}

	public static boolean equals(ItemStack stack1, ItemStack stack2) {
		return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem()
				&& (stack1.stackSize == 0 || stack2.stackSize == 0 || stack1.stackSize == stack2.stackSize)
				&& stack1.getMetadata() == stack2.getMetadata();
	}

	public static boolean equals(Object object1, Object object2) {
		if (!isItem(object1) || !isItem(object2)) {
			return false;
		}

		if (object1 instanceof String) {
			if (object2 instanceof String) {
				return equals((String) object1, (String) object2);
			} else {
				return equals((String) object1, convertToStack(object2));
			}
		} else {
			if (object2 instanceof String) {
				return equals(convertToStack(object1), (String) object2);
			} else {
				return equals(convertToStack(object1), convertToStack(object2));
			}
		}
	}

	public static boolean equals(ItemStack stack, String ore) {
		for(ItemStack entry : OreDictionary.getOres((String)ore)) {
			entry.stackSize = 0;
			if (equals(entry, stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean equals(String ore, ItemStack stack) {
		return equals(stack, ore);
	}

	public static boolean equals(String ore1, String ore2) {
		return ore1.equals(ore2);
	}
}