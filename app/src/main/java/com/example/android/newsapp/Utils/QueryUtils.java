package com.example.android.newsapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.android.newsapp.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.string.no;
import static com.example.android.newsapp.Utils.DateUtil.JSON_FORMAT;

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
        builder.appendQueryParameter("from-date", DateUtil.formatDate(DateUtil.URL_FORMAT, DateUtil.getXDaysBeforeToday(10)));// 10 days before today
        builder.appendQueryParameter("to-date", DateUtil.formatDate(DateUtil.URL_FORMAT, DateUtil.getTodayDate())); // today' date
        builder.appendQueryParameter("page-size", String.valueOf(DefaultParameter.DEFAULT_PAGE_SIZE));
        builder.appendQueryParameter("page", String.valueOf(DefaultParameter.DEFAULT_PAGE));
        builder.appendQueryParameter("show-tags", DefaultParameter.DEFAULT_TAGS);

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
                Log.e(LOG_TAG, "error createURL(): can't create URL");
            }
        }
       // Log.i(LOG_TAG, "url: "+ url);
        return url;
    }

    /**
     * download JSON response
     */

    public static String downloadJsonResponse(URL url) throws IOException {
        String response = "";
        if (url == null) {
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
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                response = getResponseFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "error: response code = " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "error downloadJsonResponse(): can't make connection");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    /**
     * build json response string
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String getResponseFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                response.append(line);
                line = reader.readLine();
            }
        }

        return response.toString();
    }

    /**
     * extract news fron json response
     */
    public static ArrayList<News> extractNews(String jsonResponse) {
        ArrayList<News> news = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);

            JSONObject response = root.getJSONObject("response");


            JSONArray results = response.getJSONArray("results");


            for (int i = 0; i < results.length(); i++) {
                JSONObject newInfo = (JSONObject) results.get(i);



                String section = extractString(newInfo, "sectionName");



                Date publishedDate = DateUtil.getDate(JSON_FORMAT, extractString(newInfo, "webPublicationDate"));

                String webUrl = extractString(newInfo, "webUrl");



                JSONArray tags = newInfo.getJSONArray("tags");



                String contributor = null;
                if(tags.length()==1) {
                    JSONObject contributorTag = (JSONObject) tags.get(0);



                     contributor = extractString(contributorTag, "webTitle");

                }else {
                     contributor = "";
                    Log.i(LOG_TAG, "no contributor " );
                }
                JSONObject fields = newInfo.getJSONObject("fields");



                String headline = extractString(fields, "headline");



                String trailText = extractString(fields, "trailText");



                String thumbnailUrl = extractString(fields, "thumbnail");


                Bitmap thumbnail = null;
                if (thumbnailUrl != null) {
                    thumbnail = makeBitmap(thumbnailUrl);
                }

                if (thumbnail != null) {
                    news.add(new News(headline, section, publishedDate, trailText, webUrl, contributor, thumbnail));
                } else {
                    news.add(new News(headline, section, publishedDate, trailText, webUrl, contributor));
                }

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "error extractNews(): parsing problem");
        }
        return news;
    }

    private static Bitmap makeBitmap(String thumbnailUrl) {
        Bitmap thumbnail = null;

        if (thumbnailUrl != null) {
            InputStream in = null;

            try {
                in = createURL(thumbnailUrl).openStream();

            } catch (IOException e) {
               Log.e(LOG_TAG, "error makeBitmap(): can't open stream");
            }

            if (in != null) {
                thumbnail = BitmapFactory.decodeStream(in);
            }
        }
        return thumbnail;
    }

    private static String extractString(JSONObject newInfo, String stringName) {
        String str = null;

        try {
            str = newInfo.getString(stringName);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "error: extractString(), can't extract string: " + stringName);
        }

        if (str != null) {
            return str;
        } else {
            return "";
        }
    }

    public static ArrayList<News> fetchNewsData(String urlString){
        ArrayList<News> news = new ArrayList<>();
        URL url = createURL(urlString);

        String response = "";
        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "error fetchNewsData(), can't downloadJsonResponse");
        }

        if(!response.equals("")){
             news = extractNews(response);
        }

        return news;

    }
}
