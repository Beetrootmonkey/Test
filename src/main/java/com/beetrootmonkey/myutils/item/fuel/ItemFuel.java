package com.beetrootmonkey.myutils.item.fuel;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemBase;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

import net.minecraft.item.Item;

public class ItemFuel extends ItemBase implements IFuel 
{
	int burnTime;
	
	@Override
	public int getBurnTime()
	{
		return burnTime;
	}
	
	public ItemFuel(String name, int burnTime)
	{
		super(name);
		this.burnTime = burnTime;
	}
}
