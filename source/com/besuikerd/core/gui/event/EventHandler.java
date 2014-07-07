package com.besuikerd.core.gui.event;

import java.util.Arrays;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.utils.ReflectUtils;
import com.besuikerd.core.utils.ReflectUtils.Invokable;
import com.besuikerd.core.utils.functional.Predicate;

public class EventHandler implements IEventHandler{
	
	/**
	 * object handling incoming events
	 */
	protected Object handlerObject;
	
	public EventHandler(Object handlerObject) {
		this.handlerObject = handlerObject;
	}

	@Override
	public void post(final String name, Object... args) {
		Invokable i = ReflectUtils.getPartialMatchingInvokable(handlerObject, ReflectUtils.getAnnotatedMethods(handlerObject, EventHandle.class, new Predicate<EventHandle>() {
			@Override
			public boolean eval(EventHandle input) {
				return input.value() != null && input.value().equals(name);
			}
		}), args);
		if(i != null){
			i.invoke();
		} else{
			ReflectUtils.invokePartialMatchingMethod(handlerObject, name, args);
		}
	}

	public void setHandlerObject(Object handlerObject) {
		this.handlerObject = handlerObject;
	}
	
	public Object getHandlerObject() {
		return handlerObject;
	}
}
