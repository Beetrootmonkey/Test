package com.beetrootmonkey.myutils.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;

public class Config {
	public static boolean sheepDrops = true;
	public static boolean squidDrops = true;
	public static boolean spiderDrops = true;
	public static boolean horseDrops = true;
	public static boolean coalPellet = true;
	public static boolean charcoalPellet = true;
	public static boolean torch = true;
	public static boolean jellyCube = true;
	public static boolean gelatin = true;
	public static boolean altBread = true;
	public static boolean easyPotions = false;
	public static boolean altCookie = true;

	public static Configuration config = null;

	public static String CATEGORY_DROPS = "drops";
	public static String CATEGORY_RECIPES = "recipes";

	public static List<IConfigElement> getElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(config.getCategory(CATEGORY_DROPS)).getChildElements());
		list.addAll(new ConfigElement(config.getCategory(CATEGORY_RECIPES)).getChildElements());

		return list;
	}
}