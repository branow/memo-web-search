package com.branow.memowebsearch.parser.items;

import java.util.List;
import java.util.Objects;

public class EnglishPronunciations {

    private List<Pronunciation> uk;
    private List<Pronunciation> us;

    public EnglishPronunciations() {

    }

    public EnglishPronunciations(List<Pronunciation> uk, List<Pronunciation> us) {
        this.uk = uk;
        this.us = us;
    }

    public List<Pronunciation> getUk() {
        return uk;
    }

    public void setUk(List<Pronunciation> uk) {
        this.uk = uk;
    }

    public List<Pronunciation> getUs() {
        return us;
    }

    public void setUs(List<Pronunciation> us) {
        this.us = us;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishPronunciations that = (EnglishPronunciations) o;
        return Objects.equals(uk, that.uk) && Objects.equals(us, that.us);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uk, us);
    }

    @Override
    public String toString() {
        return "EnglishPronunciations{" +
                "uk=" + uk +
                ", us=" + us +
                '}';
    }
}
