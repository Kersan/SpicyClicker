package me.kersan;

import me.kersan.clicker.Clicker;
import me.kersan.config.Config;
import me.kersan.listener.ClickerKeyListener;
import me.kersan.listener.ClickerMouseListener;
import me.kersan.gui.ClickerGUI;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SpicyClicker {
    public static void main(String[] args) {
        manageLogging();

        Settings settings = new Settings();
        Clicker detector = new Clicker();

        Config.loadDefault(settings);
        registerListener(settings, detector);

        ClickerGUI gui = new ClickerGUI(settings);
        gui.run();
    }

    private static void registerListener(Settings settings, Clicker detector) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(new ClickerMouseListener(settings, detector));
            GlobalScreen.addNativeKeyListener(new ClickerKeyListener(settings, detector));
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    private static void manageLogging() {
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
    }
}