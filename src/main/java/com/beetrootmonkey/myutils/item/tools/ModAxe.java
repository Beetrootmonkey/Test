package com.beetrootmonkey.myutils.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemBase;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

public class ModAxe extends ItemAxe implements ItemModelProvider{

	protected String name;
	
	public ModAxe(ToolMaterial material, String name) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MyUtils.creativeTab);
	}
	
	@Override
	public void registerItemModel(Item item) {
		MyUtils.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public ModAxe setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}

