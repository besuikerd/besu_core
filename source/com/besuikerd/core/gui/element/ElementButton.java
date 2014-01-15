package com.besuikerd.core.gui.element;

import com.besuikerd.core.BLogger;
import com.besuikerd.core.gui.styler.ElementStylerText;
import com.besuikerd.core.gui.styler.IElementStyler;
import com.besuikerd.core.gui.texture.ElementState;
import com.besuikerd.core.gui.texture.IStateFulBackground;
import com.besuikerd.core.gui.texture.StateFulBackground;
import com.besuikerd.core.utils.Tuple;

import net.minecraft.client.gui.GuiButton;

public class ElementButton extends ElementStatefulBackground{
        
        public ElementButton(int x, int y, int width, int height, IStateFulBackground<ElementState> bg, IElementStyler styler) {
                super(bg, x, y, width, height);
                this.styler = styler;
        }
        
        public ElementButton(int width, int height, IStateFulBackground<ElementState> bg, IElementStyler styler) {
                this(0, 0, width, height, bg, styler);
        }
        
        
        public ElementButton(int x, int y, int width, int height, IElementStyler styler) {
                this(x, y, width, height, StateFulBackground.BUTTON, styler);
        }
        
        public ElementButton(int width, int height, IElementStyler styler) {
                this(0, 0, width, height, styler);
        }
        
        public ElementButton(int width, int height) {
                this(0, 0, width, height, (IElementStyler) null);
        }
        
        public ElementButton(int x, int y, int width, int height, String text) {
                this(x, y, width, height, new ElementStylerText(text));
        }
        
        public ElementButton(int width, int height, String text) {
                this(0, 0, width, height, text);
        }
        
        public ElementButton(String text){
                this(0, 0, 0, 0, text);
                this.width = fontRenderer.getStringWidth(text) + 12;
                this.height = fontRenderer.FONT_HEIGHT + 6;
        }
        
        @Override
        protected boolean onPressed(int x, int y, int which) {
                super.onPressed(x, y, which);
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                getRoot().requestFocus(this);
                return true;
        }
}