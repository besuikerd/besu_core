package com.besuikerd.core.inventory;

import java.util.Random;

import com.besuikerd.core.tileentity.TileEntityBesu;
import com.besuikerd.core.utils.NBTUtils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityInventory extends TileEntityBesu implements ISidedInventory, InventoryChangedListener{
	
	protected Inventory inventory;
	
	public TileEntityInventory() {
		inventory = new Inventory();
		inventory.addInventoryChangedListener(this);
		initInventory();
	}
	
	protected void initInventory(){
		
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return inventory.decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return inventory.getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		inventory.setInventorySlotContents(i, itemStack);
	}

	@Override
	public String getInvName() {
		return inventory.getInvName();
	}

	@Override
	public boolean isInvNameLocalized() {
		return inventory.isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public void onInventoryChanged() {
		inventory.onInventoryChanged();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		inventory.openChest();
	}

	@Override
	public void closeChest() {
		inventory.closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return inventory.isItemValidForSlot(i, itemStack);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return inventory.getAccessibleSlotsFromSide(side);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, int side) {
		return inventory.canInsertItem(i, itemStack, side);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int side) {
		return inventory.canExtractItem(i, itemStack, side);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTUtils.readProcessData(inventory, tag, "items");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTUtils.writeProcessData(inventory, tag, "items");
	}
	
	@Override
	public void onTileEntityRemoved(World world, int x, int y, int z) {
		super.onTileEntityRemoved(world, x, y, z);
		
		//drop items on the floor
		Random r = new Random();
		for(int i = 0 ; i < getSizeInventory() ; i++){
			ItemStack stack = getStackInSlot(i);
			InventoryStack inventoryStack = inventory.getInventoryStackAt(i);
			if(stack != null && inventoryStack != null && inventoryStack.isReal() && stack.stackSize > 0){
				EntityItem entityItem = new EntityItem(world, x + r.nextDouble() * 0.8 + 0.1, y + r.nextDouble() * 0.8 + 0.1, z + r.nextDouble() * 0.8 + 0.1, stack);
				entityItem.motionX = r.nextGaussian() * 0.05;
				entityItem.motionY = r.nextGaussian() * 0.05 + 0.2;
				entityItem.motionZ = r.nextGaussian() * 0.05;
				world.spawnEntityInWorld(entityItem);
			}
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public void onGroupChanged(InventoryGroup group) {
	}
	
	@Override
	public void onStackChanged(InventoryStack stack) {
	}
	
}
