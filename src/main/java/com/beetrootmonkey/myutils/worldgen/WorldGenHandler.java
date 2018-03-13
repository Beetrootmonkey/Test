package com.beetrootmonkey.myutils.worldgen;

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
			event.setResult(Result.DENY);
		}
	}
}
