package com.example.shaytsabar.footballtables.model;

import android.graphics.Bitmap;

/**
 * Created by Shay Tsabar on 08/08/2017.
 */

public class TeamLeagueStandings {
    private int place;
    private String teamName;
    private int curGames;
    private int wins;
    private int draws;
    private int losses;
    private int goalDifference;
    private int points;
    private String imgString;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public TeamLeagueStandings() {
    }

    public TeamLeagueStandings(int place,String teamName, int curGames, int wins, int draws, int losses, int goalDifference, int points) {
        this.place=place;
        this.teamName = teamName;
        this.curGames = curGames;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalDifference = goalDifference;
        this.points = points;

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getCurGames() {
        return curGames;
    }

    public void setCurGames(int curGames) {
        this.curGames = curGames;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImgString() {
        return imgString;
    }

    public void setImgString(String imgString) {
        this.imgString = imgString;
    }
}
