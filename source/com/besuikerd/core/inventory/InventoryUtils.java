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
	
	public static void changeGroupStackSize(Inventory inventory, InventoryGroup group, int count){
		for(InventoryStack stack : group.getStacks()){
			if(stack.stack != null){
				stack.stack.stackSize = Math.min(stack.getStackLimit(), stack.stack.stackSize + count);
				if(stack.stack.stackSize <= 0){
					stack.stack = null;
				}
			}
		}
		inventory.onGroupChanged(group);
	}
}
