package com.besuikerd.core.gui.layout;

import java.awt.Dimension;

import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementContainer;

public interface Layout {
	
	/**
	 * Called before elements needs to be laid out; used to reset the state of the layout before laying out all Elements.
	 * @param container
	 * @param mouseX
	 * @param mouseY
	 */
	public void init(ElementContainer container);
	
	/**
	 * translate the x and y coordinates of the Element in the given ElementContainer.
	 * @param e Element that needs to be laid out
	 * @param index index of the Element in the ElementContainer
	 * @return
	 */
	public boolean layout(Element e, int index);
	
	/**
	 * The dimensions of Elements laid out so far by this Layout
	 * @return The Dimension of Elements laid out so far by this Layout
	 */
	public Dimension getLaidOutDimension();
	
	/**
	 * align an Element relative to the container
	 * @param e
	 */
	public void align(Element e);
}