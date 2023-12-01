package com.branow.memowebsearch.parser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

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

    protected <T> T wrap(Supplier<T> supplier, T other) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return other;
        }
    }

    protected <T> T wrapNull(Supplier<T> supplier) {
        return wrap(supplier, null);
    }

    protected <T> List<T> wrapList(Supplier<List<T>> supplier) {
        return wrap(supplier, List.of());
    }
}
