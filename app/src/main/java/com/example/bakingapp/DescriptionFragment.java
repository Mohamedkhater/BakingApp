package com.example.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class DescriptionFragment extends Fragment {
    private String description;
    private SimpleExoPlayerView mPlayerView;
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

        if (getActivity().getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE
                && getActivity().findViewById(R.id.tablet_linear_layout)==null ){
             description_tv.setVisibility(View.GONE);
        }



        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DESCRIPTION_TEXT,this.description);
    }
}
