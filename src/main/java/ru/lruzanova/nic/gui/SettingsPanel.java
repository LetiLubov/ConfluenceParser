package ru.lruzanova.nic.gui;

import ru.lruzanova.nic.Controller;
import ru.lruzanova.nic.TranslitUtil;
import ru.lruzanova.nic.gui.elements.LightJButton;
import ru.lruzanova.nic.gui.elements.color.GrayShadesColorResolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsPanel extends JPanel {
    private JLabel originalNameLabel;
    private JTextField originalName;
    private JLabel newNameLabel;
    private JTextField newName;
    private JCheckBox useCyrilicConverter;
    private JCheckBox deleteColorText;
    private JButton setHtmlBody;
    private Controller controller;

    public SettingsPanel(Controller controller) {
        this.controller = controller;
        initComponents();
        place();

    }

    private void initComponents() {
        originalNameLabel = new JLabel("Оригинальное название");
        originalName = new JTextField();
        originalName.setColumns(32);
        originalName.setEditable(false);
        newNameLabel = new JLabel("Новое название");
        newName = new JTextField();
        newName.setColumns(24);
        useCyrilicConverter = new JCheckBox("Конвертировать кириллицу в транслит");
        deleteColorText = new JCheckBox("Убрать разноцветный текст");
        try {
            BufferedImage bufferedImage = ImageIO.read(ClassLoader.getSystemResource("settings.png"));
            setHtmlBody = new LightJButton("Настроить обретку вики контейна", bufferedImage, new GrayShadesColorResolver());
        } catch (Exception e) {
            setHtmlBody = new LightJButton("Настроить обретку вики контейна", new GrayShadesColorResolver());
        }
        setHtmlBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentStructurePanel message = new DocumentStructurePanel();
                int optionDialog = JOptionPane.showOptionDialog(SettingsPanel.this, message,
                        "Структура документа", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                        new Object[]{"Сохранить", "Отмена"}, "Сохранить");
                if (optionDialog == JOptionPane.OK_OPTION) {

                }
            }
        });
        deleteColorText.setSelected(true);
        useCyrilicConverter.setSelected(true);

        controller.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("newDocument")){
                    Object newValue = evt.getNewValue();
                    String text = newValue.toString();
                    originalName.setText(text);
                    if (useCyrilicConverter.isSelected()) {
                      text = TranslitUtil.convert(text);
                    }
                    newName.setText(text);
                }
            }
        });
    }

    private void place() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(originalNameLabel)
                                .addComponent(originalName)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(newNameLabel)
                                .addComponent(newName)
                        )
                )
                .addComponent(useCyrilicConverter)
                .addComponent(deleteColorText)
                .addComponent(setHtmlBody)

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createBaselineGroup(true, true)
                        .addComponent(originalNameLabel)
                        .addComponent(newNameLabel))
                .addGroup(layout.createBaselineGroup(true, true)
                        .addComponent(originalName)
                        .addComponent(newName))
                .addComponent(useCyrilicConverter)
                .addComponent(deleteColorText)
                .addComponent(setHtmlBody)

        );

    }
}
