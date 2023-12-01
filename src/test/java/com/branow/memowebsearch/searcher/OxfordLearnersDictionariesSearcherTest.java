package com.branow.memowebsearch.searcher;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.parser.OxfordLearnersDictionariesParser;
import com.branow.memowebsearch.parser.items.EnglishWord;
import com.branow.memowebsearch.search.OxfordLearnersDictionariesSearcher;
import com.branow.memowebsearch.search.items.*;
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

public class OxfordLearnersDictionariesSearcherTest {

    private final OxfordLearnersDictionariesSearcher searcher = new OxfordLearnersDictionariesSearcher();

    @ParameterizedTest
    @MethodSource("provideSearchAudiosUrls")
    public void searchAudiosUrls(String word, List<String> expected) {
        List<WebContainer<String>> containers = searcher.searchAudiosUrls(word);
        containers.forEach(System.out::println);
        List<String> actual = containers.stream()
                .map(WebContainer::getData).toList();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideSearchEnglishWordSenseUnits")
    public void searchEnglishWordSenseUnits(String word) throws IOException {
        List<WebContainer<EnglishWordSenseUnit>> containers = searcher.searchEnglishWordSenseUnits(word);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(containers);
        System.out.println(json);
    }

    @ParameterizedTest
    @MethodSource("provideSearchEnglishWordUnits")
    public void searchEnglishWordUnits(String word, List<EnglishVariant> variants, List<PartOfSpeech> speech,
                                       List<WordDefComponent> components) throws IOException {
        List<EnglishWordUnit> containers = searcher.searchEnglishWordUnits(word, variants, speech, components);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(containers);
        System.out.println(json);
    }

    @ParameterizedTest
    @MethodSource("provideSearchEnglishSolidWord")
    public void searchEnglishSolidWord(String word) throws IOException {
        EnglishSolidWord containers = searcher.searchEnglishSolidWord(word);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(containers);
        System.out.println(json);
    }

    private static Stream<Arguments> provideSearchAudiosUrls() {
        return Stream.of(
                Arguments.of(
                        "dog",
                        List.of(
                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dog/dog__/dog__gb_2.mp3",
                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dog/dog__/dog__us_1_rr.mp3"
                        )
                ),
                Arguments.of(
                        "direct",
                        List.of(
                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dir/direc/direct__gb_7.mp3",
                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/d/dir/direc/direct__gb_8.mp3",
                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dir/direc/direct__us_1_rr.mp3",
                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/d/dir/direc/direct__us_2_rr.mp3"
                        )
                ),
                Arguments.of(
                        "vacuum cleaner",
                        List.of(
                                "https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/v/vac/vacuu/vacuum_cleaner_1_gb_3.mp3",
                                "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/v/vac/vacuu/vacuum_cleaner_1_us_3.mp3"
                        )
                )
        );
    }

    private static Stream<Arguments> provideSearchEnglishWordSenseUnits() {
        return Stream.of(
                Arguments.of("dog"),
                Arguments.of("direct"),
                Arguments.of("vacuum cleaner"),
                Arguments.of("stange val")
        );
    }

    private static Stream<Arguments> provideSearchEnglishSolidWord() {
        return Stream.of(
                Arguments.of("dog"),
                Arguments.of("direct"),
                Arguments.of("vacuum cleaner"),
                Arguments.of("stange val")
        );
    }

    private static Stream<Arguments> provideSearchEnglishWordUnits() {
        return Stream.of(
                Arguments.of(
                        "dog",
                        List.of(EnglishVariant.BRITISH),
                        List.of(PartOfSpeech.NOUN),
                        List.of(WordDefComponent.AUDIO)
                ),
                Arguments.of(
                        "direct",
                        List.of(EnglishVariant.BRITISH, EnglishVariant.AMERICAN),
                        List.of(PartOfSpeech.NOUN, PartOfSpeech.EXCLAMATION, PartOfSpeech.ADVERB),
                        List.of(WordDefComponent.AUDIO, WordDefComponent.EXAMPLE)
                ),
                Arguments.of(
                        "vacuum cleaner",
                        List.of(EnglishVariant.BRITISH),
                        List.of(PartOfSpeech.NOUN),
                        List.of(WordDefComponent.TRANSCRIPTION)
                ),
                Arguments.of(
                        "stange val",
                        List.of(EnglishVariant.BRITISH),
                        List.of(PartOfSpeech.NOUN),
                        List.of(WordDefComponent.AUDIO)
                )
        );
    }

}
