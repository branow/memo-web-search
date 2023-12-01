package com.branow.memowebsearch.search.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnglishWordSenseUnit {

    private String word;
    private String partOfSpeech;
    private String languageLevel;
    private String definition;
    private String topic;
    private List<String> examples;

    public EnglishWordSenseUnit() {
        this.examples = new ArrayList<>();
    }

    public EnglishWordSenseUnit(String word, String partOfSpeech, String languageLevel, String definition, String topic, List<String> examples) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.languageLevel = languageLevel;
        this.definition = definition;
        this.topic = topic;
        this.examples = examples;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishWordSenseUnit that = (EnglishWordSenseUnit) o;
        return Objects.equals(word, that.word) && Objects.equals(partOfSpeech, that.partOfSpeech) && Objects.equals(languageLevel, that.languageLevel) && Objects.equals(definition, that.definition) && Objects.equals(topic, that.topic) && Objects.equals(examples, that.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech, languageLevel, definition, topic, examples);
    }

    @Override
    public String toString() {
        return "EnglishWordSenseUnit{" +
                "word='" + word + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", languageLevel='" + languageLevel + '\'' +
                ", definition='" + definition + '\'' +
                ", topic='" + topic + '\'' +
                ", examples=" + examples +
                '}';
    }
}
