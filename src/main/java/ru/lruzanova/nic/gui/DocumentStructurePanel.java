package ru.lruzanova.nic.gui;

import javax.swing.*;

public class DocumentStructurePanel extends JPanel {
    public static final String begin =
            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
            "<HTML>\n" +
            "<HEAD>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
            "    <TITLE>\n" +
            "        ПС Граф\n" +
            "    </TITLE>\n" +
            "</HEAD>\n" +
            "<BODY BGCOLOR=\"#ffffff\">";
    private JTextArea beginPart;
    private JLabel content;
    public static final String end = "</BODY>\n" +
            "</HTML>";
    private JTextArea endPart;

    public DocumentStructurePanel() {
        initComponents();
        place();

    }

    private void initComponents() {
        beginPart = new JTextArea(begin);
        content = new JLabel("<!-- контент -->");
        endPart  = new JTextArea(end);
    }

    private void place() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(beginPart)
                .addComponent(content)
                .addComponent(endPart)

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(beginPart)
                .addComponent(content)
                .addComponent(endPart)
        );
    }

    public String getBeginText(){
        return beginPart.getText();
    }
    public String getEndText(){
        return endPart.getText();
    }
}
