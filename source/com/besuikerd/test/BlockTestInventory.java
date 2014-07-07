package com.besuikerd.test;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.besuikerd.core.block.BlockContainerBesu;

public class BlockTestInventory extends BlockContainerBesu{

	protected IIcon icon;
	
	public BlockTestInventory() {
		super(Material.ground);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int someUnknownValue) {
		return new TileEntityTestInventory();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int unknown, float aX, float aY, float aZ) {
		player.openGui(BesuCoreTest.instance, BesuCoreTest.GUI_HANDLER.fromEntry(GuiEntry.TESTINVENTORY), world, x, y, z);
		return true;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon("diamond_block");
	}
	
	@Override
	public IIcon getIcon(int par1, int par2) {
		return icon;
	}
}
