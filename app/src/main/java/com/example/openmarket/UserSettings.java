package com.example.openmarket;

import android.app.Application;

public class UserSettings  extends Application {
    public static final String PREFERENCES = "preferences";

    public static final String CUSTOM_THEME= "customTheme";
    public static final String RED_THEME= "redTheme";
    public static final String YELLOW_THEME= "yellowTheme";

    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
