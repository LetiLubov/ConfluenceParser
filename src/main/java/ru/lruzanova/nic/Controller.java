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
    static String folderPath = "/media/lyubov/240CF7E50CF7B042/Jet/conf/";
    static final String myResultDir = "dirWithResults/";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void doStaff() {
        JFileChooser fileChooser = new JFileChooser(folderPath);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Document document = Jsoup.parse(selectedFile, "UTF-8");
                Elements wikiContent = document.select("div.wiki-content");
                try {
                    String title = TranslitUtil.convert(document.title());
                    String pathToResult = folderPath + myResultDir + title + "/";
                    new File(pathToResult).mkdirs();
                    final File f = new File(pathToResult + title + ".html");
                    Elements imges = wikiContent.select("img");
                    new File(pathToResult + "img/").mkdirs();
                    for (Element img : imges) {
                        renameAndSetLoc(img, pathToResult + "img/");
                    }
                    FileUtils.writeStringToFile(f, wikiContent.outerHtml(), "UTF-8");
                } catch (IOException e) {
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void renameAndSetLoc(Element element, String dirPath) {
        String src = element.attr("src");
        String imgName = src.substring(src.lastIndexOf("/") + 1);
        String attributeValue = TranslitUtil.convert(imgName);
        element.attr("src", "img/" + attributeValue);
        //get file name from image path
        String strImagePath = src.substring(src.indexOf("/") + 1);

        try {
            //open the stream from URL
            File file = new File(folderPath + strImagePath);
            File myImage = new File(dirPath + attributeValue);
            FileUtils.copyFile(file, myImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFileFromDisk(File selectedFile){
        Document document;
        try {
            document = Jsoup.parse(selectedFile, "UTF-8");
            pcs.firePropertyChange("newDocument", null, "");
        } catch (IOException e) {
            return false;
        }
        Elements elements = document.select("div.wiki-content");

        if (elements == null){
            pcs.firePropertyChange("newDocument", null, "");
            return false;
        }

        if (elements.size() > 0){
            pcs.firePropertyChange("newDocument", null, document.title());
            return true;
        }
        return false;
    }

    /**
     * Задать слушателя изменения свойства с именем <code>propertyName</code>.
     *
     * @param propertyName
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Задать слушателя изменения свойств.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


}
