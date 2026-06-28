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
    private  ThemeType currentTheme = ThemeType.LIGHT;
    public void addListener(ThemeChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ThemeChangeListener listener) {
        listeners.remove(listener);
    }
    public void toggleTheme(){
        if (currentTheme == ThemeType.LIGHT) {
            currentTheme = ThemeType.DARK;
        } else {
            currentTheme = ThemeType.LIGHT;
        }
        for (ThemeChangeListener listener : listeners){
            listener.onThemeChanged();
        }
    }
    public ThemeType getCurrentTheme(){
        return currentTheme;
    }
}
