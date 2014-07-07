package com.besuikerd.core.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBesu extends TileEntity{
	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
            super.onDataPacket(net, pkt);
            readFromNBT(pkt.func_148857_g());
    }
	
	
	@Override
    public Packet getDescriptionPacket() {
            NBTTagCompound tag = new NBTTagCompound();
            writeToNBT(tag);
            return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
	
	public void onTileEntityPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack){}
	public void onRemoveTileEntity(World world, int x, int y, int z){}
	public void onTileEntityRemoved(World world, int x, int y, int z) {}
}
