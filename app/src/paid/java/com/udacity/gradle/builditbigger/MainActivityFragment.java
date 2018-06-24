package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import gt.com.jpvr.funnyui.JokeActivity;

import static gt.com.jpvr.funnyui.JokeActivity.ARG_JOKE;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsCallbacks {

    private LinearLayout progressLayout;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.progressLayout = view.findViewById(R.id.progressLayout);

        Button btnTellJoke = view.findViewById(R.id.btnTellJoke);

        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });
    }

    public void tellJoke() {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.VISIBLE);
        }

        new EndpointsAsyncTask(requireContext())
                .setCallbacks(this)
                .execute();
    }

    @Override
    public void onEndpointTaskResult(String result) {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.GONE);
        }

        Intent intent = new Intent(requireContext(), JokeActivity.class);
        intent.putExtra(ARG_JOKE, result);
        startActivity(intent);
    }

    @Override
    public void onEndpointTaskFailed() {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.GONE);
        }

        Intent intent = new Intent(requireContext(), JokeActivity.class);
        startActivity(intent);
    }
}
