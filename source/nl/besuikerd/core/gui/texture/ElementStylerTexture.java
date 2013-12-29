package nl.besuikerd.core.gui.texture;

import nl.besuikerd.core.BLogger;
import nl.besuikerd.core.gui.element.Element;
import nl.besuikerd.core.gui.layout.Alignment;
import nl.besuikerd.core.utils.TupleUtils;

/**
 * styler that styles a texture at a given alignment
 * @author Besuikerd
 *
 */
public class ElementStylerTexture implements IElementStyler{

	protected ITexture texture;
	protected Alignment alignment;
	
	public ElementStylerTexture(ITexture texture, Alignment alignment) {
		this.texture = texture;
		this.alignment = alignment;
	}

	public ElementStylerTexture(ITexture texture) {
		this(texture, Alignment.CENTER);
	}

	@Override
	public void style(Element e) {
		int x = 0;
		int y = 0;
		int width = TupleUtils.xDiff(texture.getTexture());
		int height = TupleUtils.yDiff(texture.getTexture());
		switch(alignment){
			case CENTER:
				x = (e.getWidth() - width) / 2;
				y = (e.getHeight() - height) / 2;
				break;
				
			default:
				//TODO implements these
				break;
		}
		e.drawTexturedModalRect(x, y, texture.getTexture().int1(), texture.getTexture().int2(), width, height);
	}

}
