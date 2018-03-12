package com.beetrootmonkey.myutils.drops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.beetrootmonkey.myutils.Reference;
import com.beetrootmonkey.myutils.block.ModBlocks;
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
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandlerBlock {

	@SubscribeEvent
	public void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
		if (event.getState().getBlock().equals(ModBlocks.oreCopper) && Math.random() < 0.5) {
			replaceDrop(ModItems.chunkCopper, new ItemStack(ModItems.ingotCopper), event);
		}
	}


	private void replaceDrop(Item filter, ItemStack replacement, BlockEvent.HarvestDropsEvent event) {
		List<ItemStack> toRemove = new ArrayList<ItemStack>();
		for (ItemStack itemStack : event.getDrops()) {
			if (itemStack.getItem().equals(filter)) {
				replacement.stackSize = itemStack.stackSize;
				toRemove.add(itemStack);
				break;
			}
		}
		
		if(!toRemove.isEmpty()) {
			event.getDrops().removeAll(toRemove);
			event.getDrops().add(replacement);
		}
		
	}
}
