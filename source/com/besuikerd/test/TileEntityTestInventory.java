package com.besuikerd.test;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import com.besuikerd.core.ClientLogger;
import com.besuikerd.core.gui.GuiBaseInventory;
import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementContainer;
import com.besuikerd.core.gui.element.ElementItemContainerArray;
import com.besuikerd.core.gui.element.ElementLabel;
import com.besuikerd.core.gui.element.ElementPlayerInventory;
import com.besuikerd.core.gui.element.ElementProgressBar;
import com.besuikerd.core.gui.layout.Alignment;
import com.besuikerd.core.gui.layout.HorizontalLayout;
import com.besuikerd.core.inventory.CraftingGroup;
import com.besuikerd.core.inventory.InventoryGroup;
import com.besuikerd.core.inventory.InventoryStack;
import com.besuikerd.core.inventory.TileEntityInventory;
import com.besuikerd.core.utils.Tuple;

public class TileEntityTestInventory extends TileEntityInventory {
	
	@Override
	public void initInventory(){
		inventory.addGroups(
			new InventoryGroup("craft", 9).shiftGroups(InventoryGroup.PLAYER_INVENTORY_SHIFTGROUPS),
			new InventoryGroup("result", 1, new InventoryStack.StackBuilder()),
			InventoryGroup.playerInventory().shiftGroups("craft"),
			InventoryGroup.playerInventoryHotbar().shiftGroups("craft")
		);
	}
	
	public void debugFakeSlots(Element e){
		InventoryGroup group = inventory.getGroup("result");
		
		
		List<Tuple> tuples = new ArrayList<Tuple>();
		for(InventoryStack stack : group.getStacks()){
			if(stack.getStack() != null){
				tuples.add(new Tuple(stack.getStack().itemID, stack.getStack().stackSize, stack.getStack().stackTagCompound));
			}
		}
		ClientLogger.debug("items: %s", tuples);
	}
	
	public static class Gui extends GuiBaseInventory{

		
		@Override
		public void init() {
			root.add(
				new ElementLabel("TileEntityTestInventory").align(Alignment.CENTER),
				
				new ElementContainer().layout(new HorizontalLayout(5,0)).add(
					new ElementItemContainerArray(this, inventory.getGroup("craft"), 3),
					ElementProgressBar.progressBarArrow().align(Alignment.CENTER),
					new ElementItemContainerArray(this, inventory.getGroup("result"), 1).align(Alignment.CENTER)
//					new ElementButton("Print").align(Alignment.CENTER).trigger(Trigger.PRESSED, "debugFakeSlots")
				).align(Alignment.CENTER),
				
				
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
	
	@Override
	public void onGroupChanged(InventoryGroup group) {
		ClientLogger.debug("group: %s", group.getName());
		if(group.getName().equals("craft")){
			
			ItemStack result = CraftingManager.getInstance().findMatchingRecipe(new CraftingGroup(group), this.worldObj);
			
			ClientLogger.debug("result: %s", result);
			
			setInventorySlotContents(inventory.getGroup("result").getStacks().iterator().next().getIndex(), result);
		}
	}
}
