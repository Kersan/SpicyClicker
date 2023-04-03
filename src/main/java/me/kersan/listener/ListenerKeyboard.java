package me.kersan.listener;

import me.kersan.Binding;
import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import me.kersan.clicker.ClickerType;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ListenerKeyboard implements NativeKeyListener {

    private final Settings settings;
    private final Clicker clicker;
    private final Binding binding;

    public ListenerKeyboard(Settings settings, Clicker clicker, Binding binding) {
        this.settings = settings;
        this.clicker = clicker;
        this.binding = binding;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (this.binding.isListening())
            this.handleBinding(nativeKeyEvent);

        if (nativeKeyEvent.getKeyCode() != settings.getBindKey()
                || this.binding.isListening()) return;

        switch (settings.getMode()) {
            case TOGGLE -> this.handleClickerToggle();
            case HOLD -> this.handleClickerHold();
            case PRESS -> this.handleClickerPress();
        }
    }

    private void handleClickerPress() {
        if (!clicker.isClicking() && !this.binding.isReady()) {
            this.binding.setReady(true);
            return;
        }

        this.clicker.setClicking(false);
        this.binding.setReady(false);
        this.binding.setBotReleased(false);
    }

    private void handleBinding(NativeKeyEvent event) {
        settings.setBindKey(event.getKeyCode());
        binding.setKey(event.getKeyCode());
        binding.setListening(false);
    }

    private void handleClickerToggle() {
        if (clicker.isClicking()) {
            clicker.setClicking(false);
            return;
        }

        clicker.startClicking(settings);
    }

    private void handleClickerHold() {
        if (clicker.isClicking()) return;

        clicker.startClicking(settings);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() != settings.getBindKey())
            return;

        if (settings.getMode() == ClickerType.Mode.HOLD)
            clicker.setClicking(false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {}
}
