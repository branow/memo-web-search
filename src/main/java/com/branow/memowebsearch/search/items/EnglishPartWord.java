package com.branow.memowebsearch.search.items;

import java.util.List;
import java.util.Objects;

public class EnglishPartWord {

    private String partOfSpeech;
    private String transcription;
    private String audio;
    private String languageLevel;
    private String definition;
    private List<String> examples;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
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
        EnglishPartWord that = (EnglishPartWord) o;
        return Objects.equals(partOfSpeech, that.partOfSpeech) && Objects.equals(transcription, that.transcription) && Objects.equals(audio, that.audio) && Objects.equals(languageLevel, that.languageLevel) && Objects.equals(definition, that.definition) && Objects.equals(examples, that.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partOfSpeech, transcription, audio, languageLevel, definition, examples);
    }

    @Override
    public String toString() {
        return "EnglishPartWord{" +
                "partOfSpeech='" + partOfSpeech + '\'' +
                ", transcription='" + transcription + '\'' +
                ", audio='" + audio + '\'' +
                ", languageLevel='" + languageLevel + '\'' +
                ", definition='" + definition + '\'' +
                ", examples=" + examples +
                '}';
    }
}
