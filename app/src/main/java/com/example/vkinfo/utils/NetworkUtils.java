package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL ="https://api.vk.com/";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION ="v";
    private static final String ACCESS_TOKEN = "access_token";
    public static URL generateURL(String userId){
        Uri builtUri = Uri.parse(VK_API_BASE_URL+VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID,userId)
                .appendQueryParameter(PARAM_VERSION,"5.21")
                .appendQueryParameter(ACCESS_TOKEN,
                        "f22ecfc6f22ecfc6f22ecfc6f0f25df556ff22ef22ecfc6ad31138401a07a65f4450caf")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
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
        }catch (UnknownHostException e){
            return null;
        }finally {
            urlConnection.disconnect();
        }

    }
}
