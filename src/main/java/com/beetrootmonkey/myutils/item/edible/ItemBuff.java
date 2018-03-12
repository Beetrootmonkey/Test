package com.beetrootmonkey.myutils.item.edible;

import java.util.ArrayList;
import java.util.List;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemBase;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBuff extends ItemModFood implements ItemModelProvider {

//	private List<PotionEffect> effects;
	protected PotionEffect effect;
	protected float chance;

//	@Override
//	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
//		super.onFoodEaten(stack, world, player);
//		for (PotionEffect effect : effects) {
//			if (!world.isRemote) {
//				player.addPotionEffect(effect);
//			}
//		}
//	}

//	public ItemBuff(String name, int amount, float saturation, List<PotionEffect> effects) {
//		super(name, amount, saturation);
//		this.effects = effects;
//	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		if (!world.isRemote && Math.random() < chance) {
			player.addPotionEffect(effect);
		}
	}
	
	public ItemBuff(String name, int amount, float saturation, PotionEffect effect, float chance) {
		super(name, amount, saturation);
		this.effect = effect;
		this.chance = chance;
	}
}
