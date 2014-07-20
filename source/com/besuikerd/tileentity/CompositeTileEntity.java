package com.besuikerd.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class CompositeTileEntity extends TileEntityBesu{
	private Map<Class<TileEntityLogic>, TileEntityLogic> composition;
	private boolean hasUpdatingLogic;
	private List<TileEntityLogic> updatingComposition;
	
	public CompositeTileEntity(){
		this.composition = new HashMap<Class<TileEntityLogic>, TileEntityLogic>();
		TileEntityLogic[] logics = createCompostion();
		for(TileEntityLogic logic : logics){
			composition.put((Class<TileEntityLogic>)logic.getClass(), logic);
		}
		this.updatingComposition = new ArrayList<TileEntityLogic>();
		for(TileEntityLogic logic : logics){
			if(logic.hasUpdateLogic()){
				updatingComposition.add(logic);
				if(!hasUpdatingLogic){
					hasUpdatingLogic = true;
				}
			}
		}
	}
	
	protected abstract TileEntityLogic[] createCompostion();
	
	public <E extends TileEntityLogic> boolean hasLogic(Class<E> logic){
		return composition.containsKey(logic);
	}
	
	public <E extends TileEntityLogic> E getLogic(Class<E> logic){
		return (E) composition.get(logic);
	}
	
	@Override
	public void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		for(TileEntityLogic logic : composition.values()){
			logic.onTileEntityPlacedBy(world, x, y, z, entity, stack);
		}
	}
	
	@Override
	public void onRemoveTileEntity(World world, int x, int y, int z, Block block, int meta) {
		for(TileEntityLogic logic : composition.values()){
			logic.onRemoveTileEntity(world, x, y, z, block, meta);
		}
	}
	
	@Override
	public void onTileEntityRemoved(World world, int x, int y, int z, Block block, int meta) {
		for(TileEntityLogic logic : composition.values()){
			logic.onTileEntityRemoved(world, x, y, z, block, meta);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		for(TileEntityLogic logic : composition.values()){
			logic.writeToNBT(nbt);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		for(TileEntityLogic logic : composition.values()){
			logic.readFromNBT(nbt);
		}
	}
	
	@Override
	public void updateEntity() {
		if(hasUpdatingLogic){
			for(TileEntityLogic logic : updatingComposition){
				logic.updateEntity();
			}
		}
	}
}
