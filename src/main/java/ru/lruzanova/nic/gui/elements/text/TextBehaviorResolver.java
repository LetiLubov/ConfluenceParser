package ru.lruzanova.nic.gui.elements.text;

/**
 * Возвращает отображение текста при различных событиях
 *
 * @author Lyubov
 */
public interface TextBehaviorResolver {
    /**
     *
     * @return отображение текста при наведении на него мыши
     */
    String getMouseEnteredText();

    /**
     *
     * @return отображение текста в дефолтном состоянии
     */
    String getDefaultText();

    /**
     *
     * @return отображение текста при нажатии
     */
    String getMousePressedText();
}
