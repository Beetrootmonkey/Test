package com.beetrootmonkey.myutils.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class WorldGenHandler {	
	@SubscribeEvent
	public void onDecorate2(DecorateBiomeEvent.Decorate event) {
		if(event.getType() == DecorateBiomeEvent.Decorate.EventType.GRASS) {
			event.setResult(Result.DENY);
		}
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onPopulate2(PopulateChunkEvent.Populate event) {
		if(event.getType() == PopulateChunkEvent.Populate.EventType.GLOWSTONE) {
			Random rand = new Random(event.getWorld().getSeed());
			BlockPos blockPos = new BlockPos(event.getChunkX()*16, 0, event.getChunkZ()*16);
//			new WorldGenGlowStoneCrystal().generate(event.getWorld(), rand, blockPos);
			for (int j1 = 0; j1 < rand.nextInt(rand.nextInt(10) + 1); ++j1)
	        {
				new WorldGenGlowStoneCrystal().generate(event.getWorld(), rand, blockPos.add(rand.nextInt(16) + 8, rand.nextInt(120) + 4, rand.nextInt(16) + 8));
	        }
			event.setResult(Result.DENY);
		}
	}
}
