package me.kersan.clicker;

import lombok.Getter;
import lombok.Setter;
import me.kersan.Binding;
import me.kersan.Settings;

import java.awt.*;
import java.util.Random;

public class Clicker {

    public final Robot robot;
    private final Binding binding;

    @Getter @Setter
    private boolean clicking = false;

    public Clicker(Binding binding) {
        this.robot = getRobot();
        this.binding = binding;
    }

    public void startClicking(Settings settings) {
        setClicking(true);
        new Thread(() -> click(
                settings.getMouseButton(),
                settings.getMinCPS(),
                settings.getMaxCPS()
        )).start();
    }

    private void click(ClickerType.Mouse type, int minCPS, int maxCPS) {
        int inputEvent = ClickerType.getClickerEvent(type);
        long lastTime = System.currentTimeMillis();

        long delay = 0;

        while (this.clicking) {
            if (!(System.currentTimeMillis() - lastTime > delay)) {
                continue;
            }
            mouseDown(inputEvent);

            delay = getDelay(minCPS, maxCPS);
            lastTime = System.currentTimeMillis();
        }

        binding.setBotReleased(false);
    }

    private long getDelay(int minCPS, int maxCPS) {
        Random random = new Random();
        int clicksPerSecond = random.nextInt(maxCPS - minCPS + 1) + minCPS;
        return (long) (1000.0 / clicksPerSecond);
    }

    private void mouseDown(int button) {
        binding.setBotReleased(true);
        robot.mousePress(button);
        robot.mouseRelease(button);
    }

    private Robot getRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
