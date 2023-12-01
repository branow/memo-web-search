package com.branow.memowebsearch.parser.items;

import java.util.List;
import java.util.Objects;

public class TopicSense {

    private String topic;
    private List<Sense> senses;

    public TopicSense() {
        this.senses = List.of();
    }

    public TopicSense(String topic, List<Sense> senses) {
        this.topic = topic;
        this.senses = senses;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicSense that = (TopicSense) o;
        return Objects.equals(topic, that.topic) && Objects.equals(senses, that.senses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, senses);
    }

    @Override
    public String toString() {
        return "TopicSense{" +
                "topic='" + topic + '\'' +
                ", senses=" + senses +
                '}';
    }
}
