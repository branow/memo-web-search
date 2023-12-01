package com.branow.memowebsearch.search.items;

import java.util.List;
import java.util.Objects;

public class EnglishSolidWord {

    private String word;
    private String audio;
    private String transcription;
    private List<EnglishPartWord> partWordList;

    public EnglishSolidWord(String word, String audio, String transcription, List<EnglishPartWord> partWordList) {
        this.word = word;
        this.audio = audio;
        this.transcription = transcription;
        this.partWordList = partWordList;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
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
        return Objects.equals(word, that.word) && Objects.equals(audio, that.audio) && Objects.equals(transcription, that.transcription) && Objects.equals(partWordList, that.partWordList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, audio, transcription, partWordList);
    }

    @Override
    public String toString() {
        return "EnglishSolidWord{" +
                "word='" + word + '\'' +
                ", audio='" + audio + '\'' +
                ", transcription='" + transcription + '\'' +
                ", partWordList=" + partWordList +
                '}';
    }
}
