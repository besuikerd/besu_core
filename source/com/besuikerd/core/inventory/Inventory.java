package com.besuikerd.core.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.ls.LSInput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

import com.besuikerd.core.BlockSide;
import com.besuikerd.core.ClientLogger;
import com.besuikerd.core.packet.IProcessData;
import com.besuikerd.core.utils.IntList;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class Inventory implements ISidedInventory, IProcessData{
	
	protected List<InventoryStack> stacks;
	
	protected Map<String, InventoryGroup> groups;
	
	protected IntList[] accessibleSides;
	
	protected String name;
	
	protected Set<InventoryChangedListener> listeners;
	
	public Inventory() {
		this.name = "besu.inv";
		this.stacks = new ArrayList<InventoryStack>();
		this.groups = new LinkedHashMap<String, InventoryGroup>();
		this.accessibleSides = new IntList[6];
		for(int i = 0 ; i < accessibleSides.length ; i++){
			accessibleSides[i] = IntList.rawIntList();
		}
		this.listeners = new HashSet<InventoryChangedListener>();
	}
	
	public Inventory addGroup(InventoryGroup group){
		group.slotOffset = stacks.size();
		addStacks(group.getStacks());
		groups.put(group.getName(), group);
		return this;
	}
	
	public Inventory addGroups(InventoryGroup... groups){
		for(InventoryGroup group : groups){
			addGroup(group);
		}
		return this;
	}
	
	private void addStacks(Collection<InventoryStack> stacks){
		for(InventoryStack stack : stacks){
			addStack(stack);
		}
	}
	
	private void addStack(InventoryStack stack){
		stacks.add(stack);
		stack.index = stacks.size() - 1;
		if(stack.isReal() && InventoryGroup.PLAYER_HOTBAR.equals(stack.group) && !InventoryGroup.PLAYER_INVENTORY.equals(stack.group)){
			for(BlockSide b : stack.getBlockSides()){
				accessibleSides[b.ordinal()].add(stacks.size() - 1);
			}
		}
	}
	
	public InventoryGroup getGroup(String name){
		return groups.get(name);
	}
	
	public Collection<InventoryGroup> getGroups(){
		return groups.values();
	}
	
	public Set<InventoryGroup> getGroups(String... names){
		Set<InventoryGroup> foundGroups = new HashSet<InventoryGroup>();
		for(String name : names){
			InventoryGroup group = getGroup(name);
			if(name != null){
				foundGroups.add(group);
			}
		}
		return foundGroups;
	}
	
	@Override
	public int getSizeInventory() {
		return stacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return stacks.get(i).stack;
	}
	
	public InventoryStack getInventoryStackAt(int index){
		return stacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int i, int amount) {
		InventoryStack invStack = stacks.get(i);
		ItemStack stack = invStack.stack;
		int toRemove = Math.min(stack.stackSize, amount);
		ItemStack decrStack = stack.copy();
		stack.stackSize -= toRemove;
		decrStack.stackSize = toRemove;
		if(stack.stackSize == 0){
			invStack.stack = null;
		}
		return decrStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(stacks.size() > i){
			ItemStack stack = stacks.get(i).stack;
			if(stack != null){
				stacks.set(i, null);
				return stack;
			}
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		InventoryStack invStack = stacks.get(i);
		if(invStack != null){
			invStack.stack = itemstack;
		}
	}

	@Override
	public String getInvName() {
		return name;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	
	@Override
	public void onInventoryChanged() {
	}
	
	public void onGroupChanged(InventoryGroup group){
		for(InventoryChangedListener listener : listeners){
			listener.onGroupChanged(group);
		}
	}
	
	public void onStackChanged(InventoryStack stack){
		for(InventoryChangedListener listener : listeners){
			listener.onStackChanged(stack);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i < stacks.size()){
			InventoryStack invStack = stacks.get(i);
			return invStack.stack == null || invStack.real && itemstack.isItemEqual(invStack.stack);
		}
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return accessibleSides[side].rawIntArray();
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, int side) {
		if(i < stacks.size()){
			InventoryStack invStack = stacks.get(i);
			return invStack.stack == null || invStack.real && itemStack.isItemEqual(invStack.stack) && BlockSide.isSideSelected(invStack.sides, side);
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int side) {
		if(i < stacks.size()){
			InventoryStack invStack = stacks.get(i);
			return invStack.stack == null || invStack.real && itemStack.isItemEqual(invStack.stack) && BlockSide.isSideSelected(invStack.sides, side);
		}
		return false;
	}

	@Override
	public void read(ByteArrayDataInput in) {
		int length = in.readInt();
		for(int i = 0 ; i < length && i < stacks.size() ; i++){
			stacks.get(i).read(in);
		}
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt(stacks.size()); //write the amount of groups
		for(InventoryStack stack : stacks){
			stack.write(out);
		}
	}

	public void addInventoryChangedListener(InventoryChangedListener listener){
		listeners.add(listener);
	}
	
	public void removeInventoryChangedListener(InventoryChangedListener listener){
		listeners.remove(listener);
	}
}
