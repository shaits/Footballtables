    package com.example.shaytsabar.footballtables.activities;

    import android.net.Uri;
    import android.os.AsyncTask;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentTransaction;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import com.example.shaytsabar.footballtables.R;
    import com.example.shaytsabar.footballtables.fragments.TableStandingsFragment;
    import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;
    import com.example.shaytsabar.footballtables.services.League_standings;

    import org.json.JSONException;

    import java.io.IOException;
    import java.net.URL;

    public class MainActivity extends AppCompatActivity implements TableStandingsFragment.OnFragmentInteractionListener{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.fragment_con, TableStandingsFragment.newInstance("param1","faf"), TableStandingsFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
            /*if (manager.findFragmentById(R.id.fragment_con) == null) {
                Fragment fragment = new TableStandingsFragment();
                manager.beginTransaction().add(R.id.fragment_con, fragment).commit();

            }
            */
        }
        @Override
        public void onFragmentInteraction(Uri uri) {

        }
    }

