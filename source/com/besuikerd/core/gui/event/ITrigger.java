package com.besuikerd.core.gui.event;

import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementRootContainer;

public interface ITrigger {
	public void trigger(String name, ElementRootContainer root, Element e, Object... args);
}
