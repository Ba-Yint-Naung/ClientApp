package com.bayintnaung.clientapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import recycler.coverflow.RecyclerCoverFlow;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {

    public SeriesFragment() {
        // Required empty public constructor
    }

    static TextView txtallseries;
    static RecyclerCoverFlow list;
    static RecyclerView allseries;
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView=  inflater.inflate(R.layout.fragment_series, container, false);
        txtallseries=myView.findViewById(R.id.txtallmovie);
        allseries=myView.findViewById(R.id.allmovies);
        list=myView.findViewById(R.id.list);
        activity=getActivity();
        FirebaseConnect fConnect=new FirebaseConnect(getContext(),getFragmentManager());
        GoogleAds googleAds=new GoogleAds();
        googleAds.loadAdVerticalAds(myView,getContext(),getActivity());
        fConnect.getAllSeriesBySeriesFragment();
        fConnect.getTopTenSeries();
        return myView;
    }
    public static Activity activity;
    public static void setHeader(String header){
        activity.setTitle(header);
    }
}
