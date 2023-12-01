package com.branow.memowebsearch.parser.items;

import java.util.List;
import java.util.Objects;

public class EnglishWord {

    private String word;
    private String partOfSpeech;
    private EnglishPronunciations pronunciations;
    private List<TopicSense> senses;

    public EnglishWord() {
        this.pronunciations = new EnglishPronunciations();
        this.senses = List.of();
    }

    public EnglishWord(String word, String partOfSpeech, EnglishPronunciations pronunciations, List<TopicSense> senses) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.pronunciations = pronunciations;
        this.senses = senses;
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

    public EnglishPronunciations getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(EnglishPronunciations pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<TopicSense> getSenses() {
        return senses;
    }

    public void setSenses(List<TopicSense> senses) {
        this.senses = senses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishWord that = (EnglishWord) o;
        return Objects.equals(word, that.word) && Objects.equals(partOfSpeech, that.partOfSpeech) && Objects.equals(pronunciations, that.pronunciations) && Objects.equals(senses, that.senses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech, pronunciations, senses);
    }

    @Override
    public String toString() {
        return "EnglishWord{" +
                "word='" + word + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", pronunciations=" + pronunciations +
                ", senses=" + senses +
                '}';
    }
}
