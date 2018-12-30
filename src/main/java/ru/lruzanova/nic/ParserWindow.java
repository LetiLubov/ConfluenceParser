package ru.lruzanova.nic;

import javax.swing.*;
import java.awt.*;

public class ParserWindow extends JFrame {
    private static final String TITLE = "Парсер";
    private JPanel mainPanel;
    private JLabel loadLabel = new JLabel("Загрузить документ");
    private JRadioButton url;
    private JTextField urlValue;
    private JRadioButton fromDisk;
    private JTextField fileValue;
    private JLabel saveLabel = new JLabel("Сохранить документ");


    public ParserWindow() throws HeadlessException {
        super(TITLE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initComponents();
        place();

//        setJMenuBar(streamPanel.get());
        pack();
    }
    private void initComponents(){

    }

    private void place(){

    }

}
