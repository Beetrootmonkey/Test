package com.beetrootmonkey.myutils.client;

import com.beetrootmonkey.myutils.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ModItems;

public class ModTab extends CreativeTabs {

	public ModTab() {
		super(Reference.MOD_ID);
		// setBackgroundImageName("tutorialmod.png");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.ingotCopper; // shown icon on creative tab
	}

	@Override
	public boolean hasSearchBar() {
		return false; // return false if you don't want search bar
	}

}
