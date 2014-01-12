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
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		Random r = new Random();
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IInventory){
			IInventory inv = (IInventory) entity;
			for(int i = 0 ; i < inv.getSizeInventory() ; i++){
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null && stack.stackSize > 0){
					EntityItem entityItem = new EntityItem(world, x + r.nextDouble() * 0.8 + 0.1, y + r.nextDouble() * 0.8 + 0.1, z + r.nextDouble() * 0.8 + 0.1, stack);
					entityItem.motionX = r.nextGaussian() * 0.05;
					entityItem.motionY = r.nextGaussian() * 0.05 + 0.2;
					entityItem.motionZ = r.nextGaussian() * 0.05;
					world.spawnEntityInWorld(entityItem);
				}
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
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
