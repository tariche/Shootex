package com.funnymon.game;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by tarik on 3/16/17.
 */

public class InterAd {
    InterstitialAd interstitialAd;

    public InterAd(Context context) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestInterstitial();
        setListenr();
    }

    private void requestInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        interstitialAd.loadAd(adRequest);
    }

    private void setListenr() {
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestInterstitial();
            }
        });
    }

    public void showAd() {
        if (interstitialAd != null) {
            interstitialAd.show();
        }
    }

    public void destroy() {

    }
}
