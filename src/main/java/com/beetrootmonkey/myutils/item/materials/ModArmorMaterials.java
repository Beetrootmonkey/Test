package com.beetrootmonkey.myutils.item.materials;

import com.beetrootmonkey.myutils.Reference;
import com.beetrootmonkey.myutils.item.ModItems;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmorMaterials {
	public static final ArmorMaterial COPPER = initialize(EnumHelper.addArmorMaterial("COPPER", Reference.MOD_ID + ":copper", 15,
			new int[] { 2, 5, 6, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F), ModItems.ingotCopper);

	
	public static ArmorMaterial initialize(ArmorMaterial material, Item repairItem) {
		material.customCraftingMaterial = repairItem;
		return material;
	}
}
