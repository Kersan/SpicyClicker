package me.kersan.gui;

import me.kersan.Settings;

import javax.swing.*;
import java.awt.*;

public class ClickerGUI {

    private final JFrame frame;
    private final JPanel panel;
    private final ImageIcon icon;

    private Settings settings;

    public ClickerGUI(Settings settings) {
        this.frame = new JFrame("SpicyClicker");
        this.panel = new JPanel();
        this.icon = getIcon();

        this.settings = settings;

        createGUI();
    }

    public void createGUI() {
        createFrame(frame);
        createPanel(panel);

        frame.getContentPane().add(panel);

        JButton button = new JButton("Click me!");

        panel.add(button);

        button.addActionListener(e -> {
            System.out.println("Button clicked!");
        });
    }

    public void run() {
        frame.pack();
        frame.setVisible(true);
    }

    private void createFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());

        frame.setResizable(false);
    }

    private void createPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(300, 300));
    }

    private static ImageIcon getIcon() {
        return new ImageIcon("src/main/resources/icon.png");
    }
}
