package ru.lruzanova.nic.gui.elements.color;

/**
 * Оттенки серого для нашего ui
 *
 * @author Lyubov
 */
public class GrayShadesColorResolver implements ColorResolver {
    public GrayShadesColorResolver() {
    }

    @Override
    public int getDefaultColor() {
        return 0x262626;
    }

    @Override
    public int getPressedColor() {
        return 0xb3b3b3;
    }

    @Override
    public int getSelectedColor() {
        return 0x262626;
    }

    @Override
    public int getDisableColor() {
        return 0xA2A2A2;
    }

    @Override
    public int getDisableSelectedColor() {
        return 0xA2A2A2;
    }

    @Override
    public int getRolloverColor() {
        return 0x666666;
    }

    @Override
    public int getRolloverSelectedColor() {
        return 0x666666;
    }
}
