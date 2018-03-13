package com.beetrootmonkey.myutils.worldgen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ConditionalBlockState {
	IBlockState blockState = null;
	ConditionalBlockPlacement code = null;
	
	
	public IBlockState getBlockState(World worldIn, Random rand, BlockPos position) {
		return code != null && !code.run(worldIn, rand, position) ? null : blockState;	
	}
	
	public ConditionalBlockState(IBlockState blockState) {
		this(blockState, null);
	}
	
	public ConditionalBlockState(IBlockState blockState, ConditionalBlockPlacement code) {
		this.blockState = blockState;
		this.code = code;
	}
}
