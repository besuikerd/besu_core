package com.besuikerd.core.gui;

import com.besuikerd.core.inventory.ContainerBesu;
import com.besuikerd.core.inventory.TileEntityInventory;
import com.besuikerd.core.utils.INumbered;
import com.besuikerd.core.utils.ReflectUtils;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerBesu implements IGuiHandler{
	public static final int HANDLER_LIMIT = 256;
	
	private static GuiHandlerBesu instance = null;
	
	
	private Class<? extends Gui>[] registryClient;
	private Class<? extends ContainerBesu>[] registryServer;
	
	private GuiHandlerBesu(){
		this.registryClient = new Class[HANDLER_LIMIT];
		this.registryServer = new Class[HANDLER_LIMIT];
	}
	
	public static GuiHandlerBesu getInstance(){
		if(instance == null){
			instance = new GuiHandlerBesu();
		}
		return instance;
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = null;
		
		//id should be valid and a TileEntity must exist in the given coords
		if(id >= 0 && id < registryServer.length && world.blockExists(x, y, z) && (entity = world.getBlockTileEntity(x, y, z)) != null){
			Class<? extends ContainerBesu> clsContainer = this.registryServer[id];
			if(clsContainer != null){
				ContainerBesu container = ReflectUtils.newInstance(clsContainer);
				if(entity instanceof TileEntityInventory){
					container.bindEntity((TileEntityInventory) entity, player);	
				}
				return container;
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile = null;
		
		//id should be valid and a TileEntity must exist in the given coords
		if(id >= 0 && id < registryClient.length && world.blockExists(x, y, z) && (tile = world.getBlockTileEntity(x, y, z)) != null){
			Class<? extends Gui> guiClass = this.registryClient[id];
			Class<? extends ContainerBesu> containerClass = this.registryServer[id];
			if(guiClass != null && containerClass != null){
				if(GuiContainer.class.isAssignableFrom(guiClass)){
					ContainerBesu container = ReflectUtils.newInstance(containerClass);
					
					if(tile instanceof TileEntityInventory){
						container.bindEntity((TileEntityInventory) tile, player);
					}
					
					Gui g = ReflectUtils.newInstance(guiClass, container);
					if(tile instanceof TileEntityInventory && g instanceof GuiTileEntity){
						((GuiTileEntity) g).bindTileEntity((TileEntityInventory) tile, player, world);
					}
					return g;
				}
			}
		}
		return null;
	}
	
	public static void registerGui(int id, Class<? extends Gui> gui, Class<? extends ContainerBesu> containerClass){
		instance.registryClient[id] = gui;
		instance.registryServer[id] = containerClass;
	}
	
	public static void registerGui(int id, Class<? extends Gui> gui){
		registerGui(id, gui, ContainerBesu.class);
	}
	
	public static void registerGui(INumbered number, Class<? extends Gui> gui, Class<? extends ContainerBesu> containerClass){
		registerGui(number.getNumber(), gui, containerClass);
	}
	
	public static void registerGui(INumbered number, Class<? extends Gui> gui){
		registerGui(number.getNumber(), gui);
	}
	
	public static void registerGuiForServer(int id, Class<? extends ContainerBesu> containerClass){
		instance.registryServer[id] = containerClass;
	}
	
	public static void registerGuiForServer(INumbered number, Class<? extends ContainerBesu> containerClass){
		registerGuiForServer(number.getNumber(), containerClass);
	}
	
	public static void registerGuiForServer(INumbered number){
		registerGuiForServer(number, ContainerBesu.class);
	}
}