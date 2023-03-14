package me.kersan.listener;

import me.kersan.Settings;
import me.kersan.clicker.Clicker;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Listener {

    public static void registerListener(Settings settings, Clicker detector) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(new ClickerMouseListener(settings, detector));
            GlobalScreen.addNativeKeyListener(new ClickerKeyListener(settings, detector));
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
