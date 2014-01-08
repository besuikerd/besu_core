package com.besuikerd.core.gui.texture;

import com.besuikerd.core.utils.Tuple;

public enum Texture implements ITexture{
	ARROW_UP(new Tuple(72, 15, 78, 18)),
	ARROW_DOWN(new Tuple(79, 15, 85, 18)),
	ARROW_RIGHT(new Tuple(86, 15, 89, 21)),
	ARROW_LEFT(new Tuple(90, 15, 93, 21)),
	
	CHECK_MARK(new Tuple(123, 0, 130, 7)),
	
	RADIO_OUTER(new Tuple(99, 0, 114, 15)),
	RADIO_INNER(new Tuple(115, 0, 122, 7)),
	;
	
	private Tuple texture;

	private Texture(Tuple texture) {
		this.texture = texture;
	}
	
	public Tuple getTexture() {
		return texture;
	}
}