package com.besuikerd.core.gui.element;

import com.besuikerd.core.gui.GuiBaseInventory;
import com.besuikerd.core.gui.layout.LayoutDimension;
import com.besuikerd.core.gui.layout.VerticalLayout;
import com.besuikerd.core.inventory.Inventory;
import com.besuikerd.core.inventory.InventoryGroup;

public class ElementPlayerInventory extends ElementNamedContainer{
	
	public ElementPlayerInventory(GuiBaseInventory inventory, Inventory inv, int x, int y) {
		super(x, y, 0, 0, "Inventory");
		this.layout = new VerticalLayout();
		this.heightDimension = LayoutDimension.WRAP_CONTENT;
		this.widthDimension = LayoutDimension.WRAP_CONTENT;
		add(new ElementItemContainerArray(inventory, inv.getGroup(InventoryGroup.PLAYER_INVENTORY), 9).paddingBottom(5));
		add(new ElementItemContainerArray(inventory, inv.getGroup(InventoryGroup.PLAYER_HOTBAR), 9));
	}

	public ElementPlayerInventory(GuiBaseInventory inventory, Inventory inv) {
		this(inventory, inv, 0, 0);
	}
}
