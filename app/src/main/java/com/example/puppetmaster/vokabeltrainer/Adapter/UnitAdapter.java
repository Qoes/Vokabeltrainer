package com.example.puppetmaster.vokabeltrainer.Adapter;

/**
 * Created by Benedikt on 08.02.17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.puppetmaster.vokabeltrainer.DatabaseCommunication.MyDatabase;
import com.example.puppetmaster.vokabeltrainer.R;
import com.example.puppetmaster.vokabeltrainer.SpacedRepititionSystem.Vocab;
import com.example.puppetmaster.vokabeltrainer.Unit;
import com.example.puppetmaster.vokabeltrainer.UnitsActivity;

import java.util.ArrayList;
import java.util.List;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {
    private Context context;
    private List<Unit> unitList;

    public UnitAdapter(ArrayList<Unit> unitList) {
        this.unitList = unitList;
    }


    @Override
    public int getItemCount() {
        return unitList.size();
    }

    @Override
    public void onBindViewHolder(UnitViewHolder topicViewHolder, int i) {
        Unit unit = unitList.get(i);
        final int selectedUnit = unit.getId();
        topicViewHolder.vNumberOfUnit.setText("Unit " + (i + 1) + " of " + unitList.size());
        topicViewHolder.vTitle.setText(unit.getTitle());

        MyDatabase db = new MyDatabase(context);
        ArrayList<Vocab> listOfVocabs = db.getVocabOfUnit(unit.getId());
        VocabAdapter vocabAdapter = new VocabAdapter(context, listOfVocabs);
        topicViewHolder.vList.setAdapter(vocabAdapter);

        topicViewHolder.vButton.setTag(selectedUnit);


        topicViewHolder.vCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UnitsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("SELECTED_UNIT", selectedUnit);
                intent.putExtras(mBundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.unit_item, viewGroup, false);
        context = viewGroup.getContext();

        return new UnitViewHolder(itemView);
    }



    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        protected CardView vCard;
        protected TextView vNumberOfUnit;
        protected TextView vTitle;
        protected ListView vList;
        protected Button vButton;

        public UnitViewHolder(View v) {
            super(v);
            vCard = (CardView) v.findViewById(R.id.card_unit);
            vNumberOfUnit =  (TextView) v.findViewById(R.id.tv_unit_number);
            vTitle = (TextView)  v.findViewById(R.id.tv_unit_title);
            vList = (ListView) v.findViewById(R.id.list_unit_vocabs);
            vButton = (Button) v.findViewById(R.id.btn_unit_learn);
        }
    }
}