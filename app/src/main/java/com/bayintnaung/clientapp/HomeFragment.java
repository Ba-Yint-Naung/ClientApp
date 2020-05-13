package com.bayintnaung.clientapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    Handler handler=new Handler();
    RewardedVideoAd rewardedVideoAd;
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(rewardedVideoAd.isLoaded()){
                rewardedVideoAd.show();
            }
        }
    };

    public static CarouselView carouselView;
    public static ArrayList<String> sampleImages=new ArrayList<String>();
    View myView;
    static Context context;
    static TextView txtallmovie,txtallseries,txtallcategory;
    static RecyclerView allMovie,allSeries,allCategory;
    static ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(context)
                    .load(sampleImages.get(position))
                    .into(imageView);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_home, container, false);
        GoogleAds googleAds=new GoogleAds();
        rewardedVideoAd=googleAds.loadRewardedVideoAds(getContext());
        googleAds.loadThreeVerticalAds(myView,getContext(),getActivity());
        handler.postDelayed(runnable,10000);
        carouselView =myView.findViewById(R.id.carouselView);
        allMovie=myView.findViewById(R.id.allmovies);
        allSeries=myView.findViewById(R.id.allseries);
        allCategory=myView.findViewById(R.id.allcategory);
        txtallcategory=myView.findViewById(R.id.txtcategory);
        txtallmovie=myView.findViewById(R.id.txtmovie);
        txtallseries=myView.findViewById(R.id.txtseries);

        SeriesFragment.activity=getActivity();
        MovieFragment.activity=getActivity();
        context=getContext();
        //FirebaseConnect firebaseConnect=new FirebaseConnect();
        //firebaseConnect.showSlide();
        HomeFragment.carouselView.setPageCount(sampleImages.size());
        HomeFragment.carouselView.setImageListener(imageListener);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference ref=db.collection("setting") ;
        FirebaseConnect fConnect=new FirebaseConnect(getContext(),getFragmentManager());
        fConnect.showSlide();
        fConnect.getAllCategory();
        fConnect.getAllMovies();
        fConnect.getAllSeries();
        return myView;
    }

}
