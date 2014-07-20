package com.besuikerd.test;

import com.besuikerd.logging.BLogger;
import com.besuikerd.logging.ClientLogger;
import com.besuikerd.logging.ServerLogger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BesuCoreTestMod.ID)
public class BesuCoreTestMod {
	public static final String ID = "BesuCoreTestMod";
	
	@SidedProxy(clientSide=ClientProxy.CLS, serverSide = CommonProxy.CLS)
	public static CommonProxy proxy;
	
	@Instance(ID)
	public static BesuCoreTestMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		proxy.registerBlocks();
		proxy.registerTileEntities();
		proxy.registerRenderers();
		
		BLogger.setDebugMode(true);
		BLogger.debug("dsa");
	}
	
	
}
