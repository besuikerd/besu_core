package com.besuikerd.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.besuikerd.core.ServerLogger;
import com.besuikerd.core.tileentity.TileEntityBesu;
import com.besuikerd.core.utils.profiling.BesuProfiler;

public abstract class BlockContainerBesu extends BlockBesu implements ITileEntityProvider{
	
	public BlockContainerBesu(Material material) {
		super(material);
		this.isBlockContainer = true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z,
			Block block, int meta) {
		BesuProfiler p = BesuProfiler.newProfile();
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof TileEntityBesu){
			((TileEntityBesu) tile).onRemoveTileEntity(world, x, y, z);
		}
		BesuProfiler p2 = BesuProfiler.newProfile();
		super.breakBlock(world, x, y, z, block, meta);
		world.removeTileEntity(x, y, z);
		if(tile != null && tile instanceof TileEntityBesu){
			((TileEntityBesu) tile).onTileEntityRemoved(world, x, y, z);
		}
//		ServerLogger.debug("onRemove: %d [%s - %s]", p.getTime() + p2.getTime(), p, p2);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack stack) {
		BesuProfiler p = BesuProfiler.newProfile();
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof TileEntityBesu){
			((TileEntityBesu) tile).onTileEntityPlacedBy(world, x, y, z, entity, stack);
		}
		ServerLogger.debug("onPlaced: %s", p);
	}
	
	
	@Override
	public boolean onBlockEventReceived(World world, int x, int y,
			int z, int blockId, int eventId) {
		super.onBlockEventReceived(world, x, y, z, blockId, eventId);
		TileEntity entity = world.getTileEntity(x, y, z);
		return entity == null ? false : entity.receiveClientEvent(blockId, eventId);
	}
}
