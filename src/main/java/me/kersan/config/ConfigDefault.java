package me.kersan.config;

import me.kersan.Settings;
import me.kersan.clicker.ClickerType;

public class ConfigDefault {

    public final static int minCPS = 8;
    public final static int maxCPS = 12;
    public final static int bindKey = 0x2E;
    public final static ClickerType.Mouse mouseButton = ClickerType.Mouse.MOUSE1;
    public final static ClickerType.Mode mode = ClickerType.Mode.TOGGLE;

    public static void loadDefault(Settings settings) {
        settings.setMinCPS(minCPS);
        settings.setMaxCPS(maxCPS);
        settings.setBindKey(bindKey);
        settings.setMouseButton(mouseButton);
        settings.setMode(mode);
    }
}
