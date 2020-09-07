package ru.arcadudu.danatest.topic_selector;

import android.provider.ContactsContract;

import java.util.List;
import java.util.Map;

public class Topic {

    //// vars

    private String title;
    private int size;
    private List<String> words;
    private List<String> translations;
    private Map<String, String> pack;

    //// constructor

    public Topic(String title, List<String> words, List<String> translations) {
        this.title = title;
        this.words = words;
        this.translations = translations;
        this.size = words.size();
//        pack = getPack(words, translations);


        //if (words.size() == translations.size()) {
        //    this.size = words.size();
        //}
    }

    //// getter

    public Map<String, String> getPack(List<String> words, List<String> translations) {
        if (words.size() == translations.size()) {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i) != null && translations.get(i) != null) {
                    pack.put(words.get(i), translations.get(i));
                }
            }

        }
        return pack;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getTranslations() {
        return translations;
    }


    //// returns 4 or less words from the list

    public String getPreview() {

        StringBuilder preview = new StringBuilder();


        for (String word : words) {
            preview.append(word).append("   ");
        }

        return preview.toString().trim();
    }

    //// задать название топику

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /// добавить новую пару  слово -> перевод
    public void addPair(String newWord, String newTranslation) {
        pack.put(newWord, newTranslation);
    }
}
