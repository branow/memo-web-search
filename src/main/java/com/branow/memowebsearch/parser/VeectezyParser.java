package com.branow.memowebsearch.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Objects;

public class VeectezyParser extends HtmlPageParser {

    public VeectezyParser(Document doc) {
        super(doc);
    }

    public List<String> getImageUrls() {
        Element result = doc.select("div.ez-search-results").first();
        if (isNull(result)) return List.of();
        Element grid = result.select("ul.ez-resource-grid").first();
        if (isNull(grid)) return List.of();
        return grid.select("li").stream()
                .map(e -> {
                    Element img = e.select("img").first();
                    if (isNull(img)) return null;
                    return img.attr("src");
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
