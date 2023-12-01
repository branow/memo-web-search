package com.branow.memowebsearch.search;

import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.exception.SiteConnectException;
import com.branow.memowebsearch.parser.OxfordLearnersDictionariesParser;
import com.branow.memowebsearch.parser.items.*;
import com.branow.memowebsearch.search.items.*;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public EnglishSolidWord searchEnglishSolidWord(String word) {
        String query = word.replaceAll(" ", "-");
        List<EnglishPartWord> partWordList = new ArrayList<>();
        String foundWord = word;
        for (int i = 0; i < 7; i++) {
            String url = url() + DEFINITION_ENGLISH.replace("[query]", query)
                    .replace("[number]", String.valueOf(i + 1));
            Document doc = null;
            try {
                doc = get(url);
            } catch (SiteConnectException e) {
                break;
            }
            EnglishWord w = new OxfordLearnersDictionariesParser(doc).getEnglishWord();
            partWordList.add(toEnglishPartWord(w));
            foundWord = w.getWord();
        }
        return new EnglishSolidWord(foundWord, partWordList);
    }

    private EnglishPartWord toEnglishPartWord(EnglishWord word) {
        EnglishPartWord partWord = new EnglishPartWord();
        partWord.setPartOfSpeech(word.getPartOfSpeech());
        if (!word.getPronunciations().getUs().isEmpty()) {
            partWord.setTranscription(word.getPronunciations().getUs().get(0).getTranscription());
            partWord.setAudio(word.getPronunciations().getUs().get(0).getAudioUrl());
        }
        if (!word.getSenses().isEmpty() && !word.getSenses().get(0).getSenses().isEmpty()) {
            Sense sense = word.getSenses().get(0).getSenses().get(0);
            partWord.setDefinition(sense.getDefinition());
            partWord.setLanguageLevel(sense.getLanguageLevel());
            List<String> examples = sense.getExamples();
            partWord.setExamples(examples.subList(0, Math.min(3, examples.size())));
        }
        return partWord;
    }


    public  List<EnglishWordUnit> searchEnglishWordUnits(String word, List<EnglishVariant> variants, List<PartOfSpeech> speech,
                                                         List<WordDefComponent> components) {
        List<EnglishWordUnit> units = new ArrayList<>();
        String query = word.replaceAll(" ", "-");
        for (int i = 0; i < 7; i++) {
            String url = url() + DEFINITION_ENGLISH.replace("[query]", query)
                    .replace("[number]", String.valueOf(i + 1));
            Document doc = null;
            try {
                doc = get(url);
            } catch (SiteConnectException e) {
                break;
            }
            EnglishWord w = new OxfordLearnersDictionariesParser(doc).getEnglishWord();
            if (w.getPartOfSpeech() == null || speech.contains(PartOfSpeech.parse(w.getPartOfSpeech()))) {
                units.add(convert(w, variants, components));
            }
        }
        return units;
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

    private EnglishWordUnit convert(EnglishWord word, List<EnglishVariant> variants,  List<WordDefComponent> components) {
        EnglishWordUnit unit = new EnglishWordUnit();
        unit.setWord(word.getWord());
        unit.setPartOfSpeech(word.getPartOfSpeech());
        unit.setPronunciations(filter(word.getPronunciations(), variants, components));
        List<TopicSenseUnit> senseUnits = word.getSenses().subList(0, Math.min(word.getSenses().size(), 3))
                .stream().map((s) -> convert(s, components)).toList();
        unit.setSenses(senseUnits);
        return unit;
    }

    private TopicSenseUnit convert(TopicSense topicSense, List<WordDefComponent> components) {
        TopicSenseUnit unit = new TopicSenseUnit();
        if (!topicSense.getSenses().isEmpty()) {
            unit.setTopic(topicSense.getTopic());
            Sense sense = topicSense.getSenses().get(0);
            unit.setDefinition(sense.getDefinition());
            unit.setLanguageLevel(sense.getLanguageLevel());
            List<String> examples = sense.getExamples();
            unit.setExamples(examples.subList(0, Math.min(examples.size(), 3)));
        }
        return unit;
    }

    private EnglishPronunciations filter(EnglishPronunciations pr, List<EnglishVariant> variants,
                                         List<WordDefComponent> components) {
        EnglishPronunciations pronunciations = new EnglishPronunciations();
        if (variants.contains(EnglishVariant.BRITISH)) {
            pronunciations.setUk(filter(pr.getUk(), components));
        }
        if (variants.contains(EnglishVariant.AMERICAN)) {
            pronunciations.setUs(filter(pr.getUs(), components));
        }
        return pronunciations;
    }

    private List<Pronunciation> filter(List<Pronunciation> pr, List<WordDefComponent> components) {
        return pr.stream().map(p -> {
           Pronunciation pronunciation = new Pronunciation();
           if (components.contains(WordDefComponent.AUDIO)) {
               pronunciation.setAudioUrl(p.getAudioUrl());
           }
           if (components.contains(WordDefComponent.TRANSCRIPTION)) {
               pronunciation.setTranscription(p.getTranscription());
           }
           if (pronunciation.getAudioUrl() == null && pronunciation.getTranscription() == null) {
               return null;
           } else {
               return pronunciation;
           }
        }).filter(Objects::nonNull).toList();
    }

}
