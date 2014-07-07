package com.besuikerd.core.gui.texture.scalable;

import com.besuikerd.core.utils.Tuple;

public enum ScalableTextureVerticalScroller implements IScalableTexture{
	NORMAL(new Tuple(74, 1, 81, 2), new Tuple(74, 0, 81, 0), new Tuple(82, 1, 83, 3), new Tuple(74, 3, 81, 4), new Tuple(72, 1, 73, 3), new Tuple(72, 0, 73, 0), new Tuple(82, 0, 83, 0), new Tuple(82, 3, 83, 4), new Tuple(72, 3, 73, 4)),
	ACTIVATED(new Tuple(74, 6, 81, 7), new Tuple(74, 5, 81, 5), new Tuple(82, 6, 83, 8), new Tuple(74, 8, 81, 9), new Tuple(72, 6, 73, 8), new Tuple(72, 5, 73, 5), new Tuple(82, 5, 83, 5), new Tuple(82, 8, 83, 9), new Tuple(72, 8, 73, 9)),
	DISABLED(new Tuple(74, 11, 81, 12), new Tuple(74, 10, 81, 10), new Tuple(82, 11, 83, 13), new Tuple(74, 13, 81, 14), new Tuple(72, 11, 73, 13), new Tuple(72, 10, 73, 10), new Tuple(82, 10, 83, 10), new Tuple(72, 13, 73, 14), new Tuple(82, 13, 83, 14))
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
	
	private ScalableTextureVerticalScroller(Tuple background, Tuple edgeTop, Tuple edgeRight, Tuple edgeBottom, Tuple edgeLeft, Tuple cornerTL, Tuple cornerTR, Tuple cornerBR, Tuple cornerBL) {
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
