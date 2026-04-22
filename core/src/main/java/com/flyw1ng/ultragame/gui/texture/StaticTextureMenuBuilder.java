package com.flyw1ng.ultragame.gui.texture;

import com.badlogic.gdx.graphics.Pixmap;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.background.Background;

import java.util.List;

public class StaticTextureMenuBuilder {

        Pixmap pixmap;

    public StaticTextureMenuBuilder(int width, int height){
        pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
    }

    public Pixmap createPixmapMenu(List<UIElement> uiElements){
        sortElements(uiElements);
        for (UIElement ui : uiElements){
         ui.renderPixmap(pixmap);
        }
        return pixmap;
    }

    private void sortElements(List<UIElement> uiElements){
        uiElements.sort((a, b) -> {
            if (a instanceof Background && !(b instanceof Background)) {
                return -1; // a — Background, b — нет → a должен быть раньше b
            }
            if (!(a instanceof Background) && b instanceof Background) {
                return 1;  // b — Background, a — нет → a должен быть позже b
            }
            return 0;      // оба Background или оба не Background → порядок не меняется
        });
    }
}
