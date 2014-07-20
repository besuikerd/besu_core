package com.besuikerd.message;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class TileEntityMessageHandler implements IMessageHandler<MessageTileEntity, MessageTileEntity>{

	public static final String CHANNEL_NAME = "com.besuikerd.tileentity";
	public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL_NAME);
	
	@Override
	public MessageTileEntity onMessage(MessageTileEntity message, MessageContext ctx) {
		message.getTileEntity().readFromNBT(message.getNBT());
		return null;
	}
}
