package com.besuikerd.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.besuikerd.message.MessageTileEntity;
import com.besuikerd.message.TileEntityMessageHandler;

public abstract class TileEntityBesu extends TileEntity{
	public abstract void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack);
	public abstract void onRemoveTileEntity(World world, int x, int y, int z, Block block, int meta);
	public abstract void onTileEntityRemoved(World world, int x, int y, int z, Block block, int meta);
	
	@Override
	public Packet getDescriptionPacket() {
		return TileEntityMessageHandler.CHANNEL.getPacketFrom(new MessageTileEntity(this));
	}
}
