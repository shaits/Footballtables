    package com.example.shaytsabar.footballtables.activities;

    import android.content.Context;
    import android.net.Uri;
    import android.support.v4.app.FragmentManager;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.Toolbar;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;

    import com.example.shaytsabar.footballtables.R;
    import com.example.shaytsabar.footballtables.fragments.TableStandingsFragment;
    import com.example.shaytsabar.footballtables.fragments.TopBarLeaguesFragment;

    public class MainActivity extends AppCompatActivity implements TableStandingsFragment.OnFragmentInteractionListener,
            TopBarLeaguesFragment.OnFragmentInteractionListener{

        public void PrintException(Exception e){
            Log.d("Error",e.toString());
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            try {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.my_toolbar,TopBarLeaguesFragment.newInstance("faf","dsg")
                        ,TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();
                manager.beginTransaction().replace(R.id.fragment_con,
                        TableStandingsFragment.newInstance("param1", "faf"),
                        TableStandingsFragment.class.getSimpleName())
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                PrintException(e);
            }

        }

        @Override
        public void onFragmentInteraction(Uri uri) {

        }
    }

