package com.besuikerd.core.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.exception.NetworkCraftException;
import com.besuikerd.core.exception.NetworkCraftProtocolException;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandlerBesu implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
		EntityPlayer ePlayer = (EntityPlayer) player;
		try {
			PacketBesu p = PacketBesu.fromId(in.readByte());
			p.player = ePlayer;
			p.read(in);
			p.onReceive(ePlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch(NetworkCraftProtocolException e){
			if(player instanceof EntityPlayerMP){
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				playerMP.playerNetServerHandler.kickPlayerFromServer(e.getMessage());
				BLogger.warn("player %s was kicked from the server, reason: %s", playerMP.username, e.getMessage());
			}
		} catch (NetworkCraftException e) {
			BLogger.warn("unable to process packet, reason: %s", e.getMessage());
		}
	}

}
