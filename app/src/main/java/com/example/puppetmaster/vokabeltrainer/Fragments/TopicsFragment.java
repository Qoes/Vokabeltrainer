package com.example.puppetmaster.vokabeltrainer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puppetmaster.vokabeltrainer.Activities.StartScreen;
import com.example.puppetmaster.vokabeltrainer.Adapter.TopicAdapter;
import com.example.puppetmaster.vokabeltrainer.Entities.Topic;
import com.example.puppetmaster.vokabeltrainer.R;

import java.util.ArrayList;


public class TopicsFragment extends Fragment {
    ArrayList<Topic> topics = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        retrieveTopics();
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.recycler_topics);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        TopicAdapter ta = new TopicAdapter(topics);
        recList.setAdapter(ta);
        return view;
    }

    private void retrieveTopics() {
        topics =  ((StartScreen)this.getActivity()).getTopics();
    }
}
