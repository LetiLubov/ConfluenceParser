package ru.lruzanova.nic.gui;

import ru.lruzanova.nic.gui.elements.LightJButton;
import ru.lruzanova.nic.gui.elements.color.GrayShadesColorResolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SavePanel extends JPanel {
    private JLabel saveLabel;
    private JTextField path;
    private JButton selectDir;

    public SavePanel() {
        initComponents();
        place();

    }

    private void initComponents() {
        saveLabel = new JLabel("Каталог для сохранения");
        path = new JTextField();
        path.setColumns(15);

        try {
            BufferedImage bufferedImage = ImageIO.read(ClassLoader.getSystemResource("open.png"));
            selectDir = new LightJButton(bufferedImage, new GrayShadesColorResolver());
        } catch (Exception e) {
            selectDir = new LightJButton("...", new GrayShadesColorResolver());
        }
        selectDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser =new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setDialogTitle("Выберите файл");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fileChooser.showOpenDialog(SavePanel.this) == JFileChooser.APPROVE_OPTION){
                    path.setText(fileChooser.getSelectedFile().toString());
                }
            }
        });
    }

    private void place() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);


        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(saveLabel)
                .addComponent(path)
                .addComponent(selectDir)

        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(saveLabel)
                .addComponent(path)
                .addComponent(selectDir)
        );

    }
}