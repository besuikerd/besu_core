package com.besuikerd.core.gui.element;

import org.lwjgl.opengl.GL11;

import com.besuikerd.core.gui.layout.LayoutDimension;
import com.besuikerd.core.gui.texture.scalable.IScalableTexture;
import com.besuikerd.core.gui.texture.scalable.ScalableTexture;
import com.besuikerd.core.utils.TupleUtils;

public class ElementStyledContainer extends ElementContainer{

	protected IScalableTexture background;
	
	public ElementStyledContainer(int x, int y, int width, int height, IScalableTexture background) {
		super(x, y, width, height);
		this.background = background;
		
		int extraPadding = 0;
		
		paddingTop = TupleUtils.yDiff(background.edgeTop()) + extraPadding;
		paddingRight = TupleUtils.xDiff(background.edgeRight()) + extraPadding;
		paddingBottom = TupleUtils.yDiff(background.edgeBottom()) + extraPadding;
		paddingLeft = TupleUtils.xDiff(background.edgeLeft()) + extraPadding;
	}
	
	public ElementStyledContainer(int width, int height, IScalableTexture background) {
		this(0, 0, width, height, background);
	}

	public ElementStyledContainer(LayoutDimension width, LayoutDimension height, IScalableTexture background) {
		this(0, 0, 0, 0, background);
		this.widthDimension = width;
		this.heightDimension = height;
	}
	
	public ElementStyledContainer(int x, int y, int width, int height) {
		this(x, y, width, height, ScalableTexture.STYLED_CONTAINER);
	}
	
	public ElementStyledContainer(int width, int height) {
		this(0,0 , width, height);
	}
	
	public ElementStyledContainer(LayoutDimension width, LayoutDimension height) {
		this(width, height, ScalableTexture.STYLED_CONTAINER);
	}
	
	public ElementStyledContainer() {
		this(LayoutDimension.WRAP_CONTENT, LayoutDimension.WRAP_CONTENT);
	}

	@Override
	public void draw() {
		bindTexture();
		drawBackgroundFromTextures(background);
		super.draw();
	}
	
}
