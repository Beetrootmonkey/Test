package com.beetrootmonkey.myutils.item;

import com.beetrootmonkey.myutils.MyUtils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements ItemModelProvider{
 
 protected String name;
 
 public ItemBase(String name) {
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
 public ItemBase setCreativeTab(CreativeTabs tab) {
 super.setCreativeTab(tab);
 return this;
 }

}
