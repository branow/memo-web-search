package com.branow.memowebsearch.searcher;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.search.OxfordLearnersDictionariesSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

}
