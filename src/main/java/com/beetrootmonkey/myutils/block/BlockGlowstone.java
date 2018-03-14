package com.beetrootmonkey.myutils.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.beetrootmonkey.myutils.item.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowstone extends BlockBase {

	public BlockGlowstone(String name) {
		super(Material.GLASS, name);
		setHardness(0.3f);
		setSoundType(SoundType.GLASS);
		setLightLevel(1.0F);
	}
	
	/**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return MathHelper.clamp_int(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.GLOWSTONE_DUST;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.SAND;
    }
	
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	protected boolean canSilkHarvest()
    {
        return true;
    }

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
