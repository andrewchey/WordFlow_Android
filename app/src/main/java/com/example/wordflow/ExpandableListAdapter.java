package com.example.wordflow;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> defArrayList;
    private HashMap<String,ArrayList<String>> synHashMap;

    public ExpandableListAdapter(Context context, ArrayList<String> defArrayList, HashMap<String, ArrayList<String>> synHashMap) {
        this.context = context;
        this.defArrayList = defArrayList;
        this.synHashMap = synHashMap;
    }

    @Override
    public int getGroupCount() {
        return defArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return synHashMap.get(defArrayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return defArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return synHashMap.get(defArrayList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() { return false; }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group,parent,false);
        }
        TextView listHeader = convertView.findViewById(R.id.definitionTextView);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
        }
        TextView listChild = convertView.findViewById(R.id.synonymTextView);
        if (childText!=null) {listChild.setText(childText);}
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }


}
