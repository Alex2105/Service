package com.kerimovalex.test.tools;


import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateConverter {
    public static String convertDate(DateTime dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa", Locale.CANADA);
        return simpleDateFormat.format(dateTime.toDate());
    }
}
