package ru.arcadudu.danatest.topic_selector;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Topic implements Comparable<Topic>{


    private String title; // имя топика
    private int size; // количество слов одного направления
    private List<String> words; // список слов
    private List<String> translations; // список переводов
    private Map<String, String> pack; // карта  (слово — перевод)

    private boolean testPassed = false;

    public boolean isTestPassed() {
        return testPassed;
    }

    public void setTestPassed(boolean testPassed) {
        this.testPassed = testPassed;
    }

    public Topic(String title, List<String> words, List<String> translations) {
        this.title = title;
        this.words = words;
        this.translations = translations;
        this.size = words.size();
    }
    public Topic(String title, List<String> words, boolean testPassed){
        this.title = title;
        this.words = words;
        this.size = words.size();
        this.testPassed = testPassed;
    }

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

    public String getPreview() {

        StringBuilder preview = new StringBuilder();


        for (String word : words) {
            preview.append(word).append("   ");
        }

        return preview.toString().trim();
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void addPair(String newWord, String newTranslation) {
        pack.put(newWord, newTranslation);
    }

    @Override
    public int compareTo(Topic o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    public static Comparator<Topic> compareByTitle = new Comparator<Topic>() {
        @Override
        public int compare(Topic topic, Topic otherTopic) {
            return topic.getTitle().compareTo(otherTopic.getTitle());
        }
    };

    public static Comparator<Topic> compareBySize = new Comparator<Topic>() {
        @Override
        public int compare(Topic topic, Topic otherTopic) {
            return otherTopic.getSize() - topic.getSize();
        }
    };

    public static Comparator<Topic> compareByStatus = new Comparator<Topic>() {
        @Override
        public int compare(Topic topic, Topic otherTopic) {
            return Boolean.compare(topic.isTestPassed(), otherTopic.isTestPassed());
        }
    };


}
