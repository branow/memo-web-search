package com.branow.memowebsearch.parser.items;

import java.util.List;
import java.util.Objects;

public class Sense {

    private String languageLevel;
    private String description;
    private String definition;
    private List<String> examples;

    public Sense() {
        this.examples = List.of();
    }

    public Sense(String languageLevel, String description, String definition, List<String> examples) {
        this.languageLevel = languageLevel;
        this.description = description;
        this.definition = definition;
        this.examples = examples;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Sense sense = (Sense) o;
        return Objects.equals(languageLevel, sense.languageLevel) && Objects.equals(description, sense.description) && Objects.equals(definition, sense.definition) && Objects.equals(examples, sense.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageLevel, description, definition, examples);
    }

    @Override
    public String toString() {
        return "Sense{" +
                "languageLevel='" + languageLevel + '\'' +
                ", description='" + description + '\'' +
                ", definition='" + definition + '\'' +
                ", examples=" + examples +
                '}';
    }
}
