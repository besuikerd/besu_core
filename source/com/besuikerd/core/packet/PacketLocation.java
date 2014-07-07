package com.besuikerd.core.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public abstract class PacketLocation extends PacketBesu{

	protected int worldId;
	protected int x;
	protected int y;
	protected int z;
	
	@Override
	public void read(ByteArrayDataInput in) {
		this.worldId = in.readInt();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
	}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.write(worldId);
		out.write(x);
		out.write(y);
		out.write(z);
	}
}
