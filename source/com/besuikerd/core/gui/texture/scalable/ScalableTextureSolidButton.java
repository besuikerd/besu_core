package com.besuikerd.core.gui.texture.scalable;

import com.besuikerd.core.utils.Tuple;
public enum ScalableTextureSolidButton implements IScalableTexture{
	NORMAL(ScalableTexture.SLOT_INVERSE),
	ACTIVATED(ScalableTexture.SLOT),
	DISABLED(new Tuple(37, 19, 52, 34), new Tuple(37, 18, 52, 18), new Tuple(53, 19, 53, 34), new Tuple(37, 35, 52, 35), new Tuple(36, 19, 36, 34), new Tuple(36, 18, 36, 18), new Tuple(53, 18, 53, 18), new Tuple(53, 35, 53, 35), new Tuple(36, 35, 36, 35))
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
	
	private ScalableTextureSolidButton(Tuple background, Tuple edgeTop, Tuple edgeRight, Tuple edgeBottom, Tuple edgeLeft, Tuple cornerTL, Tuple cornerTR, Tuple cornerBR, Tuple cornerBL) {
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
	
	private ScalableTextureSolidButton(IScalableTexture texture){
		this(texture.getTexture(), texture.edgeTop(), texture.edgeRight(), texture.edgeBottom(), texture.edgeLeft(), texture.cornerTL(), texture.cornerTR(), texture.cornerBR(), texture.cornerBL());
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
