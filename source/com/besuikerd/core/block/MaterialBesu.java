package com.besuikerd.core.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialBesu extends Material{

	public static final Material material_device = new MaterialBesu(MapColor.ironColor);
	
	public MaterialBesu(MapColor par1MapColor) {
		super(par1MapColor);
	}
}
