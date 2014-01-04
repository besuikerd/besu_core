package nl.besuikerd.core.gui.event;

import nl.besuikerd.core.BLogger;
import nl.besuikerd.core.gui.element.Element;
import nl.besuikerd.core.gui.element.ElementRootContainer;

public enum EventAction implements IEventAction{
	ENABLE{
		@Override
		public void onEvent(String name, Object[] args, ElementRootContainer root, Element e) {
			e.enabled(false);
		}
	},
	
	DISABLE{
		@Override
		public void onEvent(String name, Object[] args, ElementRootContainer root, Element e) {
			e.enabled(false);
		}
	},
	
	TOGGLE_ENABLE{
		@Override
		public void onEvent(String name, Object[] args, ElementRootContainer root, Element e) {
			e.enabled(!e.isEnabled());
		}
	},
	
	REMOVE_ELEMENT{
		@Override
		public void onEvent(String name, Object[] args, ElementRootContainer root, Element e) {
			BLogger.debug("removing element...");
			e.getParent().remove(e);
		}
	}
	
	;


	@Override
	public void onEvent(String name, Object[] args, ElementRootContainer root, Element e) {
		throw new UnsupportedOperationException("subclasses should override interface implementations");
	}

}
