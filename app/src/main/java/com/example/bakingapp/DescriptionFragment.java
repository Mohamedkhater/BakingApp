package com.example.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {
    private String description;
    public static final String DESCRIPTION_TEXT="description-text";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DescriptionFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState!=null){
            description=savedInstanceState.getString(DESCRIPTION_TEXT);
        }

        View view=inflater.inflate(R.layout.fragment_instruction_description,container,false);
        TextView description_tv=view.findViewById(R.id.description_text_view);

        description_tv.setText(description);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DESCRIPTION_TEXT,this.description);
    }
}
