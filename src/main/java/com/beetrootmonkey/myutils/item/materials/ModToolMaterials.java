package com.beetrootmonkey.myutils.item.materials;

import com.beetrootmonkey.myutils.item.ModItems;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModToolMaterials {
	public static ToolMaterial COPPER = initialize(EnumHelper.addToolMaterial("COPPER", 2, 220, 5f, 2f, 12),
			ModItems.ingotCopper);

	public static ToolMaterial initialize(ToolMaterial material, Item repairItem) {
		material.setRepairItem(new ItemStack(repairItem));
		return material;
	}
}
