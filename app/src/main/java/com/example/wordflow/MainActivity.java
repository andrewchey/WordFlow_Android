package com.example.wordflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.wordflow.WordNet.GetWordItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener{

    public static void main(String[] args) {

    }

    @Override
    public void onClick(View v) {
        Log.d("Test", "onClick");
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.d("Test", "onChildClick");
        Toast.makeText(this, "child clicked", Toast.LENGTH_SHORT);

        String synClicked = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();

        // Get wordItem from WordNet
        try {
            WordItem wordItem = GetWordItem(synClicked);
            String newLemma = ProcessLemma(synClicked); // process the clicked synonym into new lemma
            UpdateResult(wordItem.getDefArrayList(), wordItem.getSynHashMap()); // Update the ListView
            UpdateEditText(newLemma); // Update the EditText
        } catch (JWNLException e) {
            Log.d("JWNLException", e.toString());
        }

        return false;
    }

    public void OnSearchClicked (View view) throws JWNLException {

        // Convert editText input to String
        String lemma = editText.getText().toString();

        // Get wordItem from WordNet
        WordItem wordItem = GetWordItem(lemma);

        // Update the ListView
        UpdateResult(wordItem.getDefArrayList(), wordItem.getSynHashMap());

    }

    public String ProcessLemma (String lemma) {
        if (lemma.contains(" ")) {
            lemma=lemma.split(" ")[0];
        }

        return lemma;
    }

    public void UpdateResult(ArrayList<String> defArrayList, HashMap<String,ArrayList<String>> synHashMap) {

        expandableListAdapter = new ExpandableListAdapter(this,defArrayList,synHashMap);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(this);

        // expand by default
        for (int i = 0; i < expandableListAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

    }

    public void UpdateEditText(String newLemma) {
        editText.setText(newLemma);
    }

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        expandableListView = findViewById(R.id.expandable_listview);


    }

}
