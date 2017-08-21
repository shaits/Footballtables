    package com.example.shaytsabar.footballtables.fragments;

    import android.content.Context;
    import android.graphics.Rect;
    import android.net.Uri;
    import android.os.AsyncTask;
    import android.os.Bundle;
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
    import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;
    import com.example.shaytsabar.footballtables.services.Data;
    import com.example.shaytsabar.footballtables.services.League_standings;
    import com.example.shaytsabar.footballtables.viewholder.TeamAdapter;

    import org.json.JSONException;

    import java.io.IOException;
    import java.net.URL;

    public class TableStandingsFragment extends Fragment {
        private static final String LEAGUETOLAUNCH = "league";
        private ProgressBar progressBar;
        private String league;
        private View v;
        private OnFragmentInteractionListener mListener;

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
            // Gets the argument of the fragment. This will tell us which league table to show
            //using the method "GeturlTeamsByArg", which gives the URL, and then using AsyncTask to get
            //the actual data, with the given url.
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
                case ("Football League Championship"):
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
                case ("Primeira Liga"):
                    url = League_standings.GetPortugeseQuery();
                    break;
            }
            return  url;
        }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
               // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                //StrictMode.setThreadPolicy(policy);
                v= inflater.inflate(R.layout.fragment_recyclerview, container, false);
                progressBar = v.findViewById(R.id.progressBar);
                DownloadTask downloadTask=new DownloadTask();
                try {
                    downloadTask.execute(GeturlTeamsByArg());
                }
                catch (Exception e){
                    Log.d("error",e.toString());
                }




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

        //@Override
        /*public void onSaveInstanceState(Bundle savedInstanceState) {
            savedInstanceState.putString(LEAGUETOLAUNCH,league);
        }
        */
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
               progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected TeamLeagueStandings[] doInBackground(URL... params) {
                URL searchUrl = params[0];
                TeamLeagueStandings[] results = null;
                try {
                    results = League_standings.LeagueStandingsArray(searchUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return results;
            }

            @Override
            protected void onPostExecute(TeamLeagueStandings[] results) {
                progressBar.setVisibility(View.INVISIBLE);
                TextView errortxtview= v.findViewById(R.id.error_txtview);
                Button tryagainbtn=v.findViewById(R.id.tryagain_btn);
                if(results==null) {
                    errortxtview.setVisibility(View.VISIBLE);
                    tryagainbtn.setVisibility(View.VISIBLE);
                    tryagainbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DownloadTask downloadTask1=new DownloadTask();
                            downloadTask1.execute(GeturlTeamsByArg());
                        }
                    });
                }
                else {
                    if(errortxtview.getVisibility()==View.VISIBLE){
                        errortxtview.setVisibility(View.GONE);
                        tryagainbtn.setVisibility(View.GONE);
                        Toast toast = Toast.makeText(getContext(), getResources().
                                getString(R.string.nevergiveup) , Toast.LENGTH_SHORT);
                        toast.show();
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
