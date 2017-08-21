package com.example.shaytsabar.footballtables.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaytsabar.footballtables.R;

import java.util.zip.Inflater;

public class TopBarLeaguesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LEAGUETOLAUNCH = "league";
    private String league;



    private OnFragmentInteractionListener mListener;

    public TopBarLeaguesFragment() {
        // Required empty public constructor
    }



    public static TopBarLeaguesFragment newInstance(String param1) {
        TopBarLeaguesFragment fragment = new TopBarLeaguesFragment();
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
    public void SetCorrectTopBar(View v){
        TextView txtview=v.findViewById(R.id.leagueTextView);
        ImageView imageView=v.findViewById(R.id.flagImage);
        switch (league) {
            case ("Premier League"):
               txtview.setText(getResources().getString(R.string.premier_league));
                imageView.setImageResource(R.drawable.flagengland);
                break;
            case ("Football League Championship"):
                txtview.setText(getResources().getString(R.string.championship));
                imageView.setImageResource(R.drawable.flagengland);
                break;
            case ("Eredvise"):
                txtview.setText(getResources().getString(R.string.eredvise));
                imageView.setImageResource(R.drawable.flagnetherlands);
                break;
            case ("Ligue 1"):
                txtview.setText(getResources().getString(R.string.ligue1));
                imageView.setImageResource(R.drawable.flagfrance);
                break;
            case ("Ligue 2"):
                txtview.setText(getResources().getString(R.string.ligue2));
                imageView.setImageResource(R.drawable.flagfrance);
                break;
            case ("Bundesliga"):
                txtview.setText(getResources().getString(R.string.bundesliga));
                imageView.setImageResource(R.drawable.flaggermany);
                break;
            case ("2. Bundesliga"):
                txtview.setText(getResources().getString(R.string.secbundesliga));
                imageView.setImageResource(R.drawable.flaggermany);
                break;
            case ("Primera División"):
                txtview.setText(getResources().getString(R.string.spanishleague));
                imageView.setImageResource(R.drawable.flagspain);
                break;
            case ("Serie A"):
                txtview.setText(getResources().getString(R.string.seria_A));
                imageView.setImageResource(R.drawable.flagitaly);
                break;
            case ("Primeira Liga"):
                txtview.setText(getResources().getString(R.string.portugeseleague));
                imageView.setImageResource(R.drawable.flagportugal);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_topbarleagues,container,false);
        SetCorrectTopBar(v);
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
}
