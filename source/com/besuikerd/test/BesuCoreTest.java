package com.besuikerd.test;

import net.minecraft.block.Block;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.gui.GuiHandlerBesu;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=BesuCoreTest.ID)
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
//		GameRegistry.registerBlock(blockInventory, blockInventory.getUnlocalizedName());
		GameRegistry.registerBlock(blockGui, "block.gui");
//		GameRegistry.registerTileEntity(TileEntityTestInventory.class, "testinventory");
		registerGuis();
	}
	
	private void initBlocks(){
//		blockInventory = new BlockTestInventory();
		blockGui = new BlockTestGui();
	}
	
	private void registerGuis(){
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, GUI_HANDLER);
		GUI_HANDLER.addGui(GuiEntry.TESTGUI);
//		GUI_HANDLER.addGui(GuiEntry.TESTINVENTORY);
	}
}
