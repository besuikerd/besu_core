package nl.besuikerd.networkcraft.tileentity;

import nl.besuikerd.core.gui.GuiBase;
import nl.besuikerd.core.gui.element.ElementButton;
import nl.besuikerd.core.gui.element.ElementContainer;
import nl.besuikerd.core.gui.element.ElementRootContainer;
import nl.besuikerd.core.gui.element.ElementScrollContainer;
import nl.besuikerd.core.gui.layout.HorizontalLayout;
import nl.besuikerd.core.gui.layout.Orientation;
import nl.besuikerd.core.gui.layout.VerticalLayout;
import nl.besuikerd.core.inventory.ContainerBesu;
import nl.besuikerd.core.tileentity.TileEntityBesu;

public class TileEntityTestGui extends TileEntityBesu{
	public static class Gui extends GuiBase{

		public Gui(ContainerBesu container) {
			super(container);
		}
		
		@Override
		public void init() {
			final ElementScrollContainer container = new ElementScrollContainer(100, Orientation.HORIZONTAL);
			
			container.add(new ElementButton(200, 50, "LALA"));
			
			ElementButton b = new ElementButton("+"){
				protected boolean onPressed(ElementRootContainer root, int x, int y, int which) {
					container.add(new ElementButton(40, 80, "button!"));
					return true;
				};
			};
			
			ElementButton b2 = new ElementButton("-"){
				protected boolean onPressed(ElementRootContainer root, int x, int y, int which) {
					container.remove(container.getElementCount() - 1);
					return true;
				};
			};
			
			root.padding(5)
			.layout(new HorizontalLayout(5, 0))
			.add(new ElementContainer().layout(new VerticalLayout())
				.add(b)
				.add(b2)
			)
			.add(container);
			
			
			
//			.add(new ElementScrollContainer(50, new ElementContainer()
//				.layout(new HorizontalLayout())
//				.paddingRight(25)
//				.add(new ElementScrollContainer(200, new ElementContainer()
//					.layout(new HorizontalLayout())
					
//					
//				))
//			))
//			
			;
		}
	}
}
