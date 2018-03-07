package com.beetrootmonkey.myutils.item;

import com.beetrootmonkey.myutils.MyUtils;
import com.beetrootmonkey.myutils.item.armor.ItemModArmor;
import com.beetrootmonkey.myutils.item.edible.ItemBuff;
import com.beetrootmonkey.myutils.item.edible.ItemModFood;
import com.beetrootmonkey.myutils.item.fuel.ItemFuel;
import com.beetrootmonkey.myutils.item.materials.ModToolMaterials;
import com.beetrootmonkey.myutils.item.materials.ModArmorMaterials;
import com.beetrootmonkey.myutils.item.tools.ItemModAxe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

	public static ItemBase ingotCopper;
	public static ItemModAxe copperAxe;
	public static ItemFuel pelletFuel;
	public static ItemModFood rawHorse;
	public static ItemModFood cookedHorse;
	public static ItemBuff rawVillager;
	public static ItemModFood cookedVillager;
	public static ItemBase gelatin;
	public static ItemModFood cookedSpiderEye;
	public static ItemBase chunkCopper;
	public static ItemModArmor helmetCopper;
	public static ItemModArmor chestplateCopper;
	public static ItemModArmor leggingsCopper;
	public static ItemModArmor bootsCopper;

	public static void init() {
		ingotCopper = register(new ItemBase("ingotCopper"));
		copperAxe = register(new ItemModAxe("copperAxe", ModToolMaterials.COPPER, 7f, -3f));
		pelletFuel = register(new ItemFuel("pelletFuel", 200));
		rawHorse = register(new ItemModFood("rawHorse", 3, 1.8f));
		cookedHorse = register(new ItemModFood("cookedHorse", 8, 12.8f));
		rawVillager = register(new ItemBuff("rawVillager", 2, 0.4f,
				new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:hunger"), 600, 0),
				0.8f));
		cookedVillager = register(new ItemModFood("cookedVillager", 6, 9.6f));
		gelatin = register(new ItemBase("gelatin"));
		cookedSpiderEye = register(new ItemModFood("cookedSpiderEye", 4, 5f));
		chunkCopper = register(new ItemBase("chunkCopper"));
		helmetCopper = register(new ItemModArmor("helmetCopper", ModArmorMaterials.COPPER, EntityEquipmentSlot.HEAD));
		chestplateCopper = register(new ItemModArmor("chestplateCopper", ModArmorMaterials.COPPER, EntityEquipmentSlot.CHEST));
		leggingsCopper = register(new ItemModArmor("leggingsCopper", ModArmorMaterials.COPPER, EntityEquipmentSlot.LEGS));
		bootsCopper = register(new ItemModArmor("bootsCopper", ModArmorMaterials.COPPER, EntityEquipmentSlot.FEET));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider) item).registerItemModel(item);
		}

		return item;
	}
	
	public static void registerOres() {
		OreDictionary.registerOre("slimeball", gelatin);
	}
}