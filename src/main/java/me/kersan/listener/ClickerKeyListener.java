package me.kersan.listener;

import me.kersan.Binding;
import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ClickerKeyListener implements NativeKeyListener {

    private final Settings settings;
    private final Clicker clicker;
    private final Binding binding;

    public ClickerKeyListener(Settings settings, Clicker detector, Binding binding) {
        this.settings = settings;
        this.clicker = detector;
        this.binding = binding;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (this.binding.isListening()) {
            this.handleBinding(nativeKeyEvent);
            return;
        }

        if (nativeKeyEvent.getKeyCode() == settings.getBindKey())
            this.handleClicker();
    }

    private void handleBinding(NativeKeyEvent event) {
        settings.setBindKey(event.getKeyCode());
        binding.setKey(event.getKeyCode());
        binding.setListening(false);
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
