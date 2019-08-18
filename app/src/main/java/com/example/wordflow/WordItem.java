package com.example.wordflow;

import java.util.ArrayList;
import java.util.HashMap;

public class WordItem {

    String word;
    ArrayList<String> defArrayList;
    HashMap<String, ArrayList<String>> synHashMap;

    public WordItem(String word, ArrayList<String> defArrayList, HashMap<String, ArrayList<String>> synHashMap) {
        this.word = word;
        this.defArrayList = defArrayList;
        this.synHashMap = synHashMap;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getDefArrayList() {
        return defArrayList;
    }

    public void setDefArrayList(ArrayList<String> defArrayList) {
        this.defArrayList = defArrayList;
    }

    public HashMap<String, ArrayList<String>> getSynHashMap() {
        return synHashMap;
    }

    public void setSynHashMap(HashMap<String, ArrayList<String>> synHashMap) {
        this.synHashMap = synHashMap;
    }
}
