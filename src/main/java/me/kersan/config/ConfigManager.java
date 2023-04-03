package me.kersan.config;

import me.kersan.Settings;
import me.kersan.clicker.ClickerType;

import java.util.Properties;

public class ConfigManager {

    public static void loadSettings(Settings settings) {
        ConfigFileHandler.locationCheck();
        Properties properties = new Properties();

        if (!ConfigFileHandler.fileExists()) {
            ConfigDefault.loadDefault(settings);
            ConfigManager.saveSettings(settings);
        }

        ConfigFileHandler.loadFile(properties);

        settings.setMinCPS(Integer.parseInt(properties.getProperty("MinCPS")));
        settings.setMaxCPS(Integer.parseInt(properties.getProperty("MaxCPS")));
        settings.setBindKey(Integer.parseInt(properties.getProperty("BindKey")));
        settings.setMouseButton(ClickerType.Mouse.valueOf(properties.getProperty("MouseButton")));
        settings.setMode(ClickerType.Mode.valueOf(properties.getProperty("Mode")));
    }

    public static void saveSettings(Settings settings) {
        ConfigFileHandler.locationCheck();
        Properties properties = new Properties();

        properties.setProperty("MinCPS", String.valueOf(settings.getMinCPS()));
        properties.setProperty("MaxCPS", String.valueOf(settings.getMaxCPS()));
        properties.setProperty("BindKey", String.valueOf(settings.getBindKey()));
        properties.setProperty("MouseButton", String.valueOf(settings.getMouseButton()));
        properties.setProperty("Mode", String.valueOf(settings.getMode()));

        String location = ConfigFileHandler.getSettingsFileLocation();
        ConfigFileHandler.addToFile(properties, ConfigFileHandler.getFile(location));
    }
}
