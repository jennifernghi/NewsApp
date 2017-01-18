package com.example.android.newsapp.Utils;

import android.net.Uri;

/**
 * Created by jennifernghinguyen on 1/17/17.
 */

public final class QueryUtils {
    private QueryUtils() {

    }

    public static String buildURI(String urlBase, String section) {
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
}
