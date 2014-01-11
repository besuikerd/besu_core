package com.besuikerd.networkcraft.tileentity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.gui.GuiBase;
import com.besuikerd.core.gui.element.Element;
import com.besuikerd.core.gui.element.ElementButton;
import com.besuikerd.core.gui.element.ElementCheckbox;
import com.besuikerd.core.gui.element.ElementContainer;
import com.besuikerd.core.gui.element.ElementInputField;
import com.besuikerd.core.gui.element.ElementList;
import com.besuikerd.core.gui.element.ElementProgressBar;
import com.besuikerd.core.gui.element.ElementRadioButton;
import com.besuikerd.core.gui.element.ElementRootContainer;
import com.besuikerd.core.gui.element.ElementScrollContainer;
import com.besuikerd.core.gui.element.ElementStyledContainer;
import com.besuikerd.core.gui.element.RadioGroup;
import com.besuikerd.core.gui.element.adapter.BaseElementAdapter;
import com.besuikerd.core.gui.element.adapter.ButtonElementAdapter;
import com.besuikerd.core.gui.event.Event;
import com.besuikerd.core.gui.event.EventAction;
import com.besuikerd.core.gui.event.EventHandle;
import com.besuikerd.core.gui.event.Trigger;
import com.besuikerd.core.gui.layout.Alignment;
import com.besuikerd.core.gui.layout.HorizontalLayout;
import com.besuikerd.core.gui.layout.LayoutDimension;
import com.besuikerd.core.gui.layout.Orientation;
import com.besuikerd.core.gui.layout.VerticalLayout;
import com.besuikerd.core.inventory.ContainerBesu;
import com.besuikerd.core.tileentity.TileEntityBesu;

public class TileEntityTestGui extends TileEntityBesu{
	public static class Gui extends GuiBase{
		
		private List<String> items;
		
		public Gui(ContainerBesu container) {
			super(container);
		}
		
		@Override
		public void init() {
			root.add(
//				new ElementButton("Button 1").trigger(Trigger.RELEASED, "pressed"),
//				new ElementButton("Button 2").trigger(Trigger.RELEASED, "a very logical name indeed"),
//				new ElementButton("Button 3").trigger(Trigger.RELEASED, "you don't need to overide the arguments if you don't want to!"),
//				new ElementButton("Button 4").trigger(Trigger.RELEASED, "the most specific method will be chosen"),
//				
//				new ElementStyledContainer(LayoutDimension.ABSOLUTE, LayoutDimension.ABSOLUTE).add(
//					new ElementButton("blabla").align(Alignment.RIGHT)
//				).width(100).height(50),
				
				new ElementStyledContainer().add(
						new ElementButton("blabla")		
				)
				
				,
				new ElementStyledContainer().add(
					new ElementScrollContainer(100, Orientation.HORIZONTAL).add(
						new ElementButton(600, 50, "lalalalalalalalalalallaalalalalallalalallalalalla")
					)
				),
				
				new ElementScrollContainer(100, Orientation.VERTICAL).add(
					new ElementScrollContainer(200, Orientation.VERTICAL).add(
						new ElementScrollContainer(400, Orientation.VERTICAL).add(
							new ElementButton(100, 1000, "blabalabablalalbalbalbalalbalbalalbablablabl").trigger(Trigger.RELEASED, "pressed")
						)
					)
				)
			)
			;
		}
		
		public void pressed(ElementButton e){ //no need to add @EventHandle annotation if you bind the trigger to a method name
			BLogger.debug("pressed called!");
		}
		
		@EventHandle("a very logical name indeed")
		public void thisMethodHasNoLogicalNameWhatsoever(ElementButton e){ //add @EventHandle annotation to bind a method with a different name
			BLogger.debug("unlogically named method was called!");
		}
		
		@EventHandle("you don't need to overide the arguments if you don't want to!")
		public void aLessSpecificMethod(){
			BLogger.debug("a less specific method is called!");
		}
		
		@EventHandle("the most specific method will be chosen")
		public void mostSpecificMethod(){
			BLogger.debug("i will be ignored"); //there is a method with more (matching) arguments
		}
		
		@EventHandle("the most specific method will be chosen")
		public void mostSpecificMethod(Element e){
			BLogger.debug("i will be ignored"); //there is a method with more (matching) arguments
		}
		
		@EventHandle("the most specific method will be chosen")
		public void mostSpecificMethod(Element e, int mouseX, int mouseY, int which){
			BLogger.debug("i will be ignored"); //Element is less specific than ElementButton
		}
		
		@EventHandle("the most specific method will be chosen")
		public void mostSpecificMethod(ElementProgressBar e, int mouseX, int mouseY, int which){
			BLogger.debug("i will be ignored"); //ElementProgressBar is not a superclass of ElementButton
		}
		
		@EventHandle("the most specific method will be chosen")
		public void mostSpecificMethod(ElementButton e, int mouseX, int mouseY, int which){
			BLogger.debug("i will be called!"); //most specific and matching arguments
		}
	}
}
