package com.besuikerd.core.packet;

public enum PacketType {
	
	UPDATE_TILE_ENTITY,
	
	;
	
	public static PacketType lookup(int id){
		return id < values().length ? values()[id] : null;
	}
	
	public byte getId(){
		return (byte) ordinal();
	}
}
