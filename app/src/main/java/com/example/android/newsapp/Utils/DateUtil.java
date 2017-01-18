package com.example.android.newsapp.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by jennifernghinguyen on 1/18/17.
 */

public final class DateUtil {

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
}
