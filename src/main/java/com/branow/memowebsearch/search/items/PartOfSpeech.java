package com.branow.memowebsearch.search.items;

public enum PartOfSpeech {

    NOUN("noun"), ADJECTIVE("adjective"), ADVERB("adverb"), PRONOUN("pronoun"),
    PREPOSITION("preposition"), VERB("verb"), PHRASAL_VERB("phrasal verb"),
    EXCLAMATION("exclamation");

    public static PartOfSpeech parse(String value) {
        for (PartOfSpeech sp: PartOfSpeech.values()) {
            if (sp.value.equals(value)) {
                return sp;
            }
        }
        throw new IllegalStateException("Part Of Speech Not Found");
    }

    private final String value;

    PartOfSpeech(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
