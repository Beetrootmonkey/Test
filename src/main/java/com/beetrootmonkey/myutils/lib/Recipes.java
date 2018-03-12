package com.beetrootmonkey.myutils.lib;

import java.util.ArrayList;
import java.util.List;

import com.beetrootmonkey.myutils.MyUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import scala.actors.threadpool.Arrays;

public class Recipes {

	public static List<IRecipe> findRecipes(Object output) {
		List<IRecipe> recipesToReturn = new ArrayList<IRecipe>();
		if (!isItem(output)) {
			return recipesToReturn;
		}

		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			if (equals(output, recipe.getRecipeOutput())) {
				recipesToReturn.add(recipe);
			}
		}
		return recipesToReturn;
	}

	public static void removeRecipes(Object output, Object... input) {
		if (!isItem(output)) {
			return;
		}
		List<IRecipe> recipesToRemove = new ArrayList<IRecipe>();

		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			if (input.length == 0) {
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
		return isObjectInList(Arrays.asList(list), item);
	}

	public static boolean isObjectInList(List<? extends Object> list, Object item) {
		for (Object stack : completeList(list)) {
			if (isItem(stack)) {
				if (equals(stack, item)) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<Object> completeList(List<? extends Object> list) {
		List<Object> listToReturn = new ArrayList<Object>();
		for (Object item : list) {
			if (isItem(item)) {
				listToReturn.add(item);
			} else if (item instanceof List<?>) {
				for (Object oreItem : (List<?>) item) {
					if (isItem(oreItem)) {
						listToReturn.add(oreItem);
					}
				}
			}
		}
		return listToReturn;
	}

	public static boolean isIngredient(IRecipe recipe, Object item) {
		if (recipe instanceof ShapedRecipes) {
			if (!isObjectInList(((ShapedRecipes) recipe).recipeItems, item)) {
				return false;
			}
			return true;
		} else if (recipe instanceof ShapelessRecipes) {
			if (!isObjectInList(((ShapelessRecipes) recipe).recipeItems, item)) {
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
			ItemStack oldStack = (ItemStack)object;
			ItemStack stack = new ItemStack(oldStack.getItem(), 1, oldStack.getMetadata());
			stack.stackSize = 0;
			return stack;
		}
		return null;
	}

	public static boolean isItem(Object object) {
		return object instanceof String || object instanceof Item || object instanceof ItemStack
				|| object instanceof Block;
	}

	public static boolean equals(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem()
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
		for (ItemStack entry : OreDictionary.getOres((String) ore)) {
			ItemStack entryStack = new ItemStack(entry.getItem(), 1, entry.getMetadata());
			entryStack.stackSize = 0;
			if (equals(entryStack, stack)) {
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
