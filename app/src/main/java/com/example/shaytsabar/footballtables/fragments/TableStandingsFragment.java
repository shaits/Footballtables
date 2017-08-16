    package com.example.shaytsabar.footballtables.fragments;

    import android.content.Context;
    import android.graphics.Rect;
    import android.net.Uri;
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
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        TeamAdapter adapter;

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

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
        public static TableStandingsFragment newInstance(String param1, String param2) {
            TableStandingsFragment fragment = new TableStandingsFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        public TeamLeagueStandings [] GetBundesligaTeams() throws IOException, JSONException {
            URL urlBL= League_standings.GetBundesligaQuery();
            TeamLeagueStandings[] teams=League_standings.LeagueStandingsArray(urlBL);
            return  teams;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.fragment_recyclerview, container, false);


            try {
                adapter= new TeamAdapter(GetBundesligaTeams());
                RecyclerView recyclerView =  (RecyclerView) v.findViewById(R.id.recyler_teams);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(30));
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);


            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Error","We were unable to get the data from the server. Please try again later.");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error","We were unable to get the data from the server. Please try again later.");
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
    }

 /*
    public class DownloadTask extends AsyncTask<URL, Void, TeamLeagueStandings[]> {


        @Override
        protected TeamLeagueStandings[] doInBackground(URL... urls) {
            try {
                return League_standings.LeagueStandingsArray(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(TeamLeagueStandings[] teamLeagueStandingses) {

          adapter=new TeamAdapter(teamLeagueStandingses);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

        }


    }
    */
