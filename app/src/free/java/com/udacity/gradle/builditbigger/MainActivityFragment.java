package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import gt.com.jpvr.funnyui.JokeActivity;

import static gt.com.jpvr.funnyui.JokeActivity.ARG_JOKE;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsCallbacks {

    private LinearLayout mProgressLayout;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        MobileAds.initialize(requireContext(), getString(R.string.app_id));

        prepareBannerAd(root);
        prepareInterstitialAd();

        return root;
    }

    private void prepareInterstitialAd() {
        AdRequest.Builder requestBuilder = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

        mInterstitialAd = new InterstitialAd(requireContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(requestBuilder.build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                tellJoke();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("ADS", "Failed to load. Error code: " + errorCode);
            }
        });
    }

    private void prepareBannerAd(View root) {
        AdRequest bannerRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        AdView mAdView = root.findViewById(R.id.adView);

        mAdView.loadAd(bannerRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mProgressLayout = view.findViewById(R.id.progressLayout);

        Button btnTellJoke = view.findViewById(R.id.btnTellJoke);

        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    tellJoke();
                }
            }
        });
    }

    public void tellJoke() {
        if (mProgressLayout != null) {
            mProgressLayout.setVisibility(View.VISIBLE);
        }

        new EndpointsAsyncTask(requireContext())
                .setCallbacks(this)
                .execute();
    }

    @Override
    public void onEndpointTaskResult(String result) {
        if (mProgressLayout != null) {
            mProgressLayout.setVisibility(View.GONE);
        }

        Intent intent = new Intent(requireContext(), JokeActivity.class);
        intent.putExtra(ARG_JOKE, result);
        startActivity(intent);
    }

    @Override
    public void onEndpointTaskFailed() {
        if (mProgressLayout != null) {
            mProgressLayout.setVisibility(View.GONE);
        }

        Toast.makeText(requireContext(), R.string.no_joke, Toast.LENGTH_LONG).show();
    }
}
