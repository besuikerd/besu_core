package com.besuikerd.core.block;

import net.minecraft.tileentity.TileEntity;

import com.besuikerd.core.BlockSide;
import com.besuikerd.core.packet.IProcessData;
import com.besuikerd.core.utils.BitUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class ConnectingSides implements IConnectingSides, IProcessData{

	protected byte connectingSides;
	protected TileEntity entity;
	
	public ConnectingSides(TileEntity entity) {
		this.entity = entity;
	}

	@Override
	public void read(ByteArrayDataInput in) {
		connectingSides = in.readByte();
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeByte(connectingSides);
	}

	@Override
	public boolean connectsTo(TileEntity other) {
		return other instanceof IConnectingSides;
	}
	
	@Override
	public void validateConnections() {
		byte oldConnections = connectingSides;
		for(BlockSide side : BlockSide.values()){
			int[] rel = side.getRelativeCoordinates(entity.xCoord, entity.yCoord, entity.zCoord);
			TileEntity other = (TileEntity) entity.worldObj.getBlockTileEntity(rel[0], rel[1], rel[2]);
			
			//set bit for current side if other IConnectingSide exists and connects to this IConnectingSide
			connectingSides = BitUtils.toggle(connectingSides, side.ordinal() + 1, other != null && (entity instanceof IConnectingSides ? ((IConnectingSides) entity).connectsTo(other) : connectsTo(other)));
		}
//		BLogger.debug("sides have changed: %b", oldConnections != connectingSides);
		//sides have changed
		if(oldConnections != connectingSides){
			entity.worldObj.markBlockForUpdate(entity.xCoord, entity.yCoord, entity.zCoord);
		}
	}

	@Override
	public BlockSide[] getConnectingSides() {
		return BlockSide.fromByte(connectingSides);
	}
	
	@Override
	public void validateNeighbours(){
		for(BlockSide b : BlockSide.values()){
			int[] rel = b.getRelativeCoordinates(entity.xCoord, entity.yCoord, entity.zCoord);
			TileEntity other = entity.worldObj.getBlockTileEntity(rel[0], rel[1], rel[2]);
			if(other != null && other instanceof IConnectingSides){
				((IConnectingSides) other).validateConnections();
			}
		}
	}

}
