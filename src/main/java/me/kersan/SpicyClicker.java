package me.kersan;

import me.kersan.clicker.Clicker;
import me.kersan.config.Config;
import me.kersan.gui.ClickerGUI;
import me.kersan.listener.Listener;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SpicyClicker {
    public static void main(String[] args) {
        manageLogging();

        Settings settings = new Settings();
        Clicker detector = new Clicker();

        Config.loadSettings(settings);
        Listener.registerListener(settings, detector);

        ClickerGUI gui = new ClickerGUI(settings);
        gui.run();
    }

    private static void manageLogging() {
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
    }
}