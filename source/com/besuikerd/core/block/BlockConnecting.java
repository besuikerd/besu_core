package com.besuikerd.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public abstract class BlockConnecting extends BlockContainerBesu {
	
	public BlockConnecting(Material material) {
		super(material);
		this.isBlockContainer = true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
