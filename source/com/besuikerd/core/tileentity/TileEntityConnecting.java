package com.besuikerd.core.tileentity;

import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.besuikerd.core.BLogger;

import cpw.mods.fml.common.FMLCommonHandler;

public abstract class TileEntityConnecting extends TileEntityBesu{
	public static final String TAG_CONNECTED_SIDES = "SIDES";
	
	protected boolean[] connectedSides = new boolean[6];
	
	public boolean connectsTo(TileEntity other){
		return other instanceof TileEntityConnecting;
	}
	
	public void onConnected(TileEntity other){}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {		
		super.writeToNBT(tagCompound);
		byte val = 0;
		for(int i = 0 ; i < connectedSides.length ; i++){
			val |= ((connectedSides[i] ? 1 : 0) << i) &0xff;
		}
		tagCompound.setByte(TAG_CONNECTED_SIDES, val);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		byte val = tagCompound.getByte(TAG_CONNECTED_SIDES);
		for(int i = 0 ; i < connectedSides.length ; i++){
			connectedSides[i] = ((val >> i) & 1) == 1;
		}
		BLogger.debug("%s| nbt read, sides: %d %s",FMLCommonHandler.instance().getEffectiveSide(), val, Arrays.toString(connectedSides));
	}
	
	public boolean[] getConnectedSides() {
		return connectedSides;
	}
}
