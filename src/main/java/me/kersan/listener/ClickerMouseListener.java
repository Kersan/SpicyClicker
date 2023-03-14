package me.kersan.listener;

import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class ClickerMouseListener implements NativeMouseListener {

    private Settings settings;
    private Clicker detector;

    public ClickerMouseListener(Settings settings, Clicker detector) {
        this.settings = settings;
        this.detector = detector;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }
}
