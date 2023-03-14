package me.kersan.config;

import java.io.*;
import java.util.Properties;

public class ConfigFile {

    static final String CONFIG_FOLDER_NAME = "SpicyClicker";
    static final String CONFIG_FILE_NAME = "settings.properties";

    public static void addToFile(Properties properties, OutputStream outputStream) {
        try {
            properties.store(outputStream, "SpicyClicker settings");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static OutputStream getFile(String location) {
        try {
            return new FileOutputStream(location);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFile(Properties properties) {
        try {
            properties.load(new FileInputStream(getSettingsFileLocation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean fileExists() {
        File directory = new File(getSettingsFileLocation());
        return (directory.exists() && !directory.isDirectory());
    }

    public static void locationCheck() {
        if (fileExists()) return;
        File dir = new File(getSettingsLocation());

        if (!dir.mkdirs() && !dir.exists()) {
            throw new RuntimeException("Could not create directory");
        }
    }

    public static String getSettingsLocation() {
        String userHome = System.getProperty("user.home");
        return userHome + File.separator + "AppData" + File.separator + "Roaming"
                + File.separator + CONFIG_FOLDER_NAME;
    }

    public static String getSettingsFileLocation() {
        return getSettingsLocation() + File.separator + CONFIG_FILE_NAME;
    }
}
