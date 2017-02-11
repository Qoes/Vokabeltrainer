package com.example.puppetmaster.vokabeltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.puppetmaster.vokabeltrainer.Adapter.VocabAdapter;
import com.example.puppetmaster.vokabeltrainer.DatabaseCommunication.MyDatabase;
import com.example.puppetmaster.vokabeltrainer.SpacedRepititionSystem.Vocab;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllVocabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vocabs);

        MyDatabase db = new MyDatabase(this);
        ArrayList<Vocab> listOfAllVocab= db.getListOfAllVocab();
        VocabAdapter adapter = new VocabAdapter(this, listOfAllVocab);
        ListView listView = (ListView) findViewById(R.id.list_all_vocabs);
        listView.setAdapter(adapter);
    }
}