package com.besuikerd.core.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class CraftingGroup extends InventoryCrafting{

	public CraftingGroup(InventoryGroup group) {
		super(new ContainerBesu(), 3, 3);
		int i = 0;
		for(InventoryStack s : group.getStacks()){
			setInventorySlotContents(i++, s.getStack());
		}
	}
	
}
