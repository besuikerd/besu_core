package com.besuikerd.core.gui.element;

import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.Slot;

import com.besuikerd.core.gui.GuiBaseInventory;
import com.besuikerd.core.gui.layout.HorizontalLayout;
import com.besuikerd.core.gui.layout.LayoutDimension;
import com.besuikerd.core.inventory.InventoryGroup;
import com.besuikerd.core.inventory.InventoryStack;

public class ElementItemContainerArray extends ElementContainer{
	
	protected int columns;
	
	public ElementItemContainerArray(GuiBaseInventory inventory, InventoryGroup group, int columns, int x, int y) {
		super(x, y, 0, (int) (Math.ceil(group.getSize() / columns) * 18));
		this.columns = columns;
		this.layout = new HorizontalLayout();
		this.heightDimension = LayoutDimension.WRAP_CONTENT;
		inventory.registerGroup(group, this);
		for(InventoryStack stack : group.getStacks()){
			add(new ElementItemContainer(stack));
		}
	}
	
	public ElementItemContainerArray(GuiBaseInventory inventory, InventoryGroup group , int columns) {
		this(inventory, group, columns, 0, 0);
	}
	
	public void alignSlots(List<Slot> slots){
		Iterator<Slot> iSlot = slots.iterator();
		Iterator iCont = elements.iterator();
		while(iSlot.hasNext() && iCont.hasNext()){
			((ElementItemContainer) iCont.next()).alignSlot(iSlot.next());
		}
	}
	
	
	
	@Override
	public void dimension() {
		super.dimension();
		this.width = columns * 18 + paddingLeft + paddingRight;
	}
	
	
}
