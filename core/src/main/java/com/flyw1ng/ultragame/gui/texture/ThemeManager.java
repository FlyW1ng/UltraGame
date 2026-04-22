package com.flyw1ng.ultragame.gui.texture;

import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.gui.ThemeChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Управляет сменой темы (светлая/тёмная).
 */
public class ThemeManager {
    private final List<ThemeChangeListener> listeners = new ArrayList<>();

    public void addListener(ThemeChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ThemeChangeListener listener) {
        listeners.remove(listener);
    }
    public void toggleTheme(){
        if (GameManager.ThemeTypeNow == ThemeType.LIGHT) {
            GameManager.ThemeTypeNow = ThemeType.DARK;
        } else {
            GameManager.ThemeTypeNow = ThemeType.LIGHT;
        }
        for (ThemeChangeListener listener : listeners){
            listener.onThemeChanged();
        }

    }
}
