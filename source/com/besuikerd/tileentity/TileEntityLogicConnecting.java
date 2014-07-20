package com.besuikerd.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.besuikerd.utils.BlockSide;
import com.besuikerd.utils.tuple.Vector3;

public class TileEntityLogicConnecting extends TileEntityLogicBase{
	
	public static final String KEY_CONNECTING_SIDES = "connecting_sides";
	
	private ConnectsToPredicate connectsToPredicate;
	
	private List<ForgeDirection> connectingSides;
	
	public TileEntityLogicConnecting(TileEntity tile, ConnectsToPredicate connectsToPredicate) {
		super(tile);
		this.connectsToPredicate = connectsToPredicate;
		this.connectingSides = new ArrayList<ForgeDirection>();
	}
	
	@Override
	public void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onTileEntityPlacedBy(world, x, y, z, entity, stack);
		updateConnectingSides();
		updateNearbySides();
	}
	
	private void updateConnectingSides(){
		connectingSides.clear();
		for(BlockSide side : BlockSide.values()){
			Vector3 rel = side.getRelativeCoordinates(x, y, z);
			TileEntity tile = world.getTileEntity(rel._1, rel._2, rel._3);
			if(connectsToPredicate.connectsTo(tile, side)){
				connectingSides.add(side.toForgeDirection());
			}
		}
	}
	
	private void updateNearbySides(){
		for(BlockSide side : BlockSide.values()){
			Vector3 rel = side.getRelativeCoordinates(x, y, z);
			TileEntity nearbyTile = world.getTileEntity(rel._1, rel._2, rel._3);
			if(nearbyTile != null && nearbyTile instanceof CompositeTileEntity){
				CompositeTileEntity composite = (CompositeTileEntity) nearbyTile;
				if(composite.hasLogic(TileEntityLogicConnecting.class)){
					TileEntityLogicConnecting logic = composite.getLogic(TileEntityLogicConnecting.class);
					if(logic.connectsToPredicate.connectsTo(this.tile, side)){
						logic.updateConnectingSides();
					}
				}
			}
		}
	}
	
	public List<ForgeDirection> getConnectingSides() {
		return connectingSides;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		connectingSides.clear();
		int[] sides = nbt.getIntArray(KEY_CONNECTING_SIDES);
		for(int side : sides){
			connectingSides.add(ForgeDirection.values()[side]);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		int[] sides = new int[connectingSides.size()];
		for(int i = 0 ; i < connectingSides.size() ; i++){
			sides[i] = connectingSides.get(i).ordinal();
		}
		nbt.setIntArray(KEY_CONNECTING_SIDES, sides);
	}

	public static interface ConnectsToPredicate{
		public boolean connectsTo(TileEntity other, BlockSide side);
	}
}