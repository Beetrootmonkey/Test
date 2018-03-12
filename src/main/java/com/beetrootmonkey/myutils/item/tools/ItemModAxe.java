package com.beetrootmonkey.myutils.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemBase;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

public class ItemModAxe extends ItemAxe implements ItemModelProvider {

	protected String name;

	public ItemModAxe(String name, ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MyUtils.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		MyUtils.proxy.registerItemRenderer(this, 0, name);
	}

}
