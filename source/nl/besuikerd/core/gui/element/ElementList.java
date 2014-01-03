package nl.besuikerd.core.gui.element;

import nl.besuikerd.core.gui.element.adapter.IElementAdapter;
import nl.besuikerd.core.gui.element.adapter.InvalidationListener;
import nl.besuikerd.core.gui.layout.HorizontalLayout;
import nl.besuikerd.core.gui.layout.LayoutDimension;
import nl.besuikerd.core.gui.layout.Orientation;
import nl.besuikerd.core.gui.layout.VerticalLayout;

public class ElementList extends ElementContainer implements InvalidationListener{
	protected IElementAdapter adapter;

	public ElementList(IElementAdapter adapter, Orientation orientation) {
		this.adapter = adapter;
		adapter.addInvalidationListener(this);
		this.layout = orientation.isVertical() ? new VerticalLayout() : new HorizontalLayout();
		invalidateList();
	}
	
	public ElementList(IElementAdapter adapter) {
		this(adapter, Orientation.VERTICAL);
	}

	/**
	 * call this when the given list has been changed
	 */
	public void invalidateList(){
		clear();
		for(int i = 0 ; i < adapter.getElementCount() ; i++){
			add(adapter.getElementAt(i));
		}
	}

	@Override
	public void onInvalidation() {
		invalidateList();
	}
}
