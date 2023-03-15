package me.kersan.gui;

import me.kersan.Binding;
import me.kersan.Settings;
import me.kersan.clicker.ClickerType;
import me.kersan.config.Config;

import javax.swing.*;
import java.awt.*;

public class ClickerGUI {

    private final JFrame frame;
    private final JPanel panel;
    private final ImageIcon icon;

    private final Settings settings;
    private final Binding binding;

    public ClickerGUI(Settings settings, Binding binding) {
        this.frame = new JFrame("SpicyClicker");
        this.panel = new JPanel();
        this.icon = getIcon();

        this.settings = settings;
        this.binding = binding;

        createGUI();
    }

    public void createGUI() {
        createFrame(frame);
        createPanel(panel);

        frame.getContentPane().add(panel);

        createMouseButtons(panel);
        createSaveButton(panel);
        createBindKey(panel);
    }

    private void createMouseButtons(JPanel panel) {
        JRadioButton mouse1 = new JRadioButton("Mouse 1", true);
        JRadioButton mouse2 = new JRadioButton("Mouse 2");
        JRadioButton mouse3 = new JRadioButton("Mouse 3");

        ButtonGroup group = new ButtonGroup();
        group.add(mouse1);
        group.add(mouse2);
        group.add(mouse3);

        mouse1.addActionListener(e -> settings.setMouseButton(ClickerType.Mouse.MOUSE1));
        mouse2.addActionListener(e -> settings.setMouseButton(ClickerType.Mouse.MOUSE2));
        mouse3.addActionListener(e -> settings.setMouseButton(ClickerType.Mouse.MOUSE3));

        panel.add(mouse1);
        panel.add(mouse2);
        panel.add(mouse3);
    }

    private void createSaveButton(JPanel panel) {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> Config.saveSettings(settings));
        panel.add(saveButton);
    }

    private void createBindKey(JPanel panel) {
        String key = Binding.convertBindKey(settings.getBindKey());
        JButton bindKey = new JButton(key);

        bindKey.addActionListener(e -> new Thread(() -> handleBindKey(bindKey)).start());

        panel.add(bindKey);
    }

    private void handleBindKey(JButton bindKey) {
        bindKey.setText("Press a key");

        this.binding.setKey(0);
        this.binding.setListening(true);

        boolean handled = false;

        while (!handled) {
            if (!this.binding.isListening()) {
                bindKey.setText("Zmienione");
                handled = true;
            }
        }
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
