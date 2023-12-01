package com.branow.memowebsearch.searcher;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.search.VeectezySearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class VeectezySearcherTest {

    private final VeectezySearcher searcher = new VeectezySearcher();


    @ParameterizedTest
    @MethodSource("provideGetImagesUrlFreePageFirst")
    public void getImagesUrlFreePageFirst(String phrase) {
        List<WebContainer<String>> containers = searcher.searchImagesUrlFreePageFirst(phrase);
        containers.forEach(System.out::println);
        List<String> urls = containers.stream()
                .map(WebContainer::getData).toList();
        Assertions.assertTrue(urls.size() >= 10);
    }

    private static Stream<Arguments> provideGetImagesUrlFreePageFirst() {
        return Stream.of(
                Arguments.of("vacuum cleaner"),
                Arguments.of("rainy"),
                Arguments.of("implementation")
        );
    }

}
