package ru.arcadudu.danatest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

    // vars
    private String name;
    private int length;

    private List<String> words;
    private List<String> translations;

    private Map<String, String> pairs;


    //constructors
    public Dictionary(String name, List<String> words, List<String> translations) {
        this.name = name;
        this.words = words;
        this.translations = translations;

        if (words.size() == translations.size()) {
            for (int i = 0; i < words.size(); i++) {
                if (pairs.containsKey(words.get(i))) {
                    continue;
                } else {
                    pairs.put(words.get(i), translations.get(i));
                }
            }
        }

        this.length = pairs.size();
    }

    public Dictionary(String name, String word, String translation) {
        Map<String, String> map = new HashMap<>();
        this.name = name;
        map.put(word, translation);
        this.length = map.size();
    }


    // getters
    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getTranslations() {
        return translations;
    }

    // add pair of words to existing dictionary
    public void addPair(Map<String, String> pairs, String word, String translation) {
        pairs.put(word, translation);
    }


}
