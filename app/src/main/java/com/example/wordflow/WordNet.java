package com.example.wordflow;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.IndexWordSet;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordNet {

    // Search for the keyword and get all definitions and synonyms
    public static WordItem GetWordItem (String keyword) throws JWNLException {

        // Initialize WordNet
        Dictionary d = Dictionary.getDefaultResourceInstance();

        // Initialize
        ArrayList<String> defArrayList = new ArrayList<>();
        HashMap<String, ArrayList<String>> synHashMap = new HashMap<>();

        // Get senses (Synsets) by looking up all POS
        // NEED FIX IN CASE OF ERROR
        IndexWordSet indexWordSet = d.lookupAllIndexWords(keyword);

        // Get senses from each IndexWord and save to synsetArrayList
        ArrayList<Synset> synsetArrayList = new ArrayList<>();
        for (POS pos : indexWordSet.getValidPOSSet()) {

            // Get IndexWord of a particular POS
            IndexWord indexWord = indexWordSet.getIndexWord(pos);

            // Sort according to Use Count
            indexWord.sortSenses();

            // Cap upto three Senses per POS
            int cap;
            if (indexWord.getSenses().size() > 3) {cap = 3;} else {cap = indexWord.getSenses().size();}

            // Save all Senses to synsetArrayList
            for (int j = 0; j < cap; j++) {
                Synset synset = indexWordSet.getIndexWord(pos).getSenses().get(j);
                synsetArrayList.add(synset);
            }
        }

        // For each synset ArrayList element, add definition and its synonyms
        for (int i = 0; i < synsetArrayList.size(); i++) {
            Synset synset = synsetArrayList.get(i);
            List<Word> wordList = synset.getWords();

            // [[Create defArrayList]]
            String rawDefinition = "[" + synset.getPOS().name().toLowerCase() + "] " + synset.getGloss();
            String definition = rawDefinition.split(";")[0];
            defArrayList.add(definition);

            // temporary ArrayList for placing synonyms as ArrayList<String>
            ArrayList<String> synArrayList = new ArrayList<>();
            for (int j = 0; j < wordList.size(); j++) {

                // skip if the synonym is the word itself
                if (keyword.equals(wordList.get(j).getLemma())) {continue;}
                synArrayList.add(wordList.get(j).getLemma());

            }

            // [[Create synArrayList]]
            synHashMap.put(definition, synArrayList);

        }

        WordItem wordItem = new WordItem(keyword, defArrayList, synHashMap);

        return wordItem;

    }

}
