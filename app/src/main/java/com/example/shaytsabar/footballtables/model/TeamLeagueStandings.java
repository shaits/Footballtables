package com.example.shaytsabar.footballtables.model;

import android.graphics.Bitmap;

/**
 * Created by Shay Tsabar on 08/08/2017.
 */

public class TeamLeagueStandings {
    private String place;
    private String teamName;
    private String curGames;
    private String wins;
    private String draws;
    private String losses;
    private String goalDifference;
    private String points;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public TeamLeagueStandings() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCurGames() {
        return curGames;
    }

    public void setCurGames(String curGames) {
        this.curGames = curGames;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
        this.goalDifference = goalDifference;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
