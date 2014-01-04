package nl.besuikerd.core.gui.texture;

import nl.besuikerd.core.gui.texture.scalable.IScalableTexture;

public class StatefulBackgroundWrapper implements IStateFulBackground<ElementState>{

	private IScalableTexture texture;
	
	public StatefulBackgroundWrapper(IScalableTexture texture) {
		this.texture = texture;
	}

	@Override
	public IScalableTexture backgroundForState(ElementState state) {
		return texture;
	}

}
