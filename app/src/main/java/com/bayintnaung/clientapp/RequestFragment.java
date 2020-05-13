package com.bayintnaung.clientapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    public RequestFragment() {
        // Required empty public constructor
    }

    View myView;
    Button btnsave,btncancel;
    EditText edtname,edtimage;
    FirebaseFirestore db;
    CollectionReference ref;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_request, container, false);
        GoogleAds googleAds=new GoogleAds();
        rewardedVideoAd=googleAds.loadRewardedVideoAds(getContext());
        googleAds.loadAdVerticalAds(myView,getContext(),getActivity());
        handler.postDelayed(runnable,3000);
        edtimage=myView.findViewById(R.id.edtimage);
        edtname=myView.findViewById(R.id.edtname);
        btnsave=myView.findViewById(R.id.btnsave);
        btncancel=myView.findViewById(R.id.btncancel);
        db=FirebaseFirestore.getInstance();
        ref=db.collection(getString(R.string.request_ref));

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rewardedVideoAd.isLoaded()){
                    rewardedVideoAd.show();
                }
                if(!edtname.getText().toString().trim().equals("") || !edtimage.getText().toString().trim().equals("")){
                    RequestModel model=new RequestModel();
                    model.name=edtname.getText().toString().trim();
                    model.imagelink=edtimage.getText().toString().trim();
                    SimpleDateFormat format=new SimpleDateFormat("ddMMyyyyhhMMss");
                    model.createdAt=format.format(new Date());
                    ref.add(model);
                    edtimage.setText("");
                    edtname.setText("");
                    Toasty.success(getContext(),"Request Send Successfully!",Toasty.LENGTH_LONG).show();
                }else{
                    Toasty.error(getContext(),"Please Fill Data!",Toasty.LENGTH_LONG).show();
                }
            }
        });
        return myView;
    }
}
