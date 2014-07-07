package com.besuikerd.core.inventory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.ClientLogger;
import com.besuikerd.core.gui.element.Element;

/**
 * container that has automated container logic for a
 * {@link TileEntityInventory}. This container will be bound to a
 * TileEntityInventory upon creation from which it can extract information
 * regarding the inventory slots that are stored in the TileEntity.
 * 
 * @author Besuikerd
 * 
 */
public class ContainerBesu extends Container {

	protected Inventory inventory;

	/**
	 * slot index at which slot distribution takes place
	 */
	public static final int INDEX_DISTRIBUTION = -999;

	/**
	 * no modifier for slot click
	 */
	public static final int MOD_NOTHING = 0;

	/**
	 * shift was pressed while clicking
	 */
	public static final int MOD_SHIFT = 1;

	/**
	 * distribution mode
	 */
	public static final int MOD_DISTRIBUTE = 5;

	/**
	 * distribute all items in the player's inventory stack among the slots
	 */
	public static final int TYPE_DISTRIBUTE = 2;

	/**
	 * only put a single item in the slots
	 */
	public static final int TYPE_SINGLE = 6;

	/**
	 * left clicked on a slot
	 */
	public static final int TYPE_CLICK_LEFT = 0;

	/**
	 * right clicked on a slot
	 */
	public static final int TYPE_CLICK_RIGHT = 1;
	
	/**
	 * type nothing when a distribution is started
	 */
	public static final int TYPE_NOTHING = 0;

	public static final int MOUSE_LEFT = Element.BUTTON_LEFT;
	public static final int MOUSE_MIDDLE = Element.BUTTON_MIDDLE;
	public static final int MOUSE_RIGHT = Element.BUTTON_RIGHT;

	/**
	 * binds the given TileEntity to this container, from which it will extract
	 * information regarding inventory slots
	 * 
	 * @param tile
	 * @param player
	 */
	public void bindInventory(Inventory inventory, EntityPlayer player) {
		this.inventory = inventory;

		int counter = 0;
		int playerCounterInv = 9;
		int playerCounterHotbar = 0;
		for (InventoryGroup group : inventory.getGroups()) {
			for (InventoryStack stack : group.getStacks()) {
				if (group.getName().equals(InventoryGroup.PLAYER_INVENTORY)) {
					addSlotToContainer(new Slot(player.inventory, playerCounterInv++, 0, 0));
				} else if (group.getName().equals(InventoryGroup.PLAYER_HOTBAR)) {
					addSlotToContainer(new Slot(player.inventory, playerCounterHotbar++, 0, 0));
				} else {
					addSlotToContainer(new SlotBesu(inventory, stack, counter++));
				}
			}
		}
		return;
	}

	protected Set<Slot> distributeSlots = new HashSet<Slot>();

	@Override
	public ItemStack slotClick(int index, int type, int modifier, EntityPlayer player) {
		ItemStack returnStack = null;
		if (index >= 0) { //regular slot click
			Slot slot = getSlot(index);
			if (modifier == MOD_DISTRIBUTE) { //add this slot to the distributeSlots
				distributeSlots.add(slot);
			}
			ItemStack stackPlayer = (stackPlayer = player.inventory.getItemStack()) != null ? stackPlayer.copy() : null;
			InventoryStack stack = inventory.getInventoryStackAt(index);
			if (stack.isReal()) {
				returnStack = super.slotClick(index, type, modifier, player);
			} else{
				
				returnStack = handleFakeSlot(slot, stack, stackPlayer, type, modifier);
			}
			
			if(modifier != MOD_DISTRIBUTE){
				inventory.onGroupChanged(stack.group);
				inventory.onStackChanged(stack); //a real stack change
			}
			
			
		} else if (index == INDEX_DISTRIBUTION && type != TYPE_NOTHING && type != 4) { //slot distribution!
			ItemStack stackPlayer = (stackPlayer = player.inventory.getItemStack()) != null ? stackPlayer.copy() : null;
			stackPlayer.stackSize = stackPlayer.stackSize / distributeSlots.size();
			Set<InventoryGroup> groups = new HashSet<InventoryGroup>();
			
			boolean containsFakeSlots = false;
			
			for (Slot s : distributeSlots) {
				InventoryStack stack = inventory.getInventoryStackAt(s.getSlotIndex());
				inventory.onStackChanged(stack);
				if(!stack.isReal()){
					containsFakeSlots = true;
					handleFakeSlot(s, inventory.getInventoryStackAt(s.getSlotIndex()), stackPlayer, type == TYPE_DISTRIBUTE ? TYPE_CLICK_LEFT : TYPE_CLICK_RIGHT, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ? MOD_SHIFT : MOD_NOTHING);
				}
				groups.add(stack.group);
			}
			
			if(!containsFakeSlots){
				super.slotClick(index, type, modifier, player);
			}
			distributeSlots.clear();
			
			for(InventoryGroup group : groups){
				inventory.onGroupChanged(group);
			}
			returnStack = stackPlayer;
		} else{
			returnStack = super.slotClick(index, type, modifier, player);
		}
		return returnStack;
		
	}

	private ItemStack handleFakeSlot(Slot slot, InventoryStack stack, ItemStack stackPlayer, int type, int modifier) {
		ItemStack stackSlot = (stackSlot = slot.getStack()) != null ? stackSlot.copy() : null;
		//		ClientLogger.debug("index: %4d, type: %4d, mod: %4d", slot.getSlotIndex(), type, modifier);

		if (stackSlot == null) {
			if (stackPlayer != null) {
				stackPlayer.stackSize = Math.min(stackPlayer.stackSize, stack.getStackLimit()); //limit the playerStack.stackSize to the inventory stack limit
				if (modifier != MOD_SHIFT) { //shiftingclicking on an empty fake slot doesn't do anything
					stackPlayer.stackSize = type == TYPE_CLICK_LEFT ? stackPlayer.stackSize : type == TYPE_CLICK_RIGHT ? 1 : 0; //add either 1 or all
					if (stackPlayer.stackSize != 0) {
						slot.putStack(stackPlayer);
					}
				}
			}
		} else { //only merge stacks if they can be merged
			boolean combinable = InventoryUtils.areItemStacksCombinable(stackSlot, stackPlayer);

			if (!combinable && stackPlayer != null) { //change the stack to the stack held by the player
				stackSlot = stackPlayer.copy();
			}

			int change = type == TYPE_CLICK_LEFT ? combinable ? stackPlayer.stackSize : stack.getStackLimit() : 1;

			if (modifier == MOD_SHIFT) { //removal
				stackSlot.stackSize = Math.max(0, stackSlot.stackSize - change);
			} else { //merge stack in inventory and in player's inv
				stackSlot.stackSize = stackPlayer != null && !stackPlayer.isStackable() || !stackSlot.isStackable() ? 1 : Math.min(stackSlot.stackSize + change, stack.getStackLimit());
			}

			if (stackSlot.stackSize == 0) { //empty the slot if the stacksize is 0
				slot.putStack(null);
			} else {
				slot.putStack(stackSlot);
			}
		}
		return stackSlot;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		ItemStack stack = slot.getStack();

		if (stack == null) {
			return null;
		}

		ItemStack oldStack = stack.copy();
		if (!tryMerge(stack, slotIndex)) {
			return null;
		}
		if (stack.stackSize == 0) {

			slot.putStack(null);
		}

		if (oldStack.stackSize != stack.stackSize) {
			return null;
		}

		slot.onPickupFromSlot(par1EntityPlayer, stack);

		return stack;
	}

	/**
	 * try to merge the given stack somewhere in the inventory
	 * 
	 * @param stack
	 * @param slotIndex
	 * @return
	 */
	protected boolean tryMerge(ItemStack stack, int slotIndex) {
		boolean merged = false;
		InventoryStack invStack = inventory.getInventoryStackAt(slotIndex);

		Iterator<String> iterator = invStack.getGroup().getShiftGroups().iterator();
		while (!merged && iterator.hasNext()) { //iterate over shiftgroups until it finds a valid slot to put the contents in
			InventoryGroup group = inventory.getGroup(iterator.next());
			if (group != null) {
				merged = mergeItemStack(stack, group.getSlotOffset(), group.getSlotOffset() + group.getSize(), group.getShiftStart() == StartPosition.END);
			}
		}
		return merged;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	public Inventory inventory() {
		return inventory;
	}
}
