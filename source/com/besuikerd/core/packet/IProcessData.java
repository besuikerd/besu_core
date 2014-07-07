package com.besuikerd.core.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public interface IProcessData{
	public void read(ByteArrayDataInput in);
	public void write(ByteArrayDataOutput out);
}
