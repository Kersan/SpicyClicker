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
        createRangeSlider(panel);
        createDropdown(panel);
    }

    private void createMouseButtons(JPanel panel) {
        JRadioButton mouse1 = new JRadioButton("Mouse 1");
        JRadioButton mouse2 = new JRadioButton("Mouse 2");
        JRadioButton mouse3 = new JRadioButton("Mouse 3");

        switch (settings.getMouseButton().ordinal()) {
            case 0 -> mouse1.setSelected(true);
            case 1 -> mouse2.setSelected(true);
            case 2 -> mouse3.setSelected(true);
        }

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

        bindKey.addActionListener(e -> new Thread(this::handleBindKey).start());

        panel.add(bindKey);
    }

    private void handleBindKey() {
        this.binding.setKey(0);
        this.binding.setListening(true);

        boolean handled = false;

        while (!handled) {
            if (!this.binding.isListening()) {
                handled = true;
            }
        }
    }

    private void createRangeSlider(JPanel panel) {
        JPanel cpsPanel = new JPanel();

        JLabel rangeSliderLabel1 = new JLabel("Min");
        JLabel rangeSliderLabel2 = new JLabel("Max");
        JLabel rangerSliderValue1 = new JLabel(String.valueOf(settings.getMinCPS()));
        JLabel rangerSliderValue2 = new JLabel(String.valueOf(settings.getMaxCPS()));

        RangeSlider rangeSlider = new RangeSlider(1, 50);
        rangeSlider.setValue(settings.getMinCPS());
        rangeSlider.setUpperValue(settings.getMaxCPS());

        rangeSlider.addChangeListener(e -> {
            RangeSlider slider = (RangeSlider) e.getSource();
            settings.setMinCPS(slider.getValue());
            settings.setMaxCPS(slider.getUpperValue());
            rangerSliderValue1.setText(String.valueOf(slider.getValue()));
            rangerSliderValue2.setText(String.valueOf(slider.getUpperValue()));
        });

        cpsPanel.add(rangeSliderLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
        cpsPanel.add(rangeSliderLabel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
        cpsPanel.add(rangerSliderValue1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
        cpsPanel.add(rangerSliderValue2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));
        cpsPanel.add(rangeSlider, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        panel.add(cpsPanel);
    }

    private void createDropdown(JPanel panel) {
        JComboBox<String> clickerType = new JComboBox<>();
        clickerType.addItem("Toggle");
        clickerType.addItem("Hold");
        clickerType.addItem("Press");

        switch (settings.getMode().ordinal()) {
            case 0 -> clickerType.setSelectedIndex(0);
            case 1 -> clickerType.setSelectedIndex(1);
            case 2 -> clickerType.setSelectedIndex(2);
        }

        clickerType.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            System.out.println(cb.getSelectedIndex());

            switch (cb.getSelectedIndex()) {
                case 0 -> settings.setMode(ClickerType.Mode.TOGGLE);
                case 1 -> settings.setMode(ClickerType.Mode.HOLD);
                case 2 -> settings.setMode(ClickerType.Mode.PRESS);
            }
        });

        panel.add(clickerType);
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
