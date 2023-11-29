package com.branow.memowebsearch.exception;

public class SiteConnectException extends RuntimeException {

    public SiteConnectException(String url) {
        this(url, null);
    }

    public SiteConnectException(String url, Throwable cause) {
        super("Cannot connect to site: " + url, cause);
    }
}
