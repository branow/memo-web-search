package com.branow.memowebsearch;

import java.util.Objects;

public class WebContainer<T> {

    private final String site;
    private final String name;
    private final T data;

    public WebContainer(String site, String name, T data) {
        this.site = site;
        this.name = name;
        this.data = data;
    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebContainer<?> that = (WebContainer<?>) o;
        return Objects.equals(site, that.site) && Objects.equals(name, that.name) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site, name, data);
    }

    @Override
    public String toString() {
        return "WebContainer{" +
                "site='" + site + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
