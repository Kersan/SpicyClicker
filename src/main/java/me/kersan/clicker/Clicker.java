package me.kersan.clicker;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

public class Clicker {

    public final Robot robot;

    @Getter @Setter
    private boolean clicking = false;

    public Clicker() {
        this.robot = getRobot();
    }

    public void startClicking(ClickerType.ClickerTypes type, int minCPS, int maxCPS) {
        setClicking(true);
        new Thread(() -> click(type, minCPS, maxCPS)).start();
    }

    private void click(ClickerType.ClickerTypes type, int minCPS, int maxCPS) {
        int inputEvent = ClickerType.getClickerEvent(type);

        long lastTime = System.currentTimeMillis();
        long delay = getDelay(minCPS, maxCPS);

        while (this.clicking) {
            if (!(System.currentTimeMillis() - lastTime > delay)) {
                continue;
            }

            mouseDown(inputEvent);

            delay = getDelay(minCPS, maxCPS);
            lastTime = System.currentTimeMillis();
        }
    }

    private long getDelay(int minCPS, int maxCPS) {
        Random random = new Random();
        int clicksPerSecond = random.nextInt(maxCPS - minCPS + 1) + minCPS;
        return (long) (1000.0 / clicksPerSecond);
    }

    private void mouseDown(int button) {
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
