package com.beetrootmonkey.myutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

public class ModBlocks {

	public static BlockOre oreCopper;
	public static BlockBase gold_brick;
	public static BlockGlowstone glowstone;

	public static void init() {
		oreCopper = register(new BlockOre("oreCopper"));
		gold_brick = register(new BlockGoldBrick("gold_brick"));
		glowstone = register(new BlockGlowstone("glowstone"));
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {

		GameRegistry.register(block);
		if (itemBlock != null) {
			GameRegistry.register(itemBlock);
		}

		if (block instanceof ItemModelProvider) {
			((ItemModelProvider) block).registerItemModel(itemBlock);
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
