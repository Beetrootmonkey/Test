package com.beetrootmonkey.myutils.item.armor;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.ItemModelProvider;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemModArmor extends ItemArmor implements ItemModelProvider {

	private String name;

	public ItemModArmor(String name, ArmorMaterial material, EntityEquipmentSlot slot) {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
	}

	@Override
	public void registerItemModel(Item item) {
		MyUtils.proxy.registerItemRenderer(this, 0, name);
	}
}
