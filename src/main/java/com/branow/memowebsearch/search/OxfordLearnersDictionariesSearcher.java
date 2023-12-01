package com.branow.memowebsearch.search;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.exception.SiteConnectException;
import com.branow.memowebsearch.parser.OxfordLearnersDictionariesParser;
import com.branow.memowebsearch.parser.items.*;
import com.branow.memowebsearch.search.items.EnglishWordSenseUnit;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class OxfordLearnersDictionariesSearcher extends DataSearcher {

    private final String DEFINITION_ENGLISH = "/definition/english/[query]_[number]";

    public OxfordLearnersDictionariesSearcher() {
        super("www.oxfordlearnersdictionaries.com");
    }

    public List<WebContainer<EnglishWordSenseUnit>> searchEnglishWordSenseUnits(String word) {
        String query = word.replaceAll(" ", "-");
        List<WebContainer<EnglishWordSenseUnit>> senses = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String url = url() + DEFINITION_ENGLISH.replace("[query]", query)
                    .replace("[number]", String.valueOf(i + 1));
            Document doc = null;
            try {
                doc = get(url);
            } catch (SiteConnectException e) {
                break;
            }
            EnglishWord w = new OxfordLearnersDictionariesParser(doc)
                    .getEnglishWord();
            senses.addAll(convert(w));
        }
        return senses.stream().distinct().toList();
    }

    public List<WebContainer<String>> searchAudiosUrls(String word) {
        String query = word.replaceAll(" ", "-");
        List<WebContainer<String>> audios = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String url = url() + DEFINITION_ENGLISH.replace("[query]", query)
                    .replace("[number]", String.valueOf(i + 1));
            Document doc = null;
            try {
                doc = get(url);
            } catch (SiteConnectException e) {
                break;
            }
            EnglishPronunciations p = new OxfordLearnersDictionariesParser(doc)
                    .getEnglishPronunciations();
            audios.addAll(convert("british", p.getUk().stream().map(Pronunciation::getAudioUrl).toList()));
            audios.addAll(convert("american", p.getUs().stream().map(Pronunciation::getAudioUrl).toList()));
        }
        return audios.stream().distinct().toList();
    }

    private List<WebContainer<String>> convert(String prefix, List<String> audios) {
        List<WebContainer<String>> containers = new ArrayList<>();
        for (int i = 0; i<audios.size(); i++) {
            containers.add(new WebContainer<>(domain, prefix + "_" + (i + 1), audios.get(i)));
        }
        return containers;
    }

    private List<WebContainer<EnglishWordSenseUnit>> convert(EnglishWord word) {
        List<WebContainer<EnglishWordSenseUnit>> res = new ArrayList<>();
        String w = word.getWord();
        String part = word.getPartOfSpeech();
        for (TopicSense ts: word.getSenses()) {
            String topic = ts.getTopic();
            if (!ts.getSenses().isEmpty()) {
                Sense sense = ts.getSenses().get(0);
                List<String> examples = sense.getExamples();
                EnglishWordSenseUnit ewsu = new EnglishWordSenseUnit(w, part, sense.getLanguageLevel(),
                        sense.getDefinition(), topic, examples.subList(0, Math.min(examples.size(), 3)));
                res.add(new WebContainer<>(domain, w, ewsu));
            }
        }
        return res;
    }

}
