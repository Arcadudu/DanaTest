package ru.arcadudu.danatest;

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

        //if (words.size() == translations.size()) {
        //    this.size = words.size();
        //}
    }

    //// getter

    public Map<String, String> getPack(){
        if(words.size()==translations.size()){
            for (int i = 0; i <words.size() ; i++) {
                if(words.get(i)!=null && translations.get(i)!=null){
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

        //     // list contains at least 4 words
        //if (this.words.size() >= 4) {
        //    for (int i = 0; i < 4; i++) {
        //        preview.append(words.get(i)).append(" ");
        //    }
        //    // list contains less than 4 words
        //} else {
        //    for (int i = 0; i < words.size(); i++) {
        //        preview.append(words.get(i)).append(" ");
        //    }
        //}

        for (String word : words) {
            preview.append(word+" ");
        }

        return preview.toString().trim();
    }

    //// setter

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /// add new pair to pack

    public void addPair(String newWord, String newTranslation) {
        pack.put(newWord, newTranslation);
    }
}
