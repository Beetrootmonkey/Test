package com.beetrootmonkey.myutils.item.edible;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBuff extends ItemFood {
	private List<PotionEffect> effects;

	@Override
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		for (PotionEffect effect : effects) {
			if (!world.isRemote) {
				player.addPotionEffect(effect);
			}
		}
	}

	public ItemBuff(int amount, float saturation, List<PotionEffect> effects) {
		super(amount, saturation, false);
		this.effects = effects;
		this.setAlwaysEdible();
	}
}
