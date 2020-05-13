package com.bayintnaung.clientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    static NavigationView navigationView;
    static Toolbar toolbar;
    static String currentFrag,preFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");
        currentFrag="Home";
        drawer=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navView);
        View headerView=navigationView.getHeaderView(0);
        TextView txtVersion=headerView.findViewById(R.id.versionName);
        txtVersion.setText("Version "+ BuildConfig.VERSION_NAME);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home_menu){
                    setFragment(new HomeFragment());
                    setTitle("Home");
                    currentFrag="Home";
                }else if(item.getItemId()==R.id.movie_menu){
                    setFragment(new MovieFragment());
                    setTitle("Movies");
                    currentFrag="Movies";
                }else if(item.getItemId()==R.id.series_menu){
                    setFragment(new SeriesFragment());
                    setTitle("Series");
                    currentFrag="Series";
                }else if(item.getItemId()==R.id.request_menu){
                    setFragment(new RequestFragment());
                    setTitle("Request");
                    currentFrag="Request";
                }else if(item.getItemId()==R.id.about_menu){
                    setFragment(new AboutFragment());
                    setTitle("About");
                    currentFrag="About";
                }else if(item.getItemId()==R.id.search_menu){
                    currentFrag="Search";
                    setFragment(new SearchFragment());
                    setTitle("Search");
                }
                else{
                    Intent shareIntent= new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                    //shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.plarium.solitaire");
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                    currentFrag="Share";
                }
                if(VideoDetailFragment.player!=null){
                        VideoDetailFragment.player.stop();
                }
                drawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.openDrawer,R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setFragment(new HomeFragment());

    }
    public void setFragment(Fragment f){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.navContent,f);
        ft.commit();
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onBackPressed() {
        if(currentFrag.equals("Home")){
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.closeDrawer(Gravity.LEFT);
                }else{
                    final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Confirmation!");
                    alert.setMessage("Are You Sure To Exit!");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            finishAffinity();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
        }
        else if(currentFrag.equals("Movies") || currentFrag.equals("Series") || currentFrag.equals("Request")
                || currentFrag.equals("About") || currentFrag.equals("Search")){
            setFragment(new HomeFragment());
            currentFrag="Home";
            setTitle(currentFrag);
        }else if(currentFrag.equals("Series_Detail")){
                if(preFrag.equals("Home")){
                        setFragment(new HomeFragment());
                        currentFrag="Home";
                        setTitle(currentFrag);
                }else if(preFrag.equals("Series")){
                        setFragment(new SeriesFragment());
                        currentFrag="Series";
                        setTitle(currentFrag);
                }else if(preFrag.equals("Movies_Detail")){
                        setFragment(new SeriesFragment());
                        currentFrag="Series";
                        setTitle(currentFrag);
                        preFrag="Series_Detail";
                        VideoDetailFragment.player.stop();
                }else if(preFrag.equals("Search")){
                        setFragment(new SearchFragment());
                        preFrag="Home";
                        currentFrag="Search";
                        setTitle(currentFrag);
                }

        }else if (currentFrag.equals("Movies_Detail")){
            if(preFrag.equals("Home")){
                setFragment(new HomeFragment());
                currentFrag="Home";
                setTitle(currentFrag);
            }
            else if(preFrag.equals("Movies")){
                setFragment(new MovieFragment());
                currentFrag="Movies";
                setTitle(currentFrag);
            }else if(preFrag.equals("Series_Detail")){
                SeriesDetailFragment detfrag=new SeriesDetailFragment();
                setFragment(detfrag);
                currentFrag="Series_Detail";
                preFrag="Movies_Detail";
                setTitle(SeriesDetailFragment.model.seriesName);
            }else if(preFrag.equals("Search")){
                setFragment(new SearchFragment());
                currentFrag="Search";
                setTitle(currentFrag);
                preFrag="Movies_Detail";
            }
            else{
                setFragment(new SeriesFragment());
                currentFrag="Series";
                setTitle(currentFrag);
            }
            VideoDetailFragment.player.stop();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
            MainActivity.toolbar.setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }
        if(VideoDetailFragment.player!=null){
            VideoDetailFragment.player.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(VideoDetailFragment.player!=null){
            VideoDetailFragment.player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(VideoDetailFragment.player!=null){
            VideoDetailFragment.player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(VideoDetailFragment.player!=null){
            VideoDetailFragment.player.setPlayWhenReady(true);
        }
    }
}
