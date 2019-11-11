package com.example.pezyandroid.louise.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anubi on 26/10/2560.
 */

public class DateUtils {


    public static String getCurrentDateTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date); //2016/11/16 12:08:43
    }

    public static String convertTimeStampToOtherFormat(String pattern, String date){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = dtf.parseDateTime(date);
        return dt.toString(pattern);
    }
}
