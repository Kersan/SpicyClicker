package me.kersan.listener;

import me.kersan.Binding;
import me.kersan.Settings;
import me.kersan.clicker.Clicker;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class ListenerManager {

    public static void registerListener(Settings settings, Clicker clicker, Binding binding) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(new ListenerMouse(settings, clicker, binding));
            GlobalScreen.addNativeKeyListener(new ListenerKeyboard(settings, clicker, binding));
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
