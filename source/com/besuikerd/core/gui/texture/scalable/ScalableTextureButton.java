package com.besuikerd.core.gui.texture.scalable;

import com.besuikerd.core.utils.Tuple;

public enum ScalableTextureButton implements IScalableTexture{
	DISABLED(new Tuple(1, 1, 16, 16), new Tuple(1, 0, 16, 0), new Tuple(17, 1, 17, 16), new Tuple(1, 17, 16, 17), new Tuple(0, 1, 0, 16), new Tuple(0, 0, 0, 0), new Tuple(17, 0, 17, 0), new Tuple(17, 17, 17, 17), new Tuple(0, 17, 0, 17)),
	NORMAL(new Tuple(2, 20, 15, 32), new Tuple(2, 18, 15, 19), new Tuple(16, 20, 17, 32), new Tuple(2, 33, 15, 35), new Tuple(0, 20, 1, 32), new Tuple(0, 18, 1, 19), new Tuple(16, 18, 17, 19), new Tuple(16, 33, 17, 35), new Tuple(0, 33, 1, 35)),
	HOVERING(new Tuple(20, 2, 33, 14), new Tuple(20, 0, 33, 1), new Tuple(34, 2, 35, 14), new Tuple(20, 15, 33, 17), new Tuple(18, 2, 19, 14), new Tuple(18, 0, 19, 1), new Tuple(34, 0, 35, 1), new Tuple(34, 15, 35, 17), new Tuple(18, 15, 19, 17)),
	ACTIVATED(new Tuple(20, 21, 33, 33), new Tuple(20, 18, 33, 20), new Tuple(34, 21, 35, 33), new Tuple(20, 34, 33, 35), new Tuple(18, 21, 19, 33), new Tuple(18, 18, 19, 20), new Tuple(34, 18, 35, 20), new Tuple(34, 34, 35, 35), new Tuple(18, 34, 19, 35));
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
	
	private ScalableTextureButton(Tuple background, Tuple edgeTop, Tuple edgeRight, Tuple edgeBottom, Tuple edgeLeft, Tuple cornerTL, Tuple cornerTR, Tuple cornerBR, Tuple cornerBL) {
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
