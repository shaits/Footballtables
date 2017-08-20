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

    import com.example.shaytsabar.footballtables.R;
    import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;
    import com.example.shaytsabar.footballtables.services.Data;
    import com.example.shaytsabar.footballtables.services.League_standings;
    import com.example.shaytsabar.footballtables.viewholder.TeamAdapter;

    import org.json.JSONException;

    import java.io.IOException;
    import java.net.URL;


    /**
     * A simple {@link Fragment} subclass.
     * Activities that contain this fragment must implement the
     * {@link BlankFragment.OnFragmentInteractionListener} interface
     * to handle interaction events.
     * Use the {@link BlankFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class TableStandingsFragment extends Fragment {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String LEAGUETOLAUNCH = "league";


        private TeamAdapter adapter;

        // TODO: Rename and change types of parameters
        private String league;


        private OnFragmentInteractionListener mListener;

        public TableStandingsFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
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



        public URL GeturlTeamsByArg() throws IOException, JSONException {
            /*
            Gets the correct table to the user, after he pressed on the button in the chooseleaguefragment.
            */
            URL url=null;
            if(league==null)
                return url;

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
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.fragment_recyclerview, container, false);
                try{
                adapter=new TeamAdapter(League_standings.LeagueStandingsArray(GeturlTeamsByArg()));
                RecyclerView recyclerView =  (RecyclerView) v.findViewById(R.id.recyler_teams);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(30));
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
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
        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         * <p>
         * See the Android Training lesson <a href=
         * "http://developer.android.com/training/basics/fragments/communicating.html"
         * >Communicating with Other Fragments</a> for more information.
         */
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }



    /*    public class DownloadTask extends AsyncTask<URL, Void, TeamLeagueStandings[]> {

            // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // mLoadingIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            protected TeamLeagueStandings[] doInBackground(URL... params) {
                URL searchUrl = params[0];
                TeamLeagueStandings[] results = null;
                try {
                    results= League_standings.LeagueStandingsArray(searchUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return results;
            }

            @Override
            protected void onPostExecute(TeamLeagueStandings[] results) {
                // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
               adapter=new TeamAdapter(results);
            }
        }
        */

    }
