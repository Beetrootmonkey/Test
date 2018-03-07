package com.beetrootmonkey.myutils.drops;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.beetrootmonkey.myutils.Reference;
import com.beetrootmonkey.myutils.item.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandlerEntity {

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {
		String prefix = "minecraft:";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			if(file.equals("entities/horse")) {
				evt.getTable().addPool(getInjectPool(file));
			} else if(file.equals("entities/spider")) {
				evt.getTable().removePool("pool1");
				evt.getTable().addPool(getInjectPool(file));
			}
		}
	}

	private LootPool getInjectPool(String entryName) {
		return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "myutils_inject_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(Reference.MOD_ID, "inject/" + name), weight, 0, new LootCondition[0], "myutils_inject_entry");
	}
	
//	@SubscribeEvent
//	public void onEntityDrop(LivingDropsEvent event) {
//		int amount = 1;
//		// cow, extends EntityAnimal. you could use EntityTameable and so on..
//		if (event.getEntityLiving() instanceof EntityHorse &&
//				((EntityHorse)event.getEntityLiving()).isAdultHorse()) {
//			amount = (int) (Math.random() * 3 + 1 + event.getLootingLevel());
//			addDrop(new ItemStack(event.getEntity().isBurning() ? ModItems.cookedHorse : ModItems.rawHorse, amount),
//					event);
//
//		} else if (event.getEntityLiving() instanceof EntitySpider &&
//				event.getEntity().isBurning()) {
//			replaceDrop(Items.SPIDER_EYE, new ItemStack(ModItems.cookedSpiderEye), event);
//
//		}
//	}

	private void addDrop(ItemStack item, LivingDropsEvent event) {
		if(event != null && item != null && item.stackSize > 0 && item.getItem() != null) {
			event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY,
					event.getEntity().posZ, item));
		}
	}
	
	private void replaceDrop(Item filter, ItemStack replacement, LivingDropsEvent event) {
		for (EntityItem eItem : event.getDrops()) {
			if(eItem.getEntityItem().getItem().equals(filter)) {
				int amount = eItem.getEntityItem().stackSize;
				eItem.setEntityItemStack(replacement);
				eItem.getEntityItem().stackSize = amount;
			}
		}
	}
}
