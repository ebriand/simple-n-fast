package simple.n.fast.generators;

import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class DateParsetCachesGenerator {

    public static void main(final String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        final List<Long> timesForFormatter = Lists.newArrayList();
        int i = 0;
        for (; i < 100; i++) {
            final int year = 1970 + i;
            final DateTime date = DateTime.parse(year + "-01-01");
            final long dateInMillis = date.getMillis();
            timesForFormatter.add(dateInMillis);
        }
        System.out.println("For formatter : Years in millis from 1970 to " + ((1970 + i) - 1) + " { " + Joiner.on("L, ").join(timesForFormatter) + "L }");

        final List<Boolean> leapYearsForFormatter = Lists.newArrayList();
        int j = 0;
        for (; j < 100; j++) {
            final int year = 1970 + j;
            leapYearsForFormatter.add(((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0)));
        }
        System.out.println("For formatter : Leap years from 1970 to " + ((1970 + i) - 1) + " { " + Joiner.on(", ").join(leapYearsForFormatter) + " }");

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        final List<Long> times = Lists.newArrayList();
        int k = 0;
        for (; k < 100; k++) {
            final int year;
            if (k < 70) {
                year = 2000 + k;
            } else {
                year = 1900 + k;
            }
            final DateTime date = DateTime.parse(year + "-01-01");
            final long dateInMillis = date.getMillis();
            times.add(dateInMillis);
        }
        System.out.println("For parser : Years in millis from 1970 to " + ((1970 + k) - 1) + " { " + Joiner.on("L, ").join(times) + "L }");

        final List<Boolean> leapYears = Lists.newArrayList();
        int l = 0;
        for (; l < 100; l++) {
            final int year;
            if (l < 70) {
                year = 2000 + l;
            } else {
                year = 1900 + l;
            }
            leapYears.add(((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0)));
        }
        System.out.println("For parser: Leap years from 1970 to " + ((1970 + k) - 1) + " { " + Joiner.on(", ").join(leapYears) + " }");

        final List<String> years = Lists.newArrayList();
        for (int year = 1970; year < 2070; year++) {
            final String yearString = Integer.toString(year);
            years.add(new StringBuilder("{'").append(yearString.charAt(0)).append("', '").append(yearString.charAt(1)).append("', '").append(yearString.charAt(2)).append("', '").append(yearString.charAt(3)).append("'}").toString());
        }
        System.out.println("For parser: year: {" + Joiner.on(", ").join(years) + "}");

        System.out.print("For parser: day and month: {");
        for (int month = 1; month <= 12; month++) {
            final List<String> dayAndMonth = Lists.newArrayList();
            int nbDays;
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    nbDays = 31;
                    break;
                case 2:
                    nbDays = 28;
                    break;
                default:
                    nbDays = 30;
                    break;
            }
            final String monthString = month < 10 ? "0" + month : Integer.toString(month);
            for (int day = 1; day <= nbDays; day++) {
                final String dayString = day < 10 ? "0" + day : Integer.toString(day);
                dayAndMonth.add(new StringBuilder("{'").append(monthString.charAt(0)).append("', '").append(monthString.charAt(1)).append("', '").append(dayString.charAt(0)).append("', '").append(dayString.charAt(1)).append("'}").toString());
            }
            System.out.print(Joiner.on(", ").join(dayAndMonth));
            if (month != 12) {
                System.out.print(",");
                System.out.println();
            }
        }
        System.out.print("}");
        System.out.println();

        System.out.print("For parser: day and month in leap years: {");
        for (int month = 1; month <= 12; month++) {
            final List<String> dayAndMonth = Lists.newArrayList();
            int nbDays;
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    nbDays = 31;
                    break;
                case 2:
                    nbDays = 29;
                    break;
                default:
                    nbDays = 30;
                    break;
            }
            final String monthString = month < 10 ? "0" + month : Integer.toString(month);
            for (int day = 1; day <= nbDays; day++) {
                final String dayString = day < 10 ? "0" + day : Integer.toString(day);
                dayAndMonth.add(new StringBuilder("{'").append(monthString.charAt(0)).append("', '").append(monthString.charAt(1)).append("', '").append(dayString.charAt(0)).append("', '").append(dayString.charAt(1)).append("'}").toString());
            }
            System.out.print(Joiner.on(", ").join(dayAndMonth));
            if (month != 12) {
                System.out.print(",");
                System.out.println();
            }
        }
        System.out.print("}");
        System.out.println();

        final List<String> minutes = Lists.newArrayList();
        for (int blah = 0; blah < 60; blah++) {
            final String blahString = blah + "";
            minutes.add(blah < 10 ? "{'0', '" + blahString.charAt(0) + "'}" : "{'" + blahString.charAt(0) + "', '" + blahString.charAt(1) + "'}");
        }

        System.out.println(" { " + Joiner.on(", ").join(minutes) + " }");
    }
}
