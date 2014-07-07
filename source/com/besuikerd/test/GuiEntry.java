package com.besuikerd.test;

import net.minecraft.inventory.Container;

import com.besuikerd.core.gui.GuiBase;
import com.besuikerd.core.gui.GuiBinder;
import com.besuikerd.core.gui.IGuiBinder;
import com.besuikerd.core.gui.IGuiEntry;
import com.besuikerd.core.inventory.ContainerBesu;

public enum GuiEntry implements IGuiEntry{
	
	TESTINVENTORY("testInventory", ContainerBesu.class, TileEntityTestInventory.Gui.class, GuiBinder.TILE_ENTITY),
	TESTGUI("testGui", ContainerBesu.class, TileEntityTestGui.Gui.class, GuiBinder.DEFAULT),
	
	;
	
	private final String name;
	private final Class<? extends Container> containerClass;
	private final Class<? extends GuiBase> guiClass;
	private final IGuiBinder binder;

	private GuiEntry(String name, Class<? extends Container> containerClass, Class<? extends GuiBase> guiClass, IGuiBinder binder) {
		this.name = name;
		this.containerClass = containerClass;
		this.guiClass = guiClass;
		this.binder = binder;
	}

	public Class<? extends Container> getContainerClass() {
		return containerClass;
	}

	public Class<? extends GuiBase> getGuiClass() {
		return guiClass;
	}

	public IGuiBinder getBinder() {
		return binder;
	}

	@Override
	public String getName() {
		return name;
	}

}
