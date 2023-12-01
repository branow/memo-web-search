package com.branow.memowebsearch.parser;

import com.branow.memowebsearch.parser.items.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class OxfordLearnersDictionariesParser extends HtmlPageParser {

    public OxfordLearnersDictionariesParser(Document doc) {
        super(doc);
    }

    public EnglishWord getEnglishWord() {
        return wrap(() -> parseEnglishWord(doc), new EnglishWord());
    }

    private EnglishWord parseEnglishWord(Element src) {
        Element entry = src.select(".entry").first();
        Element top = entry.select(".webtop").first();
        String word = wrapNull(() -> parseWord(top));
        String partOfSpeech = wrapNull(() -> parsePartOfSpeech(top));
        EnglishPronunciations pronunciations = wrap(() -> parseEnglishPronunciations(top), new EnglishPronunciations());
        List<TopicSense> topicSenses = wrapList(() -> parseTopicSenses(entry));
        return new EnglishWord(word, partOfSpeech, pronunciations, topicSenses);
    }

    private List<TopicSense> parseTopicSenses(Element src) {
        Elements pvgs = src.select(".pv-g");
        if (!pvgs.isEmpty()) {
            return pvgs.stream().map(this::parseTopicSensePvg).toList();
        }
        Element senses = src.selectFirst(".senses_multiple");
        if (senses != null) {
            Elements shcutgs = senses.select(".shcut-g");
            if (!shcutgs.isEmpty()) {
                return shcutgs.stream().map(this::parseTopicSenseShcutg).toList();
            } else {
                return List.of(parseTopicSenseSingle(senses));
            }

        }
        Element sense = src.selectFirst(".sense_single");
        return List.of(wrap(() -> parseTopicSenseSingle(sense), new TopicSense()));
    }

    private TopicSense parseTopicSensePvg(Element src) {
        String topic = wrapNull(() -> parseTopicPvg(src));
        List<Sense> senses = wrapList(() -> parseSenses(src));
        return new TopicSense(topic, senses);
    }

    private TopicSense parseTopicSenseShcutg(Element src) {
        String topic = wrapNull(() -> parseTopicShcutg(src));
        List<Sense> senses = wrapList(() -> parseSenses(src));
        return new TopicSense(topic, senses);
    }

    private TopicSense parseTopicSenseSingle(Element src) {
        List<Sense> senses = wrapList(() -> parseSenses(src));
        return new TopicSense(null, senses);
    }

    private String parseTopicPvg(Element src) {
        return src.selectFirst(".pv").text();
    }

    private String parseTopicShcutg(Element src) {
        return src.selectFirst(".shcut").text();
    }

    private List<Sense> parseSenses(Element src) {
        Elements senses = src.select(".sense");
        return senses.stream().map(this::parseSense).toList();
    }

    private Sense parseSense(Element src) {
        String languageLevel = wrapNull(() -> parseLanguageLevel(src));
        String definition = wrapNull(() -> parseDefinition(src));
        List<String> examples = wrapList(() -> parseExamples(src));
        return new Sense(languageLevel, null, definition, examples);
    }

    private String parseLanguageLevel(Element src) {
        Element symbols = src.selectFirst(".symbols");
        Element a = symbols.selectFirst("a");
        String href = a.attr("href");
        String l = "level=";
        return href.substring(href.indexOf(l) + l.length());
    }

    private String parseDefinition(Element src) {
        return src.selectFirst(".def").text();
    }

    private List<String> parseExamples(Element src) {
        Element examples = src.selectFirst(".examples");
        return examples.select("li").stream().map(this::parseExample).toList();
    }

    private String parseExample(Element src) {
        return src.selectFirst(".x").text();
    }

    public EnglishPronunciations getEnglishPronunciations() {
        return wrap(() -> parseEnglishPronunciations(doc), new EnglishPronunciations());
    }

    private EnglishPronunciations parseEnglishPronunciations(Element src) {
        Element phonetics = src.selectFirst(".phonetics");
        List<Pronunciation> uk = wrapList(() -> parsePronunciations(phonetics.child(0)));
        List<Pronunciation> us = wrapList(() -> parsePronunciations(phonetics.child(1)));
        return new EnglishPronunciations(uk, us);
    }

    private String parseWord(Element src) {
        return src.selectFirst(".headword").text();
    }

    private String parsePartOfSpeech(Element src) {
        return src.selectFirst(".pos").text();
    }

    private List<Pronunciation> parsePronunciations(Element src) {
        List<String> url = src.select(".sound").stream().map((e) -> e.attr("data-src-mp3")).toList();
        List<String> tr = src.select(".phon").stream().map((e) -> e.text().replaceAll("/", "")).toList();
        List<Pronunciation> pr = new ArrayList<>();
        for (int i = 0; i < url.size(); i++) {
            pr.add(new Pronunciation(url.get(i), tr.get(i)));
        }
        return pr;
    }

}
