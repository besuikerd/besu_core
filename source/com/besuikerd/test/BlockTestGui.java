package com.besuikerd.test;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.besuikerd.core.BesuCore;
import com.besuikerd.core.block.BlockContainerBesu;

public class BlockTestGui extends BlockContainerBesu{

	protected Icon icon;
	
	public BlockTestGui(int id) {
		super(id, Material.ground);
		appendUnlocalizedName("gui");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTestGui();
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return icon;
	}
	
	@Override
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon("emerald_block");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int unknown, float aX, float aY, float aZ) {
		player.openGui(BesuCoreTest.instance, BesuCoreTest.GUI_HANDLER.fromEntry(GuiEntry.TESTGUI), world, x, y, z);
		return true;
	}

}
