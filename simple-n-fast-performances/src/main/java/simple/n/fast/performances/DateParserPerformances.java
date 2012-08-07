package simple.n.fast.performances;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simple.n.fast.DateParser;

public class DateParserPerformances {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateParserPerformances.class);

    private static Random random = new Random();

    public static void main(final String[] args) throws Exception {
        final int numberOfLoops = 10_000_000;

        final String[] randomDates = new String[numberOfLoops];
        for (int i = 0; i < numberOfLoops; i++) {
            randomDates[i] = randomDateString();
        }

        System.gc();
        System.gc();
        System.gc();
        LOGGER.info("Init complete.");

        final long startSimpleNFastFixDate = System.currentTimeMillis();
        for (int i = 0; i < numberOfLoops; i++) {
            DateParser.parseISODate("2012-07-14");
        }
        final long stopSimpleNFastFixDate = System.currentTimeMillis();
        LOGGER.info("simplenfast - fix date: " + (stopSimpleNFastFixDate - startSimpleNFastFixDate) + "ms, average: " + ((stopSimpleNFastFixDate - startSimpleNFastFixDate) / (numberOfLoops * 1D)));

        final long startSimpleNFastRandomDate = System.currentTimeMillis();
        for (final String randomDate : randomDates) {
            DateParser.parseISODateTime(randomDate);
        }
        final long stopSimpleNFastRandomDate = System.currentTimeMillis();
        LOGGER.info("simplenfast - random date: " + (stopSimpleNFastRandomDate - startSimpleNFastRandomDate) + "ms, average: " + ((stopSimpleNFastRandomDate - startSimpleNFastRandomDate) / (numberOfLoops * 1D)));

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final long startJDKFixDate = System.currentTimeMillis();
        for (int i = 0; i < numberOfLoops; i++) {
            simpleDateFormat.parse("2012-07-14");
        }
        final long stopJDKFixDate = System.currentTimeMillis();
        LOGGER.info("JDK - fix date: " + (stopJDKFixDate - startJDKFixDate) + "ms, average: " + ((stopJDKFixDate - startJDKFixDate) / (numberOfLoops * 1D)));

        final long startJDKRandomDate = System.currentTimeMillis();
        for (final String randomDate : randomDates) {
            simpleDateFormat.parse(randomDate);
        }
        final long stopJDKRandomDate = System.currentTimeMillis();
        LOGGER.info("JDK - random date: " + (stopJDKRandomDate - startJDKRandomDate) + "ms, average: " + ((stopJDKRandomDate - startJDKRandomDate) / (numberOfLoops * 1D)));

        final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        final long startJodaTimeFixDate = System.currentTimeMillis();
        for (int i = 0; i < numberOfLoops; i++) {
            dateFormatter.parseDateTime("2012-07-14").toDate();
        }
        final long stopJodaTimeFixDate = System.currentTimeMillis();
        LOGGER.info("joda-time - fix date: " + (stopJodaTimeFixDate - startJodaTimeFixDate) + "ms, average: " + ((stopJodaTimeFixDate - startJodaTimeFixDate) / (numberOfLoops * 1D)));

        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'kk:mm:ss");
        final long startJodaTimeRandomDate = System.currentTimeMillis();
        for (final String randomDate : randomDates) {
            dateTimeFormatter.parseDateTime(randomDate).toDate();
        }
        final long stopJodaTimeRandomDate = System.currentTimeMillis();
        LOGGER.info("joda-time - random date: " + (stopJodaTimeRandomDate - startJodaTimeRandomDate) + "ms, average: " + ((stopJodaTimeRandomDate - startJodaTimeRandomDate) / (numberOfLoops * 1D)));
    }

    private static String randomDateString() {
        final int year = random.nextInt(100) + 1970;
        final int month = random.nextInt(12) + 1;
        final String monthString = (month < 10) ? "0" + month : Integer.toString(month);
        int day = 0;
        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            day = random.nextInt(31) + 1;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            day = random.nextInt(30) + 1;
            break;
        case 2:
            if (((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0))) {
                day = random.nextInt(29) + 1;
            } else {
                day = random.nextInt(28) + 1;
            }
            break;
        }
        final String dayString = (day < 10) ? "0" + day : Integer.toString(day);
        final int hour = random.nextInt(20) + 4;
        final String hourString = (hour < 10) ? "0" + hour : Integer.toString(hour);
        final int minutes = random.nextInt(59) + 1;
        final String minutesString = (minutes < 10) ? "0" + minutes : Integer.toString(minutes);
        final int seconds = random.nextInt(59) + 1;
        final String secondsString = (seconds < 10) ? "0" + seconds : Integer.toString(seconds);
        return new StringBuilder().append(year).append("-").append(monthString).append("-").append(dayString).append("T").append(hourString).append(":").append(minutesString).append(":").append(secondsString).toString();
    }
}
