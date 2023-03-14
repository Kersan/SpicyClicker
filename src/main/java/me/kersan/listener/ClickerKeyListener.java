package me.kersan.listener;

import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ClickerKeyListener implements NativeKeyListener {

    private final Settings settings;
    private final Clicker clicker;

    public ClickerKeyListener(Settings settings, Clicker detector) {
        this.settings = settings;
        this.clicker = detector;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_V) {
            this.handleClicker();
        }
    }

    private void handleClicker() {
        if (clicker.isClicking()) {
            clicker.setClicking(false);
            return;
        }

        clicker.startClicking(settings.getMouseButton(), settings.getMinCPS(), settings.getMaxCPS());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
