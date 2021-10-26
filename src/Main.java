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

            for (int i = 1; i < dateList.size(); i++) {
                sortingDate1 = dateList.get(i - 1)[0];
                sortingDate2 = dateList.get(i)[0];
                if (sortingDate1.after(sortingDate2)) {
                    sortedDateList.set(i, dateList.get(i - 1));
                    sortedDateList.set(i - 1, dateList.get(i));
                }
            }

            for (Date[] d : dateList) {
                System.out.println(Arrays.asList(d));
            }
            System.out.println("");
            for (Date[] d : sortedDateList) {
                System.out.println(Arrays.asList(d));
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
            resultDateString = resultDateString + "0" + hours;
        }

        else {
            resultDateString = resultDateString + hours;
        }
        resultDateString = resultDateString + ":";
        if (valueOf(minutes).length() < 2) {
            resultDateString = resultDateString + "0" + minutes;
        }
        else {
            resultDateString = resultDateString + minutes;
        }

        return resultDateString;
    }

    public static void main (String[] args) {
        // keep this function call here
/*
        Scanner s = new Scanner(System.in);
        System.out.print(MostFreeTime(s.nextLine()));
*/


        String[] array = new String[] {"12:15PM-02:00PM","09:00AM-10:00AM","10:30AM-12:00PM"};
        try {
            System.out.print(MostFreeTime(array));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        /*String[] strArr;
        List <String> strList = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String templ;

        while (true) {
            templ = s.nextLine();
            if (templ == null)
            {
                break;
            }
            strList.add(templ);
        }
        strArr = strList.toArray(new String[10]);
        System.out.print(MostFreeTime(strArr));*/
    }
}