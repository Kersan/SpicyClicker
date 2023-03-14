package me.kersan;

import lombok.Getter;
import lombok.Setter;
import me.kersan.clicker.ClickerType;

@Getter @Setter
public class Settings {

    private int MinCPS;
    private int MaxCPS;
    private ClickerType.ClickerTypes mouseButton;
}