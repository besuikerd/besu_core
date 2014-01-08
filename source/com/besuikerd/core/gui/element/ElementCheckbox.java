package com.besuikerd.core.gui.element;

import com.besuikerd.core.gui.event.Trigger;
import com.besuikerd.core.gui.styler.ElementStylerTexture;
import com.besuikerd.core.gui.texture.Texture;
import com.besuikerd.core.gui.texture.scalable.ScalableTexture;

public class ElementCheckbox extends Element{

        protected boolean checked;
        
        public ElementCheckbox(boolean checked) {
                super(16, 16);
                this.styler = new ElementStylerTexture(Texture.CHECK_MARK){
                        @Override
                        public void style(Element e) {
                                if(isChecked()){
                                        super.style(e);
                                }
                        }
                };
                this.checked = checked;
        }

        public void onChecked(ElementRootContainer root, boolean checked){
                doTrigger(Trigger.CHECKED, checked);
        }
        
        public boolean isChecked() {
                return checked;
        }
        
        @Override
        public void draw(int mouseX, int mouseY) {
                drawBackgroundFromTextures(ScalableTexture.SLOT);
                super.draw(mouseX, mouseY);
        }
        
        @Override
        protected void onReleased(int x, int y, int which) {
                this.checked = !checked;
                onChecked(getRoot(), checked);
                super.onPressed(x, y, which);
        }
}