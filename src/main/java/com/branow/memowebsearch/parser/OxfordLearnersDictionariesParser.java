package com.branow.memowebsearch.parser;

import com.branow.memowebsearch.parser.items.EnglishPronunciations;
import com.branow.memowebsearch.parser.items.Pronunciation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class OxfordLearnersDictionariesParser extends HtmlPageParser {

    public OxfordLearnersDictionariesParser(Document doc) {
        super(doc);
    }

    public EnglishPronunciations getEnglishPronunciations() {
        Element phonetics = doc.select(".phonetics").first();
        if (isNull(phonetics)) return new EnglishPronunciations();
        List<Pronunciation> uk = getPronunciations(phonetics.child(0));
        List<Pronunciation> us = getPronunciations(phonetics.child(1));
        return new EnglishPronunciations(uk, us);
    }


    private List<Pronunciation> getPronunciations(Element src) {
        if (isNull(src)) return List.of();
        List<String> url = src.select(".sound").stream().map((e) -> e.attr("data-src-mp3")).toList();
        List<String> tr = src.select(".phon").stream().map((e) -> e.text().replaceAll("/", "")).toList();
        List<Pronunciation> pr = new ArrayList<>();
        for (int i = 0; i < url.size(); i++) {
            pr.add(new Pronunciation(url.get(i), tr.get(i)));
        }
        return pr;
    }

}
