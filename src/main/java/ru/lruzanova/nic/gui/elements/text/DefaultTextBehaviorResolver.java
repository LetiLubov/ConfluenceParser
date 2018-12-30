package ru.lruzanova.nic.gui.elements.text;

import ru.lruzanova.nic.gui.elements.color.TextColorResolver;

/**
 * Возвращает отображение текста при различных событиях
 *
 * @author Lyubov
 */
public class DefaultTextBehaviorResolver implements TextBehaviorResolver {
    private final String defaultText;
    private final String enteredText;
    private final String pressedText;

    public DefaultTextBehaviorResolver(String text, TextColorResolver textColorResolver) {
        defaultText = new StringBuilder("<html>").append(text).append("</html>").toString();
        enteredText = new StringBuilder("<html><font color=#")
                .append(Integer.toHexString(textColorResolver.getRolloverColor() & 0xffffff)).append("><u>")
                .append(text).append("</u></font></html>").toString();
        pressedText = new StringBuilder("<html><font color=#")
                .append(Integer.toHexString(textColorResolver.getPressedColor() & 0xffffff)).append("><u>")
                .append(text).append("</u></font></html>").toString();
    }

    @Override
    public String getMouseEnteredText() {
        return enteredText;
    }


    @Override
    public String getMousePressedText() {
        return pressedText;

    }

    @Override
    public String getDefaultText() {
        return defaultText;
    }
}
