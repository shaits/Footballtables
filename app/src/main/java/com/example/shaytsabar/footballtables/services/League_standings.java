package com.example.shaytsabar.footballtables.services;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.util.Log;


import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Shay Tsabar on 08/08/2017.
 */

public class League_standings {

    //League codes in the url
    private final static int PLCODE=445;
    private final static int CHAMPIONSHIPCODE=446;
    private final static int EREDVISECODE=449;
    private final static int LIGUE1CODE=450;
    private final static int LIGUE2CODE=451;
    private final static int BUNDESLIGACODE=452;
    private final static int SECBUNDESLIGACODE=453;
    private final static int SPANISHCODE=455;
    private final static int SERIAACODE=456;
    private final static int PORTUGESECODE=457;
    private static String nationCode="";
    private static String querystr="";
    private static URL url= null;
    private static TeamLeagueStandings[] teams;


    public static URL GetPLQuery() {
        // Returns the full URL of the search query combined with the PL code.
        nationCode = Integer.toString(PLCODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetChampionshipQuery() {
        // Returns the full URL of the search query combined with the championship code.
        nationCode = Integer.toString(CHAMPIONSHIPCODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetEredviseQuery() {
        // Returns the full URL of the search query combined with the eredvise code.
        nationCode = Integer.toString(EREDVISECODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetLigue1Query() {
        // Returns the full URL of the search query combined with the ligue1 code.
        nationCode = Integer.toString(LIGUE1CODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetLigue2Query() {
        // Returns the full URL of the search query combined with the ligue2 code.
        nationCode = Integer.toString(LIGUE2CODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetBundesligaQuery() {
        // Returns the full URL of the search query combined with the bundesliga code.
        nationCode = Integer.toString(BUNDESLIGACODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetSecBundesligaQuery() {
        // Returns the full URL of the search query combined with the second bundesliga code.
        nationCode = Integer.toString(SECBUNDESLIGACODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetSpanishQuery(){
        // Returns the full URL of the search query combined with the Spanish league code.
        nationCode= Integer.toString(SPANISHCODE);
        querystr="competitions/" + nationCode + "/leagueTable";
        url= Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetSeriaAQuery(){
        // Returns the full URL of the search query combined with the seria A code.
        nationCode= Integer.toString(SERIAACODE);
        querystr="competitions/" + nationCode + "/leagueTable";
        url= Data.BuildUrl(querystr);
        return url;
    }
    public static URL GetPortugeseQuery() {
        // Returns the full URL of the search query combined with the Portugese league code.
        nationCode = Integer.toString(PORTUGESECODE);
        querystr = "competitions/" + nationCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }


    public static TeamLeagueStandings[] LeagueStandingsArray(URL url) throws IOException, JSONException {
        //Gets the full url of the requested league table, should look like: "http://api.football-data.org/v1/competitions/445/leagueTable"
        //Processing the JSON data from the API, then returns an array which contains the teams of the league
        //that was requested, in THE ORDER OF THE TABLE!!!
        String results = Data.GetResponseFromHttpUrl(url);
        JSONObject resultsJSON = new JSONObject(results);
        JSONArray teamsJson = resultsJSON.getJSONArray("standing");
        int length = teamsJson.length();
        teams = new TeamLeagueStandings[length];
        // name,games,wins,draws,losses,GD,points, pic
        for (int i = 0; i < length; i++) {

            JSONObject object = teamsJson.getJSONObject(i);
            TeamLeagueStandings team = new TeamLeagueStandings();
            team.setPlace(object.getInt("position"));
            team.setTeamName(object.getString("teamName"));
            team.setCurGames(object.getInt("playedGames"));
            team.setWins(object.getInt("wins"));
            team.setDraws(object.getInt("draws"));
            team.setLosses(object.getInt("losses"));
            team.setGoalDifference(object.getInt("goalDifference"));
            team.setPoints(object.getInt("points"));
            team.setImgString(object.getString("crestURI"));
            if(team.getTeamName().toLowerCase().contains("FC".toLowerCase())){
                team.setTeamName(team.getTeamName().replace("FC",""));
            }

            teams[i] = team;
        }
        return teams;
    }




}
