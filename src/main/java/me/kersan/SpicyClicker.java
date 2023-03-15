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

    private static final Binding binding = new Binding();
    private static final Clicker clicker = new Clicker();
    private static final Settings settings = new Settings();

    public static void main(String[] args) {
        manageLogging();

        Config.loadSettings(settings);
        Listener.registerListener(settings, clicker, binding);

        ClickerGUI gui = new ClickerGUI(settings, binding);
        gui.run();
    }

    private static void manageLogging() {
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
    }
}