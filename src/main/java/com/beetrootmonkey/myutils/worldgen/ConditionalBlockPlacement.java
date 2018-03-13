package com.beetrootmonkey.myutils.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@FunctionalInterface
public interface ConditionalBlockPlacement {
	public boolean run(World worldIn, Random rand, BlockPos position);
}
