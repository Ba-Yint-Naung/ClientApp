package com.bayintnaung.clientapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    View myView;
    EditText search;
    static Context context;
    static TextView txtallmovie,txtallseries;
    static RecyclerView allMovie,allSeries;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_search, container, false);
        GoogleAds googleAds=new GoogleAds();
        googleAds.loadAdVerticalAds(myView,getContext(),getActivity());
        initUI();
        final FirebaseConnect firebaseConnect=new FirebaseConnect(context,getFragmentManager());
        firebaseConnect.getAllMoviesForSearchFragment();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(search.getText().toString().trim().equals("")){
                        firebaseConnect.getAllMoviesForSearchFragment();
                }else {
                        firebaseConnect.getAllMoviesForSearchFragmentByQuery(search.getText().toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return myView;
    }
    public void initUI(){
        search=myView.findViewById(R.id.search);
        context=getContext();
        txtallmovie=myView.findViewById(R.id.txtmovie);
        txtallseries=myView.findViewById(R.id.txtseries);
        allMovie=myView.findViewById(R.id.allmovies);
        allSeries=myView.findViewById(R.id.allseries);

    }
}
