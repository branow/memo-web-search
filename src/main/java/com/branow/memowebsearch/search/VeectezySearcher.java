package com.branow.memowebsearch.search;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.parser.VeectezyParser;

import java.util.List;

public class VeectezySearcher extends DataSearcher {

    private final String SEARCH_IMAGE_FREE_AI_PAGE =
            "/search?qterm=[query]&content_type=image&license-free=true&ai_generated-ai_generated=true&page=[page]";

    public VeectezySearcher() {
        super("www.vecteezy.com");
    }

    public List<WebContainer<String>> getImagesUrlFreePageFirst(String phrase) {
        String query = phrase.replaceAll(" ", "-");
        String url = url() +  SEARCH_IMAGE_FREE_AI_PAGE.replace("[query]", query).replace("[page]", String.valueOf(1));
        return new VeectezyParser(get(url)).getImageUrls().stream()
                .map(e -> {
                    String[] sub = e.split("/");
                    String name = sub[sub.length - 1].split("\\.")[0];
                    return new WebContainer<>(domain, name, e);
                }).toList();
    }

}
