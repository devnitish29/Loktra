package learning.nitish.github.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nitish Singh Rathore on 6/8/17.
 */

public class DateTimeHellperClass {

    public static int convertTOMillis(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1 = null;
        try {
            date1 = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        return (int) calendar.getTimeInMillis();
    }

//"EEE, d MMM yyyy HH:mm:ss Z"
    public static String parseDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat df2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        Date date1 = null;
        String date2 = null;
        try {
            date1 = df.parse(date);
            date2 = df2.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return date2;
    }
}
