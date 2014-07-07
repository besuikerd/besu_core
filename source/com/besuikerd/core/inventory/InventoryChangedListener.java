package com.besuikerd.core.inventory;

public interface InventoryChangedListener {
	public void onStackChanged(InventoryStack stack);
	public void onGroupChanged(InventoryGroup group);
}
