package me.kersan.listener;

import me.kersan.Binding;
import me.kersan.clicker.Clicker;
import me.kersan.Settings;
import me.kersan.clicker.ClickerType;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class ListenerMouse implements NativeMouseListener {

    private final Settings settings;
    private final Clicker clicker;
    private final Binding binding;

    public ListenerMouse(Settings settings, Clicker clicker, Binding binding) {
        this.clicker = clicker;
        this.binding = binding;
        this.settings = settings;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        int testVal = settings.getMouseButton().ordinal() + 1;
        System.out.println("Down: " + nativeMouseEvent.getButton() + " " + testVal);

        if (binding.isListening()
                || binding.isBotReleased()
                || !binding.isReady()
                || settings.getMode() != ClickerType.Mode.PRESS)
            return;

        if (nativeMouseEvent.getButton() == testVal)
            this.handleClickerPress();
    }

    private void handleClickerPress() {
        if (!this.clicker.isClicking())
            clicker.startClicking(settings);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        int testVal = settings.getMouseButton().ordinal() + 1;
        System.out.println("Up: " + nativeMouseEvent.getButton() + " " + testVal);

        if (settings.getMode() != ClickerType.Mode.PRESS)
            return;

        if (!clicker.isClicking())
            return;

        if (!binding.isBotReleased() && nativeMouseEvent.getButton() == testVal)
            clicker.setClicking(false);

        binding.setBotReleased(false);
    }
}
