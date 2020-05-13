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
public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }

    static TextView txtallmovie;
    static RecyclerCoverFlow list;
    static RecyclerView allmovie;
    View myview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_movie, container, false);
        GoogleAds googleAds=new GoogleAds();
        googleAds.loadAdVerticalAds(myview,getContext(),getActivity());
        txtallmovie=myview.findViewById(R.id.txtallmovie);
        allmovie=myview.findViewById(R.id.allmovies);
        list=myview.findViewById(R.id.list);
        activity=getActivity();
        FirebaseConnect fConnect=new FirebaseConnect(getContext(),getFragmentManager());
        fConnect.getAllMoviesByMovieFragment();
        fConnect.getTopTenMovies();
        return myview;
    }

    public static Activity activity;
    public static void setHeader(String header){
        activity.setTitle(header);
    }
}
