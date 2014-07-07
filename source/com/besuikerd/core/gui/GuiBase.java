package com.besuikerd.core.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementContainer;
import com.besuikerd.core.gui.element.ElementRootContainer;
import com.besuikerd.core.gui.event.EventHandler;
import com.besuikerd.core.gui.event.IEventHandler;
import com.besuikerd.core.gui.layout.VerticalLayout;
import com.besuikerd.core.utils.collection.ArrayUtils;

public class GuiBase implements IEventHandler{
	
	protected ElementRootContainer root;
	protected EventHandler eventHandler;
	
	public GuiBase() {
		this.root = new ElementRootContainer();
		root.setEventHandler(this);
		this.eventHandler = new EventHandler(this);
		root.layout(new VerticalLayout())
		.padding(5);
	}
	
	public void bindEventHandler(Object o){
		eventHandler.setHandlerObject(o);
	}

	/**
	 * Override this method to attach Elements to the {@link #root} container
	 */
	public void init(){}
	
	public void handleMouseInput(int mouseX, int mouseY){
		root.handleMouseInput(mouseX, mouseY);
	}
	
	public ElementContainer getRoot() {
		return root;
	}
	
	public boolean handleKeyboardInput(){
		return root.handleKeyboardInput() && Keyboard.getEventKey() != Keyboard.KEY_ESCAPE; //if the root element consumes input, do not let others handle keyboard input
		
	}
	
	public void dimension(GuiScreen gui){
		//update all elements before rendering
		root.update();
		
		//dimension all elements in the root container
		root.dimension();
	}
	
	public void center(GuiScreen gui){
		//center the root container
		root.x((gui.width - root.getWidth()) / 2);
		root.y((gui.height - root.getHeight()) / 2);
	}
	
	public void draw(){
		root.draw();
	}

	@Override
	public void post(String name, Object... args) {
		if(args.length > 0){
			Object first = args[0];
			
			if(first instanceof Element){
				Element e = (Element) first;
				Object[] eArgs = ArrayUtils.copyOfRange(args, 1, args.length);
				root.onEvent(name, e, eArgs);
				onEvent(name, e, eArgs);
			}
		}
		eventHandler.post(name, args);
	}
	
	public void onEvent(String name, Element e, Object... args){
		
	}
}
