package me.kersan.clicker;

import java.awt.event.InputEvent;

public class ClickerType {

    public enum ClickerTypes {
        MOUSE1, MOUSE2, MOUSE3
    }

    public static int getClickerEvent(ClickerTypes type) {
        return switch (type) {
            case MOUSE1 -> InputEvent.BUTTON1_DOWN_MASK;
            case MOUSE2 -> InputEvent.BUTTON2_DOWN_MASK;
            case MOUSE3 -> InputEvent.BUTTON3_DOWN_MASK;
        };
    }
}
