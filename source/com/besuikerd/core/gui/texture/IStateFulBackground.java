package com.besuikerd.core.gui.texture;

import com.besuikerd.core.gui.texture.scalable.IScalableTexture;

/**
 * texture that changes depending on state
 * @author Besuikerd
 *
 */
public interface IStateFulBackground<E> {
	public IScalableTexture backgroundForState(E state);
}
