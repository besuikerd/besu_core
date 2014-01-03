package nl.besuikerd.core.gui.texture;

import nl.besuikerd.core.utils.Tuple;

public interface IBorderTexture {
	public Tuple edgeTop();
	public Tuple edgeRight();
	public Tuple edgeBottom();
	public Tuple edgeLeft();
	
	public Tuple cornerTL();
	public Tuple cornerTR();
	public Tuple cornerBR();
	public Tuple cornerBL();
}
