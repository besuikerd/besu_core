package com.besuikerd.core.inventory;

import net.minecraft.inventory.Slot;

import com.besuikerd.core.ClientLogger;

public class SlotBesu extends Slot {
	
	protected Inventory inventory;
	protected InventoryStack stack;
	
	public SlotBesu(Inventory inventory, InventoryStack stack, int slotIndex, int xDisplay, int yDisplay){
		super(inventory, slotIndex, xDisplay, yDisplay);
		this.inventory = inventory;
		this.stack = stack;
		this.xDisplayPosition = -1000;
		this.yDisplayPosition = -1000;
	}
	
	public SlotBesu(Inventory inventory, InventoryStack stack, int slotIndex){
		this(inventory, stack, slotIndex, 0, 0);
	}

	@Override
	public void onSlotChanged() {
		inventory.onInventoryChanged();
	}
	
	@Override
	public int getSlotStackLimit() {
		return stack.getStackLimit();
	}
}
