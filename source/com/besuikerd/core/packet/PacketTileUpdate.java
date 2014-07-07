package com.besuikerd.core.packet;

import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketTileUpdate extends PacketLocation{
	
	protected TileEntity entity;
	
	@Override
	public void read(ByteArrayDataInput in) {
		this.entity = player.worldObj.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IProcessData){
			((IProcessData) entity).read(in);
		}
	}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		if(entity != null && entity instanceof IProcessData){
			((IProcessData) entity).write(out);
		}
	}
	
	@Override
	public void onReceive(Side side) {
	}
}
