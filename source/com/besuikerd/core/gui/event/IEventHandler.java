package com.besuikerd.core.gui.event;

import com.besuikerd.core.gui.element.Element;

public interface IEventHandler {
	public void post(String name, Element e, Object... args);
}