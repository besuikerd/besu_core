package com.besuikerd.core.utils;

import net.minecraft.nbt.NBTTagCompound;

import com.besuikerd.core.packet.IProcessData;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class NBTUtils {
	public static void writeProcessData(IProcessData data, NBTTagCompound tag, String key){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		data.write(out);
		tag.setByteArray(key, out.toByteArray());
	}
	
	public static void readProcessData(IProcessData data, NBTTagCompound tag, String key){
		data.read(ByteStreams.newDataInput(tag.getByteArray(key)));
	}
}
