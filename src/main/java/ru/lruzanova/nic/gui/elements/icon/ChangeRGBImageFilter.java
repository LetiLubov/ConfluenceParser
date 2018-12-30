package ru.lruzanova.nic.gui.elements.icon;

import java.awt.image.RGBImageFilter;

/**
 * Фильтр для изменения цвета изображения на заданный, не трогает прозрачную часть изображения
 */
public class ChangeRGBImageFilter extends RGBImageFilter {
    private final int filterColor;

    public ChangeRGBImageFilter(int rgb) {
        filterColor = rgb;
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
          return (filterColor & 0x00ffffff) | (rgb & 0xff000000);
    }
}
