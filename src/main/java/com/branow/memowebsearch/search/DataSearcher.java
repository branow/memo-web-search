package com.branow.memowebsearch.search;

import com.branow.memowebsearch.exception.SiteConnectException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DataSearcher {

    protected final String domain;

    public DataSearcher(String domain) {
        this.domain = domain;
    }

    protected String url() {
        return "https://" + domain;
    }

    protected Document get(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new SiteConnectException(url, e);
        }
    }
}
