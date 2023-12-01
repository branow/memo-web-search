package com.branow.memowebsearch.parser;

import com.branow.memowebsearch.parser.items.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class OxfordLearnersDictionariesParserTest {

    @ParameterizedTest
    @MethodSource("provideGetEnglishPronunciations")
    public void getEnglishPronunciations(String srcUrl, EnglishPronunciations expected) throws IOException {
        Document doc = Jsoup.connect(srcUrl).get();
        OxfordLearnersDictionariesParser parser = new OxfordLearnersDictionariesParser(doc);
        EnglishPronunciations actual = parser.getEnglishPronunciations();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideGetEnglishWord")
    public void getEnglishWord(String srcUrl) throws IOException {
        Document doc = Jsoup.connect(srcUrl).get();
        OxfordLearnersDictionariesParser parser = new OxfordLearnersDictionariesParser(doc);
        EnglishWord actual = parser.getEnglishWord();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(actual);
        System.out.println(json);
    }

    private static Stream<Arguments> provideGetEnglishPronunciations() {
        return Stream.of(
                Arguments.of(
                        "https://www.oxfordlearnersdictionaries.com/definition/english/dog_1?q=dog",
                        new EnglishPronunciations(
                                List.of(
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dog/dog__/dog__gb_2.mp3",
                                                "dɒɡ"
                                        )
                                ),
                                List.of(
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dog/dog__/dog__us_1_rr.mp3",
                                                "dɔːɡ"
                                        )
                                )
                        )
                ),
                Arguments.of(
                        "https://www.oxfordlearnersdictionaries.com/definition/english/direct_1?q=direct",
                        new EnglishPronunciations(
                                List.of(
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dir/direc/direct__gb_7.mp3",
                                                "dəˈrekt"
                                        ),
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dir/direc/direct__gb_8.mp3",
                                                "daɪˈrekt"
                                        )
                                ),
                                List.of(
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dir/direc/direct__us_1_rr.mp3",
                                                "dəˈrekt"
                                        ),
                                        new Pronunciation(
                                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dir/direc/direct__us_2_rr.mp3",
                                                "daɪˈrekt"
                                        )
                                )
                        )
                )
        );
    }

    private static Stream<Arguments> provideGetEnglishWord() {
        return Stream.of(
                Arguments.of("https://www.oxfordlearnersdictionaries.com/definition/english/put-on_1?q=put+on"),
                Arguments.of("https://www.oxfordlearnersdictionaries.com/definition/english/direct_1?q=direct"),
                Arguments.of("https://www.oxfordlearnersdictionaries.com/definition/english/vacuum-cleaner?q=vacuum+cleaner"),
                Arguments.of("https://www.oxfordlearnersdictionaries.com/spellcheck/english/?q=stange+val")
        );
    }

}
