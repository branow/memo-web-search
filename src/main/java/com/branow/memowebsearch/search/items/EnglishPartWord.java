package com.branow.memowebsearch.search.items;

import java.util.List;
import java.util.Objects;

public class EnglishPartWord {

    private String partOfSpeech;
    private String languageLevel;
    private String definition;
    private List<String> examples;

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
        EnglishPartWord partWord = (EnglishPartWord) o;
        return Objects.equals(partOfSpeech, partWord.partOfSpeech) && Objects.equals(languageLevel, partWord.languageLevel) && Objects.equals(definition, partWord.definition) && Objects.equals(examples, partWord.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partOfSpeech, languageLevel, definition, examples);
    }

    @Override
    public String toString() {
        return "EnglishPartWord{" +
                "partOfSpeech='" + partOfSpeech + '\'' +
                ", languageLevel='" + languageLevel + '\'' +
                ", definition='" + definition + '\'' +
                ", examples=" + examples +
                '}';
    }
}
