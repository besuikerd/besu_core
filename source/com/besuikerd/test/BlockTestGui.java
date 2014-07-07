package com.besuikerd.test;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.besuikerd.core.block.BlockBesu;

public class BlockTestGui extends BlockBesu{

	protected IIcon icon;
	
	public BlockTestGui() {
		super(Material.ground);
		setBlockName("Test Gui");
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public IIcon getIcon(int par1, int par2) {
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		icon = register.registerIcon("emerald_block");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int unknown, float aX, float aY, float aZ) {
		player.openGui(BesuCoreTest.instance, BesuCoreTest.GUI_HANDLER.fromEntry(GuiEntry.TESTGUI), world, x, y, z);
		return true;
	}
	
	
}
