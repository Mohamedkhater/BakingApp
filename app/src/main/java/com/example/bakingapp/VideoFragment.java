package com.example.bakingapp;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileFilter;


public class VideoFragment extends Fragment   {
    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView playerView;
    public static final String VIDEO_URL_TEXT="video-text";
    DefaultHttpDataSourceFactory dataSourceFactory;
    MediaSource mediaSource;
    ExtractorsFactory extractorsFactory;
    ImageView stepImage;
    private final String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String imagePath;
    private long videoPosition;
    public static final String SELECTED_POSITION="selected-position";

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    private String videoUrl;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        exoPlayer= ExoPlayerFactory.newSimpleInstance(context);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getActivity().findViewById(R.id.tablet_linear_layout)==null){
            videoUrl=getArguments().getString("video-url");
            imagePath=getArguments().getString("image-path");

        }

        View view = inflater.inflate(R.layout.fragment_video,container,false);
        playerView=(SimpleExoPlayerView) view.findViewById(R.id.exoplayer_view);
        stepImage =(ImageView)view.findViewById(R.id.step_image);
        if (imagePath!=null && (!imagePath.equals(""))){
            int i=0;
           while( i<okFileExtensions.length){
               if(imagePath.endsWith(okFileExtensions[i])){
                   Picasso.with(getContext()).load(imagePath).into(stepImage);
                   break;

               }
               i++;
               stepImage.setImageResource(R.drawable.photo);


           }
        }
        else{
            stepImage.setImageResource(R.drawable.photo);
        }

        if (savedInstanceState!=null){
            videoUrl=savedInstanceState.getString(VIDEO_URL_TEXT);
            videoPosition = savedInstanceState.getLong(SELECTED_POSITION, C.TIME_UNSET);

        }
        if (videoUrl.equals("") || videoUrl==null)
        {
            playerView.setVisibility(View.GONE);

            return view;
        }


        Uri videoUri=Uri.parse(videoUrl);
         dataSourceFactory= new DefaultHttpDataSourceFactory("exoplayer-video");
         extractorsFactory= new DefaultExtractorsFactory();
         mediaSource= new ExtractorMediaSource(videoUri,dataSourceFactory,extractorsFactory,null,null);
        playerView.setPlayer(exoPlayer);
        exoPlayer.prepare(mediaSource);

        exoPlayer.setPlayWhenReady(true);
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);

        if (getActivity().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE&& getActivity().findViewById(R.id.tablet_linear_layout)==null) {
           LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    playerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = params.MATCH_PARENT;
            playerView.setLayoutParams(params);
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            exoPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPosition = exoPlayer.getCurrentPosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!videoUrl.equals("")){
            exoPlayer.prepare(mediaSource);
            if (videoPosition != C.TIME_UNSET)
                exoPlayer.seekTo(videoPosition);

            exoPlayer.setPlayWhenReady(true);
        }




    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VIDEO_URL_TEXT,videoUrl);
        outState.putLong(SELECTED_POSITION,videoPosition);
    }

    @Override
    public void onStop() {
        super.onStop();
        exoPlayer.stop(false);
    }


}

