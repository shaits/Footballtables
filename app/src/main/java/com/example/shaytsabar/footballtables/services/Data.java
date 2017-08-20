package com.example.shaytsabar.footballtables.services;

import android.net.Uri;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Shay Tsabar on 08/08/2017.
 */

public class Data {
    private final static String URLFOOTBALL= "http://api.football-data.org/v1/";
    private final static String APIKEY="c68a1e2956304efd8c1445b63248046e ";
    public static URL BuildUrl(String searchQuery) {
        String finalURL= URLFOOTBALL+searchQuery;
        URL url=null;
        try {
            url = new URL(finalURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String GetResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("X-Auth-Token",APIKEY);
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }

}
