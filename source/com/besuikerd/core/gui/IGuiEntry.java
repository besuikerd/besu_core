package com.besuikerd.core.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.Container;

import com.besuikerd.core.utils.INamed;

public interface IGuiEntry extends INamed{
	public Class<? extends Container> getContainerClass();
	public Class<? extends GuiBase> getGuiClass();
	public IGuiBinder getBinder();
}
