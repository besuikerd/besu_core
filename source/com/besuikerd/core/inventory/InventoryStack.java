package com.besuikerd.core.inventory;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.BlockSide;
import com.besuikerd.core.packet.IProcessData;
import com.besuikerd.core.utils.BitUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class InventoryStack implements IProcessData{
	protected ItemStack stack;
	protected int stackLimit;
	protected byte sides;
	protected boolean real;
	protected int index;
	
	protected InventoryGroup group;
	
	public ItemStack getStack() {
		return stack;
	}
	
	public BlockSide[] getBlockSides() {
		return BlockSide.fromByte(sides);
	}
	
	public byte getSides(){
		return sides;
	}
	
	public int getStackLimit() {
		return stackLimit;
	}
	
	public boolean isReal() {
		return real;
	}
	
	public InventoryGroup getGroup() {
		return group;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public void read(ByteArrayDataInput in) {
		boolean hasStack = in.readBoolean();
		boolean hasCompound = in.readBoolean();
		
		if(hasStack){
			this.stack = new ItemStack(in.readInt(), in.readInt(), in.readInt());
			if(hasCompound){
				try {
					stack.stackTagCompound = (NBTTagCompound) NBTTagCompound.readNamedTag(in);
				} catch (IOException e) {
					//TODO some proper error handling
					BLogger.warn("failed to read nbt data for ItemStack: %s:%s", e.getClass().getName(), e.getMessage());
				} catch(ClassCastException e){
					BLogger.warn("nbt data from ItemStack didn't begin with a NBTTagCompound!");
				}
			}
		}
	}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeBoolean(stack != null);
		out.writeBoolean(stack != null && stack.stackTagCompound != null);
		
		if(stack != null){
			out.writeInt(stack.itemID);
			out.writeInt(stack.stackSize);
			out.writeInt(stack.getItemDamage());
			if(stack.stackTagCompound != null){
				try {
					NBTBase.writeNamedTag(stack.stackTagCompound, out);
				} catch (IOException e) {
					//TODO some proper error handling
					BLogger.warn("failed to write nbt data for ItemStack: %s:%s", e.getClass().getName(), e.getMessage());
				}
			}
		}
	}
	
	public static class StackBuilder{
		protected boolean isReal;
		protected BlockSide[] blockSides;
		protected int stackLimit;
		
		public StackBuilder() {
			this.isReal = true;
			this.blockSides = BlockSide.values();
			this.stackLimit = 64;
		}
		
		public StackBuilder real(boolean isReal){
			this.isReal = isReal;
			return this;
		}
		
		public StackBuilder sides(BlockSide... sides){
			this.blockSides = sides;
			return this;
		}
		
		public StackBuilder stackLimit(int stackLimit){
			this.stackLimit = stackLimit;
			return this;
		}
		
		public InventoryStack build(){
			InventoryStack inv = new InventoryStack();
			inv.real = this.isReal;
			inv.sides = BlockSide.toByte(this.blockSides);
			inv.stackLimit = this.stackLimit;
			return inv;
		}
	}
}
