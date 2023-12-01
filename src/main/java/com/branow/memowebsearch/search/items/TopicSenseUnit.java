package com.branow.memowebsearch.search.items;

import java.util.List;
import java.util.Objects;

public class TopicSenseUnit {

    private String topic;
    private String languageLevel;
    private String definition;
    private List<String> examples;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        TopicSenseUnit unit = (TopicSenseUnit) o;
        return Objects.equals(topic, unit.topic) && Objects.equals(languageLevel, unit.languageLevel) && Objects.equals(definition, unit.definition) && Objects.equals(examples, unit.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, languageLevel, definition, examples);
    }

    @Override
    public String toString() {
        return "TopicSenseUnit{" +
                "topic='" + topic + '\'' +
                ", languageLevel='" + languageLevel + '\'' +
                ", definition='" + definition + '\'' +
                ", examples=" + examples +
                '}';
    }
}
