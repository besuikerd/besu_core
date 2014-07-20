package com.besuikerd.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityLogicBase implements TileEntityLogic {

	protected int x;
	protected int y;
	protected int z;
	protected World world;
	protected TileEntity tile;

	public TileEntityLogicBase(TileEntity tile) {
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.world = tile.getWorldObj();
		this.tile = tile;
	}

	@Override
	public void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
	}

	@Override
	public void onRemoveTileEntity(World world, int x, int y, int z, Block block, int meta) {
	}

	@Override
	public void onTileEntityRemoved(World world, int x, int y, int z, Block block, int meta) {
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public boolean hasUpdateLogic() {
		return false;
	}

	@Override
	public void updateEntity() {
	}
}
