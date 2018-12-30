package ru.lruzanova.nic.gui.elements;

import ru.lruzanova.nic.gui.elements.color.ColorResolver;
import ru.lruzanova.nic.gui.elements.color.TextColorResolver;
import ru.lruzanova.nic.gui.elements.icon.ChangeRGBImageFilter;
import ru.lruzanova.nic.gui.elements.text.DefaultTextBehaviorResolver;
import ru.lruzanova.nic.gui.elements.text.TextBehaviorResolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

/**
 * Кнопка без рамок с поддержкой самоопределения изображений для различных событий при наведении, нажатии и пр.
 *
 * @author Lyubov
 */
public class LightJButton extends JButton {
    private ColorResolver colorResolver;
    private TextColorResolver textColorResolver;
    private Image templateImage;
    private final String text;

    public LightJButton(Image image, ColorResolver generalColorResolver) {
        this(null, image, generalColorResolver);
    }

    public LightJButton(String text, TextColorResolver textColorResolver) {
        this(text, null, null);
        this.textColorResolver = textColorResolver;
    }

    public LightJButton(String text, Image image, ColorResolver colorResolver) {
        this.templateImage = image;
        this.colorResolver = colorResolver;
        this.textColorResolver = colorResolver;
        this.text = text;
        defaultSettings();
    }

    private void defaultSettings() {
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);

        setOpaque(true);
        setContentAreaFilled(false);
        setFocusPainted(false);
        if (templateImage != null && textColorResolver != null) {
            ImageProducer defaultIcon = new FilteredImageSource(templateImage.getSource(),
                    new ChangeRGBImageFilter(textColorResolver.getDefaultColor()));
            Image defaultImage = Toolkit.getDefaultToolkit().createImage(defaultIcon);
            this.setIcon(new ImageIcon(defaultImage));

            ImageProducer rolloverIcon = new FilteredImageSource(templateImage.getSource(),
                    new ChangeRGBImageFilter(textColorResolver.getRolloverColor()));
            Image rolloverImage = Toolkit.getDefaultToolkit().createImage(rolloverIcon);
            this.setRolloverIcon(new ImageIcon(rolloverImage));

            ImageProducer pressedIcon = new FilteredImageSource(templateImage.getSource(),
                    new ChangeRGBImageFilter(textColorResolver.getPressedColor()));
            Image pressedImage = Toolkit.getDefaultToolkit().createImage(pressedIcon);
            this.setPressedIcon(new ImageIcon(pressedImage));
        }

        if (text != null && textColorResolver != null) {
            final TextBehaviorResolver textBehaviorResolver = new DefaultTextBehaviorResolver(text, textColorResolver);
            this.setText(textBehaviorResolver.getDefaultText());
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setText(textBehaviorResolver.getMouseEnteredText());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setText(textBehaviorResolver.getDefaultText());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    setText(textBehaviorResolver.getMousePressedText());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setText(textBehaviorResolver.getMouseEnteredText());
                }
            });
        }
    }

//    private Image getImageFromIcon(Icon icon) {
//        if (icon instanceof ImageIcon) {
//            return ((ImageIcon) icon).getImage();
//        } else {
//            int w = icon.getIconWidth();
//            int h = icon.getIconHeight();
//            GraphicsEnvironment ge =
//                    GraphicsEnvironment.getLocalGraphicsEnvironment();
//            GraphicsDevice gd = ge.getDefaultScreenDevice();
//            GraphicsConfiguration gc = gd.getDefaultConfiguration();
//            BufferedImage image = gc.createCompatibleImage(w, h);
//            Graphics2D g = image.createGraphics();
//            icon.paintIcon(null, g, 0, 0);
//            g.dispose();
//            return image;
//        }
//    }

}
