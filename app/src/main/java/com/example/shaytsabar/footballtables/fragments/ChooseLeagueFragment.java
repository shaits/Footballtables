package com.example.shaytsabar.footballtables.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shaytsabar.footballtables.R;
import com.example.shaytsabar.footballtables.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseLeagueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseLeagueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseLeagueFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button plbtn;
    private Button championshipbtn;
    private Button bundesligabtn;
    private Button secbundesligabtn;
    private Button ligue1btn;
    private Button ligue2btn;
    private Button eredvisebtn;
    private Button spanishbtn;
    private Button seriaabtn;
    private Button seriabbtn;
    private Button portugesebtn;
    private Button brazilbtn;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChooseLeagueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseLeagueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseLeagueFragment newInstance(String param1, String param2) {
        ChooseLeagueFragment fragment = new ChooseLeagueFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chooseleague, container, false);
        plbtn = v.findViewById(R.id.premierleague_btn);
        championshipbtn = v.findViewById(R.id.championship_btn);
        bundesligabtn = v.findViewById(R.id.bundesliga_btn);
        secbundesligabtn = v.findViewById(R.id.bundesliga2_btn);
        ligue1btn = v.findViewById(R.id.ligue1_btn);
        ligue2btn = v.findViewById(R.id.ligue2_btn);
        eredvisebtn = v.findViewById(R.id.eredvise_btn);
        spanishbtn = v.findViewById(R.id.spanish_btn);
        seriaabtn = v.findViewById(R.id.seriaa_btn);
        seriabbtn = v.findViewById(R.id.seriab_btn);
        portugesebtn = v.findViewById(R.id.portugese_btn);
        brazilbtn = v.findViewById(R.id.brazil_btn);
        plbtn.setOnClickListener(this);
        championshipbtn.setOnClickListener(this);
        bundesligabtn.setOnClickListener(this);
        secbundesligabtn.setOnClickListener(this);
        ligue1btn.setOnClickListener(this);
        ligue2btn.setOnClickListener(this);
        eredvisebtn.setOnClickListener(this);
        spanishbtn.setOnClickListener(this);
        seriaabtn.setOnClickListener(this);
        seriabbtn.setOnClickListener(this);
        portugesebtn.setOnClickListener(this);
        brazilbtn.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {

        int id = view.getId();
        String arg = "";
        switch (id) {
            case (R.id.premierleague_btn):
                arg = getResources().getString(R.string.premier_league);
                break;
            case (R.id.championship_btn):
                arg = getResources().getString(R.string.championship);
                break;
            case (R.id.bundesliga_btn):
                arg = getResources().getString(R.string.bundesliga);
                break;
            case (R.id.bundesliga2_btn):
                arg = getResources().getString(R.string.secbundesliga);
                break;
            case (R.id.ligue1_btn):
                arg = getResources().getString(R.string.ligue1);
                break;
            case (R.id.ligue2_btn):
                arg = getResources().getString(R.string.ligue2);
                break;
            case (R.id.eredvise_btn):
                arg = getResources().getString(R.string.eredvise);
                break;
            case (R.id.spanish_btn):
                arg = getResources().getString(R.string.spanishleague);
                break;
            case (R.id.seriaa_btn):
                arg = getResources().getString(R.string.seria_A);
                break;
            case (R.id.seriab_btn):
                arg = getResources().getString(R.string.seria_B);
                break;
            case (R.id.portugese_btn):
                arg = getResources().getString(R.string.portugeseleague);
                break;
            case (R.id.brazil_btn):
                arg = getResources().getString(R.string.brazilleague);
                break;
        }

        MainActivity ma = (MainActivity) getActivity();
        FragmentManager manager = ma.getSupportFragmentManager();
        manager.beginTransaction().
                replace(R.id.toolbar_con, TopBarLeaguesFragment.newInstance(arg)
                        , TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();
        manager.beginTransaction().
                replace(R.id.fragment_con,
                        TableStandingsFragment.newInstance(arg),
                        TableStandingsFragment.class.getSimpleName()).commitAllowingStateLoss();

    }
}
