package me.kersan;

import me.kersan.clicker.Clicker;
import me.kersan.config.ConfigManager;
import me.kersan.gui.ClickerGUI;
import me.kersan.listener.ListenerManager;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SpicyClicker {

    public static void main(String[] args) {
        manageLogging();

        Binding binding = new Binding();
        Settings settings = new Settings();
        Clicker clicker = new Clicker(binding);

        ConfigManager.loadSettings(settings);
        ListenerManager.registerListener(settings, clicker, binding);

        ClickerGUI gui = new ClickerGUI(settings, binding);
        gui.run();
    }

    private static void manageLogging() {
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
    }
}