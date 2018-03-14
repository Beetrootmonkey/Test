package com.beetrootmonkey.myutils.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenHandler {
	@SubscribeEvent
	public static void onDecorate(Decorate event) {
		if(event.getType().equals(DecorateBiomeEvent.Decorate.EventType.GRASS)) {
			event.setResult(Result.DENY);
		}
	}
	
	@SubscribeEvent
	public static void onPopulate(Populate event) {
		if(event.getType().equals(PopulateChunkEvent.Populate.EventType.GLOWSTONE)) {
			Random rand = new Random(event.getWorld().getSeed());
			BlockPos blockPos = new BlockPos(event.getChunkX()*16, 0, event.getChunkZ()*16);
//			new WorldGenGlowStoneCrystal().generate(event.getWorld(), rand, blockPos);
			event.setResult(Result.DENY);
			event.setCanceled(true);
		}
	}
}
