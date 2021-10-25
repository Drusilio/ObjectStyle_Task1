import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import static java.lang.String.valueOf;

class Main {

    public static String MostFreeTime(String[] strArr) throws ParseException {
        // String to Date
        List <String[]> stringList = new ArrayList<>(strArr.length);
        List <Date[]> dateList = new ArrayList<>(strArr.length);
        final String timeAmountSeparator = "-";
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("hh:mma");
        SimpleDateFormat format2 = new SimpleDateFormat();
        format2.applyPattern("hh:mm");

        for (String d : strArr) {
            stringList.add(d.split(timeAmountSeparator));
        }

        for (int i = 0; i < stringList.size(); i++) {
            dateList.add(new Date[2]);
            for (int p = 0; p < 2; p++){
                try {
                    dateList.get(i)[p] = format.parse(stringList.get(i)[p]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        // sort Date
        Date sortingDate1;
        Date sortingDate2;
        List <Date[]> sortedDateList = new ArrayList<>(dateList);
        int count = 0;
            for (int i = 1; i < sortedDateList.size(); i++) {
                sortingDate1 = dateList.get(i - 1)[0];
                sortingDate2 = dateList.get(i)[0];
                if (sortingDate1.compareTo(sortingDate2) == 1) {
                    sortedDateList.set(i - 1, dateList.get(i));
                    sortedDateList.set(i, dateList.get(i - 1));
                }
            }

        // List of free time
        List <Long> freeTimeLongList = new ArrayList<>(strArr.length);
        long dateToMs1 = 0;
        long dateToMs2 = 0;
        long dateDifLong = 0;

        for (int i = 1; i < sortedDateList.size(); i++) {
            dateToMs1 = sortedDateList.get(i-1)[1].getTime();
            dateToMs2 = sortedDateList.get(i)[0].getTime();
            dateDifLong = dateToMs2 - dateToMs1;
            freeTimeLongList.add(dateDifLong);
        }

        // max free time period
        int hours = 0;
        int minutes = 0;
        String resultDateString = "";
        long maxFreeTimeLong = Collections.max(freeTimeLongList);

        hours = (int) (maxFreeTimeLong / (60 * 60 * 1000));
        minutes = (int) ((maxFreeTimeLong - hours * (60 * 60 * 1000)) / (60 * 1000));

        if (valueOf(hours).length() < 2) {
            resultDateString = "0" + hours;
        }
        else {
            resultDateString = resultDateString + hours;
        }
        resultDateString = resultDateString + ":";
        if (valueOf(minutes).length() < 2) {
            resultDateString = "0" + minutes;
        }
        else {
            resultDateString = resultDateString + minutes;
        }

        return resultDateString;
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(MostFreeTime(s.nextLine()));
    }

}