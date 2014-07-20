package com.besuikerd.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface TileEntityLogic {
	public void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack);
	public void onRemoveTileEntity(World world, int x, int y, int z, Block block, int meta);
	public void onTileEntityRemoved(World world, int x, int y, int z, Block block, int meta);
	
	public void writeToNBT(NBTTagCompound nbt);
	public void readFromNBT(NBTTagCompound nbt);
	
	public boolean hasUpdateLogic();
	public void updateEntity();
}
