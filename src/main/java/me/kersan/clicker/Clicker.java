package me.kersan.clicker;

import java.awt.*;
import java.util.Random;

public class Clicker {

    public final Robot robot;
    private boolean doClick = false;

    public Clicker() {
        this.robot = getRobot();
    }

    public void stopClicking() {
        this.doClick = false;
    }

    public void startClicking(ClickerType.ClickerTypes type, int minCPS, int maxCPS) {
        this.doClick = true;
        new Thread(() -> click(type, minCPS, maxCPS)).start();
    }

    public boolean isClicking() {
        return this.doClick;
    }

    private void click(ClickerType.ClickerTypes type, int minCPS, int maxCPS) {
        int inputEvent = ClickerType.getClickerEvent(type);

        long lastTime = System.currentTimeMillis();
        long delay = getDelay(minCPS, maxCPS);

        while (this.doClick) {
            if (System.currentTimeMillis() - lastTime > delay) {
                System.out.println(System.currentTimeMillis() - lastTime);
                mouseDown(inputEvent);
                delay = getDelay(minCPS, maxCPS);
                lastTime = System.currentTimeMillis();
            }
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
