package com.besuikerd.core.gui.texture.scalable;

import com.besuikerd.core.utils.Tuple;

public enum ScalableBackgroundHorizontalScroller implements IScalableTexture{
	NORMAL(new Tuple(85, 2, 86, 9), new Tuple(85, 0, 87, 1), new Tuple(87, 2, 88, 9), new Tuple(85, 10, 87, 11), new Tuple(84, 2, 84, 9), new Tuple(84, 0, 84, 1), new Tuple(87, 0, 88, 1), new Tuple(87, 10, 88, 11), new Tuple(84, 10, 84, 11)),
	ACTIVATED(new Tuple(90, 2, 91, 9), new Tuple(90, 0, 92, 1), new Tuple(92, 2, 93, 9), new Tuple(90, 10, 92, 11), new Tuple(89, 2, 89, 9), new Tuple(89, 0, 89, 1), new Tuple(92, 0, 93, 1), new Tuple(92, 10, 93, 11), new Tuple(89, 10, 89, 11)),
	DISABLED(new Tuple(95, 2, 96, 9), new Tuple(95, 0, 97, 1), new Tuple(97, 2, 98, 9), new Tuple(95, 10, 97, 11), new Tuple(94, 2, 94, 9), new Tuple(94, 0, 94, 1), new Tuple(97, 0, 98, 1), new Tuple(97, 10, 98, 11), new Tuple(94, 10, 94, 11))
	;
	
	protected Tuple background;
	
	protected Tuple edgeTop;
	protected Tuple edgeRight;
	protected Tuple edgeBottom;
	protected Tuple edgeLeft;

	protected Tuple cornerTL;
	protected Tuple cornerTR;
	protected Tuple cornerBR;
	protected Tuple cornerBL;
	
	private ScalableBackgroundHorizontalScroller(Tuple background, Tuple edgeTop, Tuple edgeRight, Tuple edgeBottom, Tuple edgeLeft, Tuple cornerTL, Tuple cornerTR, Tuple cornerBR, Tuple cornerBL) {
		this.background = background;
		this.edgeTop = edgeTop;
		this.edgeRight = edgeRight;
		this.edgeBottom = edgeBottom;
		this.edgeLeft = edgeLeft;
		this.cornerTL = cornerTL;
		this.cornerTR = cornerTR;
		this.cornerBR = cornerBR;
		this.cornerBL = cornerBL;
	}

	@Override
	public Tuple getTexture() {
		return background;
	}

	@Override
	public Tuple edgeTop() {
		return edgeTop;
	}

	@Override
	public Tuple edgeRight() {
		return edgeRight;
	}

	@Override
	public Tuple edgeBottom() {
		return edgeBottom;
	}

	@Override
	public Tuple edgeLeft() {
		return edgeLeft;
	}

	@Override
	public Tuple cornerTL() {
		return cornerTL;
	}

	@Override
	public Tuple cornerTR() {
		return cornerTR;
	}

	@Override
	public Tuple cornerBR() {
		return cornerBR;
	}

	@Override
	public Tuple cornerBL() {
		return cornerBL;
	}
}
