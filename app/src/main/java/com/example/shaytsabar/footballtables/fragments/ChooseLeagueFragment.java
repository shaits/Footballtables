package com.example.shaytsabar.footballtables.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPropertyAnimatorListener;
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
    private final int premierLeagueid = R.id.premierleague_btn;
    private final int championshipid = R.id.championship_btn;
    private final int bundesligaid = R.id.bundesliga_btn;
    private final int secbundesligaid = R.id.bundesliga2_btn;
    private Button plbtn;
    private Button championshipbtn;
    private Button bundesligabtn;
    private Button secbundesligabtn;


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
        plbtn.setOnClickListener(this);
        championshipbtn.setOnClickListener(this);
        bundesligabtn.setOnClickListener(this);
        secbundesligabtn.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String arg = "";
        switch (id) {
            case (premierLeagueid):
                arg = getResources().getString(R.string.premier_league);
                break;
            case (championshipid):
                arg = getResources().getString(R.string.championship);
                break;
            case (bundesligaid):
                arg = getResources().getString(R.string.bundesliga);
                break;
            case (secbundesligaid):
                arg = getResources().getString(R.string.secbundesliga);
                break;
        }

        MainActivity ma = (MainActivity) getActivity();
        FragmentManager manager = ma.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.toolbar_con, TopBarLeaguesFragment.newInstance("faf", "dsg")
                , TopBarLeaguesFragment.class.getSimpleName()).commitAllowingStateLoss();
        manager.beginTransaction().replace(R.id.fragment_con,
                TableStandingsFragment.newInstance(arg),
                TableStandingsFragment.class.getSimpleName()).commitAllowingStateLoss();
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