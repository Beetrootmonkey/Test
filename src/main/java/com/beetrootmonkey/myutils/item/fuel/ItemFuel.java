package com.beetrootmonkey.myutils.item.fuel;

import net.minecraft.item.Item;

public class ItemFuel extends Item implements IFuel
{
	int burnTime;
	
	@Override
	public int getBurnTime()
	{
		return burnTime;
	}
	
	public ItemFuel(int burnTime)
	{
		super();
		this.burnTime = burnTime;
	}
}
