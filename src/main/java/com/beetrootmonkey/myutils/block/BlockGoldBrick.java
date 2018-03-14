package com.beetrootmonkey.myutils.block;

import java.util.List;
import java.util.Random;

import com.beetrootmonkey.myutils.item.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGoldBrick extends BlockBase {

	public BlockGoldBrick(String name) {
		super(Material.IRON, name);
		setHardness(3f);
		setResistance(5f);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GOLD_INGOT;
	}

	@Override
	public int quantityDropped(Random random) {
		// TODO Auto-generated method stub
		return random.nextInt(4) + 1;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return Math.min(random.nextInt(4 + fortune) + 1, 4);
	}
	
	
	
	
	
	

}
