package ru.lruzanova.nic.gui.elements.color;

/**
 * Набор цветовых решений для работы с любыми компонентами
 *
 * @author Lyubov
 */
public interface ColorResolver extends TextColorResolver {
    /**
     * @return возвращает цвет
     */
    int getDefaultColor();

    /**
     * @return возвращает цвет
     */
    int getPressedColor();

    /**
     * @return возвращает цвет
     */
    int getSelectedColor();

    /**
     * @return возвращает цвет
     */
    int getDisableColor();

    /**
     * @return возвращает цвет
     */
    int getDisableSelectedColor();

    /**
     * @return возвращает цвет
     */
    int getRolloverColor();

    /**
     * @return возвращает цвет
     */
    int getRolloverSelectedColor();
}
