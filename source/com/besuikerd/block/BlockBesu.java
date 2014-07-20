package com.besuikerd.block;

import com.besuikerd.tileentity.TileEntityBesu;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBesu extends Block implements ITileEntityProvider {

	protected BlockBesu(Material material) {
		super(material);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEntityBesu) {
			((TileEntityBesu) tile).onTileEntityPlacedBy(world, x, y, z, entity, stack);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEntityBesu) {
			TileEntityBesu tileBesu = (TileEntityBesu) tile;
			tileBesu.onRemoveTileEntity(world, x, y, z, block, meta);
			world.removeTileEntity(x, y, z);
			tileBesu.onTileEntityRemoved(world, x, y, z, block, meta);
		} else {
			super.breakBlock(world, x, y, z, block, meta);
		}
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	
	//method overrides to prettify names
	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return super.getIcon(side, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float posX, float posY, float posZ) {
		return super.onBlockActivated(world, x, y, z, player, meta, posX, posY, posZ);
	}
}
