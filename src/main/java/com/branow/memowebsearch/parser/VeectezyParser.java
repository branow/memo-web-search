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
        return wrapList(() -> parseImageUrls(doc));
    }

    private List<String> parseImageUrls(Element src) {
        Element result = doc.selectFirst("div.ez-search-results");
        Element grid = result.selectFirst("ul.ez-resource-grid");
        return grid.select("li").stream()
                .map(e -> {
                    Element img = e.selectFirst("img");
                    if (isNull(img)) return null;
                    return img.attr("src");
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
