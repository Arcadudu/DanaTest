package ru.arcadudu.danatest;

// Класс модели данных, отображающий конкретные поля, имеющиеся для привязки в холдер
class Topic {

    private String title;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
