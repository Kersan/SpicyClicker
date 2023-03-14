package me.kersan.listener;

import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ClickerKeyListener implements NativeKeyListener {

    private Settings settings;
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
        System.out.println("Key was pressed " + this.clicker.isClicking());
        if (!this.clicker.isClicking()) {
            this.clicker.startClicking(
                    settings.getMouseButton(),
                    settings.getMinCPS(),
                    settings.getMaxCPS()
            );
        } else {
            this.clicker.stopClicking();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
