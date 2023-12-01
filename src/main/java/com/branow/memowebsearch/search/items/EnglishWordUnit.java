package com.branow.memowebsearch.search.items;

import com.branow.memowebsearch.parser.items.EnglishPronunciations;

import java.util.List;
import java.util.Objects;

public class EnglishWordUnit {

    private String word;
    private String partOfSpeech;
    private EnglishPronunciations pronunciations;
    private List<TopicSenseUnit> senses;


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

    public List<TopicSenseUnit> getSenses() {
        return senses;
    }

    public void setSenses(List<TopicSenseUnit> senses) {
        this.senses = senses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishWordUnit that = (EnglishWordUnit) o;
        return Objects.equals(word, that.word) && Objects.equals(partOfSpeech, that.partOfSpeech) && Objects.equals(pronunciations, that.pronunciations) && Objects.equals(senses, that.senses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech, pronunciations, senses);
    }

    @Override
    public String toString() {
        return "EnglishWordUnit{" +
                "word='" + word + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", pronunciations=" + pronunciations +
                ", senses=" + senses +
                '}';
    }
}
