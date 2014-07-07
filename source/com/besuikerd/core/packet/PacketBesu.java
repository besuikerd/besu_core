package com.besuikerd.core.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

import com.besuikerd.core.exception.NetworkCraftException;
import com.besuikerd.core.exception.NetworkCraftProtocolException;
import com.besuikerd.core.utils.ReflectUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Nicker
 * 
 * 
 * Packets have the following structure:
 * 
 * [id(8)][payload(variable)]
 *
 */
public abstract class PacketBesu implements IProcessData{
	
	public static final String DEFAULT_CHANNEL = "besu";
	
	/**
	 * channel defaults to "besu"
	 */
	protected String channel = DEFAULT_CHANNEL;
	
	private static final BiMap<Byte, Class<? extends PacketBesu>> ids;
	
	protected byte id;

	static{
		ImmutableBiMap.Builder<Byte, Class<? extends PacketBesu>> builder = ImmutableBiMap.builder();
		ids = builder.build();
		registerPacketTypes();
	}
	
	//register our packet types	
	private static void registerPacketTypes(){
		
	}
	
	protected PacketType type;
	protected EntityPlayer player;
	
	public static PacketBesu fromId(byte id) throws NetworkCraftException{
		Class<? extends PacketBesu> cls = ids.get(id);
		if(cls == null){
			throw new NetworkCraftProtocolException(String.format("invalid packet id %d", id));
		}
		
		PacketBesu packet = ReflectUtils.newInstance(cls);
		if(packet == null){
			throw new NetworkCraftException(String.format("failed to instantiate new packet, please check if %s has a default constructor", cls.getName()));
		}
		return packet;
	}
	
	public PacketType getType() {
		return type;
	}
	
	public Packet build(){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(id);
		write(out);
		return new C17PacketCustomPayload(channel, out.toByteArray());
	}
	
	public abstract void onReceive(Side side);
}
