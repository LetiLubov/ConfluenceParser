package ru.lruzanova.nic;


import org.jsoup.nodes.Document;


public interface SettingContext {
    String getSaveDirPath();

    Document getDocument();

    String getPathToImg();

    String getNewFileName();

    boolean needConvertCyrilic();

    boolean needRemoveColorText();

    String getTextBeforeContain();

    String getTextAfterContain();
}
