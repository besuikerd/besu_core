package com.besuikerd.test;

import com.besuikerd.core.gui.GuiBaseInventory;
import com.besuikerd.core.gui.element.ElementContainer;
import com.besuikerd.core.gui.element.ElementItemContainerArray;
import com.besuikerd.core.gui.element.ElementLabel;
import com.besuikerd.core.gui.element.ElementPlayerInventory;
import com.besuikerd.core.gui.element.ElementProgressBar;
import com.besuikerd.core.gui.layout.Alignment;
import com.besuikerd.core.gui.layout.HorizontalLayout;
import com.besuikerd.core.gui.layout.VerticalLayout;
import com.besuikerd.core.inventory.InventoryGroup;
import com.besuikerd.core.inventory.TileEntityInventory;

public class TileEntityTestInventory extends TileEntityInventory {
	
	@Override
	public void initInventory(){
		inventory.addGroups(
			new InventoryGroup("first", 9).shiftGroups("second"),
			new InventoryGroup("second", 9).shiftGroups("third"),
			new InventoryGroup("third", 9).shiftGroups("fourth"),
			new InventoryGroup("fourth", 9).shiftGroups(InventoryGroup.PLAYER_INVENTORY_SHIFTGROUPS),
			InventoryGroup.playerInventory().shiftGroups("first"),
			InventoryGroup.playerInventoryHotbar().shiftGroups("first")
		);
	}
	
	
	
	public static class Gui extends GuiBaseInventory{

		
		@Override
		public void init() {
			root.add(
				new ElementLabel("TileEntityTestInventory").align(Alignment.CENTER),
				
				new ElementContainer().layout(new VerticalLayout(0,5)).add(
					new ElementContainer().layout(new HorizontalLayout(5, 0)).add(
						new ElementItemContainerArray(this, inventory.getGroup("first"), 3),
						new ElementItemContainerArray(this, inventory.getGroup("second"), 3)
					),
					new ElementContainer().layout(new HorizontalLayout(5, 0)).add(
						new ElementItemContainerArray(this, inventory.getGroup("fourth"), 3),
						new ElementItemContainerArray(this, inventory.getGroup("third"), 3)
					)
				),
				
				
				new ElementPlayerInventory(this, inventory)
				
			);
		}
		
		public void updateBurn(ElementProgressBar e){
			if(e.getProgress() == e.getMax()){
				e.reset();
			} else{
				e.increment();
			}
		}
	}
	
	
}
