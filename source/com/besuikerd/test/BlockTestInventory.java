package com.besuikerd.test;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.besuikerd.core.BesuCore;
import com.besuikerd.core.block.BlockContainerBesu;

public class BlockTestInventory extends BlockContainerBesu{

	protected Icon icon;
	
	public BlockTestInventory(int id) {
		super(id, Material.ground);
		appendUnlocalizedName("testinventory");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTestInventory();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int unknown, float aX, float aY, float aZ) {
		player.openGui(BesuCoreTest.instance, BesuCoreTest.GUI_HANDLER.fromEntry(GuiEntry.TESTINVENTORY), world, x, y, z);
		return true;
	}
	
	@Override
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon("diamond_block");
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return icon;
	}
}
