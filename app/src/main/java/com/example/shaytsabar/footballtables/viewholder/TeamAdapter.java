package com.example.shaytsabar.footballtables.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shaytsabar.footballtables.R;
import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;

/**
 * Created by Shay Tsabar on 10/08/2017.
 */

        public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

            private TeamLeagueStandings[] teams;
            public TeamAdapter(TeamLeagueStandings[] teams){
                this.teams=teams;
            }

            @Override
            public TeamAdapter.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.trycardview, parent, false);

                return new TeamViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(TeamAdapter.TeamViewHolder holder, int position) {
                TeamLeagueStandings team = teams[position];
                holder.place.setText(Integer.toString(team.getPlace())+ ".");
                holder.teamname.setText(team.getTeamName());
                holder.matches.setText(Integer.toString(team.getCurGames()));
                holder.wins.setText(Integer.toString(team.getWins()));
                holder.draws.setText(Integer.toString(team.getDraws()));
                holder.losses.setText(Integer.toString(team.getLosses()));
                holder.gd.setText(Integer.toString(team.getGoalDifference()));
                holder.points.setText(Integer.toString(team.getPoints()));

            }

            @Override
            public int getItemCount() {
                return teams.length;
            }

        public static class TeamViewHolder extends RecyclerView.ViewHolder{
            protected TextView place;
            protected TextView teamname;
            protected TextView matches;
            protected TextView wins;
            protected TextView draws;
            protected TextView losses;
            protected TextView gd;
            protected TextView points;

            public TeamViewHolder(View itemView) {

                super(itemView);
                place=itemView.findViewById(R.id.position_txtview);
                teamname=itemView.findViewById(R.id.teamName_txtview);
                matches=itemView.findViewById(R.id.matches_txtview);
                wins=itemView.findViewById(R.id.wins_txtview);
                draws=itemView.findViewById(R.id.draws_txtview);
                losses=itemView.findViewById(R.id.losses_txtview);
                gd=itemView.findViewById(R.id.gd_txtview);
                points=itemView.findViewById(R.id.points_txtview);


            }

        }

        }