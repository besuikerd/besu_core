package com.besuikerd.core.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiScreen;

public class GuiScreenBesu extends GuiScreen{
	private GuiBase gui;

	public GuiScreenBesu(GuiBase gui) {
		this.gui = gui;
	}
	
	@Override
	public void initGui() {
		
		gui.dimension(this);
		gui.center(this);
		
		super.initGui();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		gui.dimension(this);
		gui.center(this);
		gui.draw();
	}
	
	@Override
	protected void keyTyped(char key, int code) {
		super.keyTyped(key, code);
	}
	
	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        
        //delegate mouse input to root container
        gui.handleMouseInput(x, y);
    }
	
	@Override
	public void handleKeyboardInput() {
		if(!gui.handleKeyboardInput()){
			super.handleKeyboardInput();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
