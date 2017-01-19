package com.example.android.newsapp.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by jennifernghinguyen on 1/18/17.
 */

public final class DateUtil {

    final static String LOG_TAG = DateUtil.class.getSimpleName();
    public static Date getTodayDate(){
        return new Date();
    }

    public static String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        return format.format(date);
    }

    public static Date getXDaysBeforeToday(int x){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -x);
        return calendar.getTime();
    }

    public static Date getDateFromInputString(String date){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateInput=null;
        try {
            dateInput = input.parse(date);

        } catch (ParseException e) {
            Log.e(LOG_TAG, "error: getDateFromInputString(): can't parse input date");
        }

        if(dateInput !=null){
            return dateInput;
        }else {
            return null;
        }
    }

    public static Date toDate(String formattedDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");

        Date date = null;
        try {
            date = format.parse(formattedDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "error: toDate(): can't parse to date");
        }

        return date;
    }
}
