package ru.lruzanova.nic;


import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public class Controller {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public SettingContext context;
    public Document checkUrl(String url){
//        Document doc;
//        try {
//            doc = Jsoup.connect(url).get();
//            doc.select("#main-content");
//            String title = doc.title();
//            FileUtils.writeStringToFile(new File("qwe"), doc.outerHtml(), "UTF-8");
//            return doc;
//        } catch (IOException e) {
//
//        }
        return null;
    }
    public Document checkFileFromDisk(File selectedFile) {
        Document document;
        try {
            document = Jsoup.parse(selectedFile, "UTF-8");
            pcs.firePropertyChange("newDocument", null, "");
        } catch (IOException e) {
            return null;
        }
        Elements elements = document.select("div.wiki-content");

        if (elements == null) {
            pcs.firePropertyChange("newDocument", null, "");
            return null;
        }

        if (elements.size() > 0) {
            pcs.firePropertyChange("newDocument", null, document.title());
            return document;
        }
        return null;
    }


    /**
     * Задать слушателя изменения свойств.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void save(SettingContext context) {

        this.context = context;
        Document document = context.getDocument();

        Elements wikiContent = document.select("div.wiki-content");
        try {
            String pathToResult = context.getSaveDirPath() + "/" + context.getNewFileName() + "/";
            new File(pathToResult).mkdirs();
            final File f = new File(pathToResult + context.getNewFileName() + ".html");
            saveImages(wikiContent, pathToResult);

            if (context.needRemoveColorText()){
                removeColorText(wikiContent);
            }

            String data = wikiContent.outerHtml();
            FileUtils.writeStringToFile(f, context.getTextBeforeContain() + data + context.getTextAfterContain(), "UTF-8");
            JOptionPane.showMessageDialog(null, "Сохранилося");
        } catch (IOException e) {
        }

    }

    private void saveImages(Elements wikiContent, String pathToResult) {
        Elements imges = wikiContent.select("img");
        new File(pathToResult + "img/").mkdirs();
        for (Element img : imges) {
            saveImg(img, pathToResult + "img/");
        }
    }

    private void saveImg(Element element, String dirPath) {
        String src = element.attr("src");

        String imgName = src.substring(src.lastIndexOf("/") + 1);
        String attributeValue = context.needConvertCyrilic() ? TranslitUtil.convert(imgName) : imgName;
        element.attr("src", "img/" + attributeValue);
        //get file name from image path

        try {
            //open the stream from URL
            File file = new File(context.getPathToImg());
            File myImage = new File(dirPath + attributeValue);
            FileUtils.copyFile(file, myImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeColorText(Elements wikiContent){
        Elements elements = wikiContent.select("span");
        for (Element element : elements) {
            if (!element.toString().contains("rgb(0,0,0)")){
                element.remove();
            }
        }
    }

}
