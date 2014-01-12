package com.besuikerd.test;

import net.minecraft.block.Block;

import com.besuikerd.core.BesuCore;
import com.besuikerd.core.gui.GuiHandlerBesu;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=BesuCoreTest.ID)
@NetworkMod
public class BesuCoreTest {
	
	public static final String ID = "BesuCore|Test";
	
	@Instance(ID)
	public static BesuCoreTest instance;
	
	public static Block blockInventory;
	public static Block blockGui;
	
	public static final GuiHandlerBesu GUI_HANDLER = new GuiHandlerBesu();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		
		initBlocks();
		
		GameRegistry.registerBlock(blockInventory, blockInventory.getUnlocalizedName());
		GameRegistry.registerBlock(blockGui, blockGui.getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TileEntityTestInventory.class, "testinventory");
		GameRegistry.registerTileEntity(TileEntityTestGui.class, "testGui");
		
		registerGuis();
	}
	
	private void initBlocks(){
		blockInventory = new BlockTestInventory(643);
		blockGui = new BlockTestGui(644);
	}
	
	private void registerGuis(){
		NetworkRegistry.instance().registerGuiHandler(instance, GUI_HANDLER);
		GUI_HANDLER.addGui(GuiEntry.TESTGUI);
		GUI_HANDLER.addGui(GuiEntry.TESTINVENTORY);
	}
}
