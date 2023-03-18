package me.kersan;

import lombok.Getter;
import lombok.Setter;
import me.kersan.clicker.ClickerType;

@Getter @Setter
public class Settings {
    private int MinCPS;
    private int MaxCPS;
    private int bindKey;
    private ClickerType.Mouse mouseButton;
    private ClickerType.Mode mode;
}
