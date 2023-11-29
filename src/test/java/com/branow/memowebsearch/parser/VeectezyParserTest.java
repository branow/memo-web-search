package com.branow.memowebsearch.parser;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class VeectezyParserTest {

    @ParameterizedTest
    @MethodSource("provideGetImageUrls")
    public void getImageUrls(String url) throws IOException {
        List<String> urls = new VeectezyParser(Jsoup.connect(url).get()).getImageUrls();
        urls.forEach(System.out::println);
        Assertions.assertTrue(urls.size() >= 10);
    }

    private static Stream<Arguments> provideGetImageUrls() {
        return Stream.of(
                Arguments.of("https://www.vecteezy.com/search?qterm=my&content_type=image&ai_generated-ai_generated=true"),
                Arguments.of("https://www.vecteezy.com/search?content_type=image&qterm=my-account"),
                Arguments.of("https://www.vecteezy.com/search?content_type=image&qterm=card")
        );
    }

}
