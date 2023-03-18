package me.kersan.config;

import me.kersan.Settings;
import me.kersan.clicker.ClickerType;

import java.util.Properties;

public class Config {

    public static void loadSettings(Settings settings) {
        ConfigFile.locationCheck();
        Properties properties = new Properties();

        if (!ConfigFile.fileExists()) {
            ConfigDefault.loadDefault(settings);
            Config.saveSettings(settings);
        }

        ConfigFile.loadFile(properties);

        settings.setMinCPS(Integer.parseInt(properties.getProperty("MinCPS")));
        settings.setMaxCPS(Integer.parseInt(properties.getProperty("MaxCPS")));
        settings.setBindKey(Integer.parseInt(properties.getProperty("BindKey")));
        settings.setMouseButton(ClickerType.Mouse.valueOf(properties.getProperty("MouseButton")));
        settings.setMode(ClickerType.Mode.valueOf(properties.getProperty("Mode")));
    }

    public static void saveSettings(Settings settings) {
        ConfigFile.locationCheck();
        Properties properties = new Properties();

        properties.setProperty("MinCPS", String.valueOf(settings.getMinCPS()));
        properties.setProperty("MaxCPS", String.valueOf(settings.getMaxCPS()));
        properties.setProperty("BindKey", String.valueOf(settings.getBindKey()));
        properties.setProperty("MouseButton", String.valueOf(settings.getMouseButton()));
        properties.setProperty("Mode", String.valueOf(settings.getMode()));

        String location = ConfigFile.getSettingsFileLocation();
        ConfigFile.addToFile(properties, ConfigFile.getFile(location));
    }
}
