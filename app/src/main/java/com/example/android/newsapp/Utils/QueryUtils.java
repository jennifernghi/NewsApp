package com.example.android.newsapp.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.newsapp.models.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */

public final class QueryUtils {
    final static String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {

    }

    /**
     * build a complete url
     *
     * @param urlBase
     * @param section - sectionId
     * @return
     */
    public static String buildURI(String urlBase, String section) {
        if (urlBase == null && section == null) {
            return null;
        }
        Uri base = Uri.parse(urlBase);
        Uri.Builder builder = base.buildUpon();
        builder.path(section);
        builder.appendQueryParameter("api-key", DefaultParameter.DEFAULT_API_KEY);
        builder.appendQueryParameter("show-fields", DefaultParameter.DEFAULT_FIELDS);
        builder.appendQueryParameter("order-by", DefaultParameter.DEFAULT_ORDER_BY);
        builder.appendQueryParameter("from-date", DateUtil.formatDate(DateUtil.getXDaysBeforeToday(10)));// 10 days before today
        builder.appendQueryParameter("to-date", DateUtil.formatDate(DateUtil.getTodayDate())); // today' date

        return builder.toString().replace("%2C", ",");
    }

    /**
     * createURL
     */
    public static URL createURL(String urlString) {
        URL url = null;
        if (urlString == null) {
            return null;
        }

        if (url == null) {
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "error createURL(): can't create URL" );
            }
        }

        return url;
    }

    /**
     * download JSON response
     */

    public static String downloadJsonResponse(URL url) throws IOException {
        String response ="";
        if(url==null){
            return response;
        }

        //open HTTP connection
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode()==200){
                inputStream=httpURLConnection.getInputStream();
                response = getResponseFromStream(inputStream);
            }else {
                Log.e(LOG_TAG, "error: response code = " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
           Log.e(LOG_TAG, "error downloadJsonResponse(): can't make connection");
        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }

            if(inputStream!=null){
                inputStream.close();
            }
        }
        return response;
    }

    /**
     * build json response string
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String getResponseFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null){
                response.append(line);
                line=reader.readLine();
            }
        }

        return response.toString();
    }

    /**
     * extract news fron json response
     */
    public static List<News> extractNews(String response){
        List<News> news = new ArrayList<>();

        return news;
    }
}
