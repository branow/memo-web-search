package com.branow.memowebsearch.search.items;

import java.util.List;
import java.util.Objects;

public class EnglishSolidWord {

    private String word;
    private List<EnglishPartWord> partWordList;

    public EnglishSolidWord(String word, List<EnglishPartWord> partWordList) {
        this.word = word;
        this.partWordList = partWordList;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<EnglishPartWord> getPartWordList() {
        return partWordList;
    }

    public void setPartWordList(List<EnglishPartWord> partWordList) {
        this.partWordList = partWordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishSolidWord that = (EnglishSolidWord) o;
        return Objects.equals(word, that.word) && Objects.equals(partWordList, that.partWordList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partWordList);
    }

    @Override
    public String toString() {
        return "EnglishSolidWord{" +
                "word='" + word + '\'' +
                ", partWordList=" + partWordList +
                '}';
    }
}
