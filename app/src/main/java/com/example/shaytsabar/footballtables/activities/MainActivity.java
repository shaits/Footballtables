    package com.example.shaytsabar.footballtables.activities;

    import android.content.res.Configuration;
    import android.net.Uri;
    import android.support.constraint.ConstraintLayout;
    import android.support.constraint.ConstraintSet;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.FrameLayout;

    import com.example.shaytsabar.footballtables.R;
    import com.example.shaytsabar.footballtables.fragments.ChooseLeagueFragment;
    import com.example.shaytsabar.footballtables.fragments.TableStandingsFragment;
    import com.example.shaytsabar.footballtables.fragments.TopBarLeaguesFragment;
    import com.google.android.gms.ads.AdListener;
    import com.google.android.gms.ads.AdRequest;
    import com.google.android.gms.ads.AdSize;
    import com.google.android.gms.ads.AdView;
    import com.google.android.gms.ads.InterstitialAd;
    import com.google.android.gms.ads.MobileAds;

    import static android.support.constraint.ConstraintSet.LEFT;
    import static android.support.constraint.ConstraintSet.RIGHT;
    //import com.google.android.gms.ads.MobileAds;

    public class MainActivity extends AppCompatActivity implements TableStandingsFragment.OnFragmentInteractionListener,
            TopBarLeaguesFragment.OnFragmentInteractionListener,ChooseLeagueFragment.OnFragmentInteractionListener{
        FragmentManager manager;
        InterstitialAd mInterstitialAd;
        AdView mAdView;
        private static boolean isMainFragment=true;
        private static final String LEAGUETOLAUNCH = "league";
        private static final String TIMESTILLNEXTBIGAD="timesnext";
        private static final String APPCODE="ca-app-pub-3046903934801639~4850321210";
        private int timesTillNextAd=3;
        public int screenWidth;
        public ConstraintLayout constraintLayout;
        public ConstraintSet constraintSet;
        private boolean istwofragments;
        FrameLayout fragmentcon;
        FrameLayout choosecon;
        Fragment fragmentTables;



        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            MobileAds.initialize(this, APPCODE);
            ShowLittleAd();
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3046903934801639/8187425836");
            //DELETE THE "ADDTESTDEVICE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
            mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("5FE9A80EF1EDD66EC5FD4A2FDFEDDE7C").
                    addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    ShowLittleAd();
                }
            });
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed ()
                {
                    //DELETE THE "ADDTESTDEVICE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
                    AdRequest adRequest = new AdRequest.Builder().addTestDevice("5FE9A80EF1EDD66EC5FD4A2FDFEDDE7C")
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
                    mInterstitialAd.loadAd(adRequest);
                }
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    onAdClosed();
                }
            });

            if(savedInstanceState!=null)
                timesTillNextAd=savedInstanceState.getInt(TIMESTILLNEXTBIGAD);
            manager = getSupportFragmentManager();
            Configuration configuration = getApplicationContext().getResources().getConfiguration();
            screenWidth= configuration.smallestScreenWidthDp;
            doineedtwofragments();
            if(istwofragments){
                constraintLayout= (ConstraintLayout)
                        findViewById(R.id.rootconstraint);
                constraintSet=new ConstraintSet();
                if(!isMainFragment) {
                    ShowSecFragment(savedInstanceState.getString(LEAGUETOLAUNCH));
                    manager.beginTransaction().
                            replace(R.id.choose_con, ChooseLeagueFragment.newInstance(true)
                                    , ChooseLeagueFragment.class.getSimpleName()).commitAllowingStateLoss();
                }
                else
                ShowMainFragment();

            }
            else {
                // Happens if the width is less than 590, isn't relevant to this question.
                if (!isMainFragment) {
                    String league = savedInstanceState.getString(LEAGUETOLAUNCH);
                    ShowSecFragment(league);
                    isMainFragment = true;
                } else {

                    ShowMainFragment();
                }

            }

        }
        public void ShowLittleAd() {
            try {
                mAdView = (AdView) findViewById(R.id.adView);

                //DELETE THE "ADDTESTDEVICE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
                AdRequest adRequest = new AdRequest.Builder().addTestDevice("5FE9A80EF1EDD66EC5FD4A2FDFEDDE7C").
                        addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
                mAdView.loadAd(adRequest);
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }

        public void ShowMainFragment() {
            if (istwofragments) {
                if(fragmentTables!=null && fragmentTables instanceof TableStandingsFragment)
                    manager.beginTransaction().remove(fragmentTables).commit();

                fragmentTables= manager.findFragmentById(R.id.fragment_con);
                fragmentcon=(FrameLayout)findViewById(R.id.fragment_con);
                choosecon=(FrameLayout)findViewById(R.id.choose_con);
                //Change the constraints of the fragments, so the chooseleague fragment will be all over the screen.
                constraintSet.clone(constraintLayout);
                 constraintSet.connect(fragmentcon.getId(),LEFT,constraintSet.PARENT_ID,RIGHT,0);
                constraintSet.connect(choosecon.getId(),RIGHT,
                        constraintSet.PARENT_ID, RIGHT,0);
                constraintSet.applyTo(constraintLayout);

               //Starting to match the fragments to their places.
                manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right).
                        replace(R.id.choose_con, ChooseLeagueFragment.newInstance(false)
                                , ChooseLeagueFragment.class.getSimpleName()).commitAllowingStateLoss();

                manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                        .replace(R.id.toolbar_con, TopBarLeaguesFragment.newInstance
                                        ("Sushi")
                                , TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();

            } else {
                manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right).
                        replace(R.id.fragment_con, ChooseLeagueFragment.newInstance(false)
                                , ChooseLeagueFragment.class.getSimpleName()).commitAllowingStateLoss();

                manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                        .replace(R.id.toolbar_con, TopBarLeaguesFragment.newInstance
                                        ("Sushi")
                                , TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();
            }
        }
        public void ShowSecFragment(String league){
            manager.beginTransaction().
                    replace(R.id.fragment_con, TableStandingsFragment.newInstance(league)
                    , TableStandingsFragment.class.getSimpleName()).commitAllowingStateLoss();

            manager.beginTransaction().
                    replace(R.id.toolbar_con, TopBarLeaguesFragment.newInstance(league)
                    , TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();

        }

        @Override
        public void onBackPressed() {
            fragmentTables= manager.findFragmentById(R.id.fragment_con);
            if (timesTillNextAd > 0)
                timesTillNextAd--;
            if (mInterstitialAd.isLoaded() && timesTillNextAd == 0) {
                mInterstitialAd.show();
                timesTillNextAd = 3;
            }

            if (screenWidth < 590)
                if (fragmentTables instanceof TableStandingsFragment) {
                    ShowMainFragment();
                    isMainFragment = true;
                }
                else
                super.onBackPressed();

            else {
                if (fragmentTables != null) {
                    if (fragmentTables instanceof TableStandingsFragment)
                        ShowMainFragment();
                    isMainFragment = true;
                }

                else
                    super.onBackPressed();
            }
        }
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            outState.putInt(TIMESTILLNEXTBIGAD,timesTillNextAd);
            fragmentTables = manager.findFragmentById(R.id.fragment_con);
            if (fragmentTables != null) {
                if (fragmentTables instanceof TableStandingsFragment) {
                    isMainFragment=false;
                    outState.putString(LEAGUETOLAUNCH,fragmentTables.getArguments().getString(LEAGUETOLAUNCH));

                }
            }
        }


        public void onAdClosed ()
        {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3046903934801639/8187425836");
            mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("5FE9A80EF1EDD66EC5FD4A2FDFEDDE7C").build());
        }
        public void doineedtwofragments(){
            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE&&
                    screenWidth>=590)
                istwofragments=true;
            else
                istwofragments=false;
        }


        @Override
        public void onFragmentInteraction(Uri uri) {

        }

    }



