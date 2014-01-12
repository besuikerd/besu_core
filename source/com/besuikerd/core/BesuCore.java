package com.besuikerd.core;

import net.minecraft.block.Block;

import com.besuikerd.core.gui.GuiHandlerBesu;
import com.besuikerd.test.BlockTestGui;
import com.besuikerd.test.BlockTestInventory;
import com.besuikerd.test.GuiEntry;
import com.besuikerd.test.TileEntityTestGui;
import com.besuikerd.test.TileEntityTestInventory;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BesuCore.ID)
@NetworkMod
public class BesuCore {
	public static final String ID = "BesuCore";
	
	@Instance(ID)
	public static BesuCore instance;
}
