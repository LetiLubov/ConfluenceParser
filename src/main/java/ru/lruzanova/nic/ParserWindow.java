package ru.lruzanova.nic;

import ru.lruzanova.nic.gui.LoadPanel;
import ru.lruzanova.nic.gui.SavePanel;
import ru.lruzanova.nic.gui.SettingsPanel;
import ru.lruzanova.nic.gui.elements.LightJButton;
import ru.lruzanova.nic.gui.elements.color.GrayShadesColorResolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ParserWindow extends JFrame {
    private static final String TITLE = "Confluence Ripper by Lyubov Crutch Power Inc.";

    private LoadPanel loadPanel;
    private SettingsPanel settingsPanel;
    private SavePanel savePanel;
    private JButton save;
    private Controller controller;


    public ParserWindow() throws HeadlessException {
        super(TITLE);
        setMinimumSize(new Dimension(300, 320));
        setLocation(600, 400);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initComponents();
        place();

//        setJMenuBar(streamPanel.get());
        pack();
    }

    private void initComponents() {
        controller = new Controller();
        loadPanel = new LoadPanel(controller);
        settingsPanel = new SettingsPanel(controller);
        savePanel = new SavePanel();
        try {
            BufferedImage bufferedImage = ImageIO.read(ClassLoader.getSystemResource("save.png"));
            save = new LightJButton("Сохранить", bufferedImage, new GrayShadesColorResolver());
        } catch (Exception e) {
            save = new LightJButton("Сохранить", new GrayShadesColorResolver());
        }
    }

    private void place() {
        setLayout(new GridBagLayout());
        add(loadPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0.0,
                GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
                new Insets(16, 16, 0, 16), 0, 0));
        add(settingsPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0.0,
                GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
                new Insets(16, 16, 0, 16), 0, 0));
        add(savePanel, new GridBagConstraints(0, 2, 1, 1, 0, 0.0,
                GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
                new Insets(16, 16, 0, 16), 0, 0));
        add(save, new GridBagConstraints(0, 3, 1, 1, 0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.EAST,
                new Insets(16, 16, 16, 16), 0, 0));

    }

}
