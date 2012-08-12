package simple.n.fast.performances.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import simple.n.fast.date.DateParser;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class DateParsersBenchmark extends SimpleBenchmark {

	private static Random random = new Random();

	private static String DATE_PATTERN = "yyyy-MM-dd";

	private static String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(DATE_PATTERN);

	final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN);

	@Param("100000")
	private int size;

	private String[] randomDates;

	@Override
	protected void setUp() throws Exception {
		randomDates = new String[size];
		for (int i = 0; i < size; i++) {
			randomDates[i] = randomDateString();
		}
	}

	public static void main(final String[] args) throws Exception {
		Runner.main(DateParsersBenchmark.class, args);
	}

	public void timeSimpleNFastFixDate(final int reps) {
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				DateParser.parseISODate("2012-07-14");
			}
		}
	}

	public void timeSimpleNFastRandomDate(final int reps) {
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				DateParser.parseISODateTime(randomDate);
			}
		}
	}

	public void timeJDKFixDate(final int reps) throws ParseException {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				simpleDateFormat.parse("2012-07-14");
			}
		}
	}

	public void timeJDKRandomDate(final int reps) throws ParseException {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				simpleDateFormat.parse(randomDate);
			}
		}
	}

	public void timeJodaTimeFixDate(final int reps) throws ParseException {
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				dateFormatter.parseDateTime("2012-07-14");
			}
		}
	}

	public void timeJodaTimeRandomDate(final int reps) throws ParseException {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		for (int i = 0; i < reps; i++) {
			for (final String randomDate : randomDates) {
				dateTimeFormatter.parseDateTime(randomDate);
			}
		}
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
