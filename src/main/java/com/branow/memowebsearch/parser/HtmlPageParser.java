package com.branow.memowebsearch.parser;

import org.jsoup.nodes.Document;

import java.util.Objects;

public class HtmlPageParser {

    protected final Document doc;

    public HtmlPageParser(Document doc) {
        this.doc = doc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HtmlPageParser that = (HtmlPageParser) o;
        return Objects.equals(doc, that.doc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doc);
    }

    protected boolean isNull(Object o) {
        return o == null;
    }
}
