package com.beetrootmonkey.myutils.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;

import java.util.Random;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ModItems;

public class BlockOre extends BlockBase {

	public BlockOre(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(5f);
	}
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return (int)(Math.random() * 3);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.chunkCopper;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return random.nextInt(fortune + 1) + 1;
	}

}
