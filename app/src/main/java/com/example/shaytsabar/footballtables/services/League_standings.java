package com.example.shaytsabar.footballtables.services;


import com.example.shaytsabar.footballtables.model.TeamLeagueStandings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Shay Tsabar on 08/08/2017.
 */

public class League_standings {

    //League codes in the url
    private final static int PLCODE = 445;
    private final static int CHAMPIONSHIPCODE = 446;
    private final static int EREDVISECODE = 449;
    private final static int LIGUE1CODE = 450;
    private final static int LIGUE2CODE = 451;
    private final static int BUNDESLIGACODE = 452;
    private final static int SECBUNDESLIGACODE = 453;
    private final static int SPANISHCODE = 455;
    private final static int SERIAACODE = 456;
    private final static int SERIABCODE = 459;
    private final static int PORTUGESECODE = 457;
    private final static int BRAZILCODE = 444;
    private static String leagueCode = "";
    private static String querystr = "";
    private static URL url = null;
    private static TeamLeagueStandings[] teams;

    public static URL GetBrazilQuery() {
        // Returns the full URL of the search query combined with the PL code.
        leagueCode = Integer.toString(BRAZILCODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetPLQuery() {
        // Returns the full URL of the search query combined with the PL code.
        leagueCode = Integer.toString(PLCODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetChampionshipQuery() {
        // Returns the full URL of the search query combined with the championship code.
        leagueCode = Integer.toString(CHAMPIONSHIPCODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetEredviseQuery() {
        // Returns the full URL of the search query combined with the eredvise code.
        leagueCode = Integer.toString(EREDVISECODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetLigue1Query() {
        // Returns the full URL of the search query combined with the ligue1 code.
        leagueCode = Integer.toString(LIGUE1CODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetLigue2Query() {
        // Returns the full URL of the search query combined with the ligue2 code.
        leagueCode = Integer.toString(LIGUE2CODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetBundesligaQuery() {
        // Returns the full URL of the search query combined with the bundesliga code.
        leagueCode = Integer.toString(BUNDESLIGACODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetSecBundesligaQuery() {
        // Returns the full URL of the search query combined with the second bundesliga code.
        leagueCode = Integer.toString(SECBUNDESLIGACODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetSpanishQuery() {
        // Returns the full URL of the search query combined with the Spanish league code.
        leagueCode = Integer.toString(SPANISHCODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetSeriaAQuery() {
        // Returns the full URL of the search query combined with the seria A code.
        leagueCode = Integer.toString(SERIAACODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetSeriaBQuery() {
        // Returns the full URL of the search query combined with the seria A code.
        leagueCode = Integer.toString(SERIABCODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }

    public static URL GetPortugeseQuery() {
        // Returns the full URL of the search query combined with the Portugese league code.
        leagueCode = Integer.toString(PORTUGESECODE);
        querystr = "competitions/" + leagueCode + "/leagueTable";
        url = Data.BuildUrl(querystr);
        return url;
    }


    public static TeamLeagueStandings[] LeagueStandingsArray(URL url, boolean isOnLandscape) throws IOException, JSONException {
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

            JSONObject object = teamsJson.getJSONObject((i));
            TeamLeagueStandings team = new TeamLeagueStandings();
            team.setPlace(Integer.toString(i+1));
            team.setTeamName(object.getString("teamName"));
            team.setCurGames(Integer.toString(object.getInt("playedGames")));
            team.setWins(Integer.toString(object.getInt("wins")));
            team.setDraws(Integer.toString(object.getInt("draws")));
            team.setLosses(Integer.toString(object.getInt("losses")));
            team.setGoalDifference(Integer.toString(object.getInt("goalDifference")));
            team.setPoints(Integer.toString(object.getInt("points")));
            team.setImgString(object.getString("crestURI"));
            String teamm = team.getTeamName();
            teamm = FixGoddamnAPIErrors(teamm, isOnLandscape);
            team.setTeamName(teamm);
            teams[i] = team;

        }

        return teams;
    }


    private static String FixGoddamnAPIErrors(String teamm, boolean isOnLandscape) {
        boolean rowdown = false;
        if (teamm.startsWith("1. "))
            teamm = teamm.replace("1. ", "");
        if (!teamm.toLowerCase().contains("AFC".toLowerCase())) {
            if (teamm.toLowerCase().contains("FC".toLowerCase()))
                teamm = teamm.replace("FC", "");
        }
        if (teamm.endsWith(" "))
            teamm = StringUtils.strip(teamm);
        if (!isOnLandscape) {
            if (teamm.length() > 12) {
                for (int j = 12; j < teamm.length(); j++) {
                    char c = teamm.charAt(j);
                    if (c == ' ') {
                        teamm = teamm.substring(0, j) + "\n" + teamm.substring(j + 1);
                        rowdown = true;
                        break;
                    }
                }
            }

            if (!rowdown) {
                if (teamm.length() >= 16) {
                    for (int j = 0; j < 12; j++) {
                        char c = teamm.charAt(j);
                        if (c == ' ') {
                            teamm = teamm.substring(0, j) + "\n" + teamm.substring(j + 1);
                            break;
                        }
                    }
                }

            }


        }
            return teamm;

    }
}