package com.example.wordflow;

import java.util.ArrayList;

public class FlowItem {

    private int placementId;
    private String def;
    private ArrayList<String> synArraylist;

    public FlowItem(int placementId, String def, ArrayList<String> synArraylist) {
        this.placementId = placementId;
        this.def = def;
        this.synArraylist = synArraylist;
    }

    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public ArrayList<String> getSynArraylist() {
        return synArraylist;
    }

    public void setSynArraylist(ArrayList<String> synArraylist) {
        this.synArraylist = synArraylist;
    }
}
