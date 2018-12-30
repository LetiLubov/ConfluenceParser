package ru.lruzanova.nic.gui.elements.color;

/**
 * Набор цветовых решений для работы с текстовыми компонентами
 *
 * @author Lyubov
 */
public interface TextColorResolver {

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
    int getRolloverColor();
}
