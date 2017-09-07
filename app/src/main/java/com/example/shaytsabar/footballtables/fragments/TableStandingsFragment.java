    package com.example.shaytsabar.footballtables.fragments;

    import android.content.Context;
    import android.content.pm.ActivityInfo;
    import android.content.res.Configuration;
    import android.graphics.Rect;
    import android.net.Uri;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.os.Handler;
    import android.os.StrictMode;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ProgressBar;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.shaytsabar.footballtables.R;
    import com.example.shaytsabar.footballtables.activities.MainActivity;
    import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;
    import com.example.shaytsabar.footballtables.services.Data;
    import com.example.shaytsabar.footballtables.services.League_standings;
    import com.example.shaytsabar.footballtables.viewholder.TeamAdapter;

    import org.json.JSONException;

    import java.io.IOException;
    import java.net.URL;

    //import pl.droidsonroids.gif.GifTextView;

    public class TableStandingsFragment extends Fragment {
        private static final String LEAGUETOLAUNCH = "league";
     //   private GifTextView gifTextView;
        private String league;
        private View v;
        private OnFragmentInteractionListener mListener;
        private boolean isOnLandscape=false;
        boolean isLittle=false;
        boolean isBig=false;
        ProgressBar progressBar;

        public TableStandingsFragment() {
            // Required empty public constructor
        }


        public static TableStandingsFragment newInstance(String param1) {
            TableStandingsFragment fragment = new TableStandingsFragment();
            Bundle args = new Bundle();
            args.putString(LEAGUETOLAUNCH, param1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                league = getArguments().getString(LEAGUETOLAUNCH);
            }
        }



        public URL GeturlTeamsByArg() {

            //Returns the correct URL of the required league table, depends on the variable "league"

            URL url=null;
            if (league == "" || league == null)
                return null;
            switch (league) {
                case ("Premier League"):
                    url = League_standings.GetPLQuery();
                    break;
                case ("Championship"):
                    url = League_standings.GetChampionshipQuery();
                    break;
                case ("Eredvise"):
                    url = League_standings.GetEredviseQuery();
                    break;
                case ("Ligue 1"):
                    url = League_standings.GetLigue1Query();
                    break;
                case ("Ligue 2"):
                    url = League_standings.GetLigue2Query();
                    break;
                case ("Bundesliga"):
                    url = League_standings.GetBundesligaQuery();
                    break;
                case ("2. Bundesliga"):
                    url = League_standings.GetSecBundesligaQuery();
                    break;
                case ("Primera División"):
                    url = League_standings.GetSpanishQuery();
                    break;
                case ("Serie A"):
                    url = League_standings.GetSeriaAQuery();
                    break;
                case ("Serie B"):
                    url=League_standings.GetSeriaBQuery();
                    break;
                case ("Primeira Liga"):
                    url = League_standings.GetPortugeseQuery();
                    break;
                case ("Brasileirão"):
                    url=League_standings.GetBrazilQuery();
                    break;
            }
            return  url;
        }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
                    isOnLandscape=true;
                else
                    isOnLandscape=false;
                v= inflater.inflate(R.layout.fragment_recyclerview, container, false);
                Configuration configuration = getActivity().getApplicationContext()
                        .getResources().getConfiguration();
                int screenWidth= configuration.smallestScreenWidthDp;
                if(screenWidth<340)
                {
                    isLittle=true;
                }else
                    if(screenWidth>560)
                        isBig=true;


                TextView textView=v.findViewById(R.id.points_txtview);

                 Runnable mMyRunnable = new Runnable() {
                    @Override
                    public void run() {
                        DownloadTask downloadTask = new DownloadTask();
                        downloadTask.execute(GeturlTeamsByArg());
                    }
                };
                    progressBar=v.findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                    Handler myHandler = new Handler();
                    myHandler.postDelayed(mMyRunnable, 1000);

                return v;
            }


        // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }

        class VerticalSpaceItemDecorator extends RecyclerView.ItemDecoration {

            private final int spacer;

            public VerticalSpaceItemDecorator(int spacer) {
                this.spacer = spacer;
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = spacer;
            }
        }

        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }



        public class DownloadTask extends AsyncTask<URL, Void, TeamLeagueStandings[]> {

            // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected TeamLeagueStandings[] doInBackground(URL... params) {
                URL searchUrl = params[0];
                TeamLeagueStandings[] results = null;
                try {
                    results = League_standings.LeagueStandingsArray(searchUrl, isOnLandscape,isLittle,isBig);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.d("error", e.toString());
                }

                return results;
            }

            @Override
            protected void onPostExecute(TeamLeagueStandings[] results) {
                progressBar.setVisibility(View.GONE);
                TextView errortxtview = v.findViewById(R.id.error_txtview);
                Button tryagainbtn = v.findViewById(R.id.tryagain_btn);
                if (results == null) {
                    errortxtview.setVisibility(View.VISIBLE);
                    tryagainbtn.setVisibility(View.VISIBLE);
                    tryagainbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DownloadTask downloadTask1 = new DownloadTask();
                            downloadTask1.execute(GeturlTeamsByArg());
                        }
                    });
                } else {
                    if (errortxtview.getVisibility() == View.VISIBLE) {
                        errortxtview.setVisibility(View.GONE);
                        tryagainbtn.setVisibility(View.GONE);
                        Toast toast = Toast.makeText(getContext(), getResources().
                                getString(R.string.nevergiveup), Toast.LENGTH_SHORT);
                        toast.show();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.ShowLittleAd();

                    }
                        TeamAdapter adapter = new TeamAdapter(results);
                        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyler_teams);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(30));
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);


                }
            }
        }


    }
