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
        }

        ConfigFile.loadFile(properties);

        settings.setMinCPS(Integer.parseInt(properties.getProperty("MinCPS")));
        settings.setMaxCPS(Integer.parseInt(properties.getProperty("MaxCPS")));
        settings.setMouseButton(ClickerType.ClickerTypes.valueOf(properties.getProperty("MouseButton")));
    }

    public static void saveSettings(Settings settings) {
        ConfigFile.locationCheck();
        Properties properties = new Properties();

        properties.setProperty("MinCPS", String.valueOf(settings.getMinCPS()));
        properties.setProperty("MaxCPS", String.valueOf(settings.getMaxCPS()));
        properties.setProperty("MouseButton", String.valueOf(settings.getMouseButton()));

        String location = ConfigFile.getSettingsFileLocation();
        ConfigFile.addToFile(properties, ConfigFile.getFile(location));
    }
}
