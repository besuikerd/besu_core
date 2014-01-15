package com.besuikerd.core.inventory;

import net.minecraft.item.ItemStack;

public class InventoryUtils {
	
	/**
	 * checks wheter the two stacks can be combined together, not looking at the stacksizes, only item types
	 * @param first
	 * @param snd
	 * @return
	 */
	public static boolean areItemStacksCombinable(ItemStack first, ItemStack snd){
		return	first == null && snd == null 
				|| first != null && snd != null 
					&& ItemStack.areItemStackTagsEqual(first, snd)
					&& first.isItemEqual(snd);
	}
}
