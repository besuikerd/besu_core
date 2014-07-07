package com.besuikerd.core.gui.event;

import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementRootContainer;

public interface IEventAction {
	public void onEvent(String name, Object[] args, ElementRootContainer root, Element e);
}
