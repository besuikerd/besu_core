package com.besuikerd.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageTileEntity implements IMessage{

	private TileEntity tile;
	private NBTTagCompound nbt;
	
	public MessageTileEntity(TileEntity tile) {
		this.tile = tile;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		nbt = new NBTTagCompound();
		tile.writeToNBT(nbt);
		ByteBufUtils.writeTag(buf, nbt);
	}

	public NBTTagCompound getNBT() {
		return nbt;
	}
	
	public TileEntity getTileEntity() {
		return tile;
	}
}
