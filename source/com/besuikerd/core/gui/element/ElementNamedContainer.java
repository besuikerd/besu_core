package com.besuikerd.core.gui.element;

import com.besuikerd.core.gui.layout.Alignment;

public class ElementNamedContainer extends ElementContainer{

	protected String name;
	
	public ElementNamedContainer(int width, int height, String name) {
		this(0, 0, width, height, name);
	}
	
	

	public ElementNamedContainer(int x, int y, int width, int height, String name) {
		super(x, y, width, height);
		this.name = name;
		add(new ElementLabel(name).align(Alignment.CENTER));
	}


	@Override
	public void draw(int mouseX, int mouseY) {
		super.draw(mouseX, mouseY);
	}
}
