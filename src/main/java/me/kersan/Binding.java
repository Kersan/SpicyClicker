package me.kersan;

import lombok.Getter;
import lombok.Setter;
import org.jnativehook.keyboard.NativeKeyEvent;


public class Binding {

    @Getter @Setter
    private boolean listening = false;

    @Getter @Setter
    private int key;

    @Getter @Setter
    private boolean ready = false;

    @Getter @Setter
    private boolean botReleased = false;

    public static String convertBindKey(int keyValue) {
        return NativeKeyEvent.getKeyText(keyValue);
    }
}
