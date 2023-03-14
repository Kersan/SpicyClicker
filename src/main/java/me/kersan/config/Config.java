package me.kersan.config;

import me.kersan.Settings;
import me.kersan.clicker.ClickerType;

public class Config {

    public static Settings loadDefault(Settings settings) {
        settings.setMinCPS(20);
        settings.setMaxCPS(50);
        settings.setMouseButton(ClickerType.ClickerTypes.MOUSE1);
        return settings;
    }
}
