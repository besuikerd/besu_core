package com.besuikerd.core.gui.element;

import com.besuikerd.core.gui.styler.ElementStylerTexture;
import com.besuikerd.core.gui.texture.scalable.ScalableTexture;
import com.besuikerd.core.inventory.InventoryStack;
import com.besuikerd.core.utils.Tuple;

import net.minecraft.inventory.Slot;

public class ElementItemContainer extends Element{

	protected InventoryStack stack;
	
	
	public ElementItemContainer(InventoryStack stack, int x, int y) {
		super(x, y, 18, 18);
		this.stack = stack;
	}
	
	public ElementItemContainer(InventoryStack stack){
		this(stack, 0, 0);
	}
	
	public void alignSlot(Slot s){
		//re-align the slot
		s.xDisplayPosition = absX() - getRoot().absX() + 1;
		s.yDisplayPosition = absY() - getRoot().absY() + 1;
	}
	
	@Override
	public void draw() {
		super.draw();
		drawBackgroundFromTextures(ScalableTexture.SLOT);
	}
}
