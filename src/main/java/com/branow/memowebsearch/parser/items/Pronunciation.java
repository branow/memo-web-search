package com.branow.memowebsearch.parser.items;

import java.util.Objects;

public class Pronunciation {

    private String audioUrl;
    private String transcription;

    public Pronunciation() {

    }

    public Pronunciation(String audioUrl, String transcription) {
        this.audioUrl = audioUrl;
        this.transcription = transcription;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pronunciation that = (Pronunciation) o;
        return Objects.equals(audioUrl, that.audioUrl) && Objects.equals(transcription, that.transcription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(audioUrl, transcription);
    }

    @Override
    public String toString() {
        return "Pronunciation{" +
                "audioUrl='" + audioUrl + '\'' +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
