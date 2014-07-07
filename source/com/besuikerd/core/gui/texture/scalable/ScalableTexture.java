package com.besuikerd.core.gui.texture.scalable;

import com.besuikerd.core.utils.Tuple;

public enum ScalableTexture implements IScalableTexture{
	STYLED_CONTAINER(new Tuple(232, 6, 249, 23), new Tuple(186, 0, 249, 2), new Tuple(253, 0, 255, 63), new Tuple(186, 3, 249, 5), new Tuple(250, 0, 252, 63), new Tuple(242, 24, 245, 27), new Tuple(246, 24, 249, 27), new Tuple(246, 28, 249, 31), new Tuple(242, 28, 245, 31)),
	SLOT(new Tuple(55, 1, 70, 16), new Tuple(55, 0, 70, 0), new Tuple(71, 1, 71, 16), new Tuple(55, 17, 70, 17), new Tuple(54, 1, 54, 16), new Tuple(54, 0, 54, 0), new Tuple(71, 0, 71, 0), new Tuple(71, 17, 71, 17), new Tuple(54, 17, 54, 17)),
	SLOT_INVERSE(new Tuple(37, 1, 52, 16), new Tuple(37, 0, 52, 0), new Tuple(53, 1, 53, 16), new Tuple(37, 17, 52, 17), new Tuple(36, 1, 36, 16), new Tuple(36, 0, 36, 0), new Tuple(53, 0, 53, 0), new Tuple(53, 17, 53, 17), new Tuple(36, 17, 36, 17)),
	
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
	
	private ScalableTexture(Tuple background, Tuple edgeTop, Tuple edgeRight, Tuple edgeBottom, Tuple edgeLeft, Tuple cornerTL, Tuple cornerTR, Tuple cornerBR, Tuple cornerBL) {
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
