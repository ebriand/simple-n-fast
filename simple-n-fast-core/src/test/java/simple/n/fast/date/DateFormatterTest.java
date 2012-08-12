package simple.n.fast.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simple.n.fast.date.DateFormatter;

public class DateFormatterTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateFormatterTest.class);

	private final static String DATE_PATTERN = "yyyy-MM-dd";

	private final static String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	private TimeZone defaultTimeZone;

	private DateTimeZone defaultDateTimeZone;

	@Before
	public void init() {
		defaultTimeZone = TimeZone.getDefault();
		defaultDateTimeZone = DateTimeZone.getDefault();
	}

	@Test
	public void formatOriginDate() throws ParseException {
		formatDateTest("1970-01-01");
	}

	@Test
	public void parseNowDateTest() throws ParseException {
		formatDateTest(DateTime.now().toString(DATE_PATTERN));
	}

	@Test
	public void parseOriginDateTimeTest() throws ParseException {
		formatDateTimeTest("1970-01-01T00:00:00");
	}

	@Test
	public void parseNowDateTimeTest() throws ParseException {
		formatDateTimeTest(DateTime.now().toString(DATE_TIME_PATTERN));
	}

	private void formatDateTest(final String pDate) throws ParseException {
		formatDateTest(pDate, false);
	}

	private void formatDateTimeTest(final String pDateTime) throws ParseException {
		formatDateTest(pDateTime, true);
	}

	private void formatDateTest(final String pDate, final boolean pDateTime) throws ParseException {
		parseDate(pDate, pDateTime);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.forID("UTC"));
		DateFormatter.refreshDefaultTimeZone();
		parseDate(pDate, pDateTime);
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
		DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
		DateFormatter.refreshDefaultTimeZone();
		parseDate(pDate, pDateTime);
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		DateFormatter.refreshDefaultTimeZone();
		parseDate(pDate, pDateTime);
	}

	private void parseDate(final String pDate, final boolean pDateTime) throws ParseException {
		final Date date = DateTime.parse(pDate).toDate();
		final String dateFormattedFromSimpleDateFormat;
		final String jodaTimeFormatted;
		final String dateFormatted;
		if (pDateTime) {
			dateFormattedFromSimpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
			jodaTimeFormatted = DateTimeFormat.forPattern(DATE_TIME_PATTERN).print(new DateTime(date));
			dateFormatted = DateFormatter.formatISODateTime(date);
		} else {
			dateFormattedFromSimpleDateFormat = new SimpleDateFormat(DATE_PATTERN).format(date);
			jodaTimeFormatted = DateTimeFormat.forPattern(DATE_PATTERN).print(new DateTime(date));
			dateFormatted = DateFormatter.formatISODate(date);
		}

		logDebug(dateFormattedFromSimpleDateFormat, jodaTimeFormatted, dateFormatted);

		Assert.assertEquals("Difference with JDK formatted date.", dateFormattedFromSimpleDateFormat, dateFormatted);
		Assert.assertEquals("Difference with joda-time formatted date.", jodaTimeFormatted, dateFormatted);
	}

	private void logDebug(final String dateParsedFromSimpleDateFormat, final String jodaTimeDate, final String dateFormatted) {
		final String message = ": {}";
		LOGGER.debug("jdk" + message, dateParsedFromSimpleDateFormat);
		LOGGER.debug("joda-time" + message, jodaTimeDate);
		LOGGER.debug("simplenfast" + message, dateFormatted);
	}

	@After
	public void resetTimeZone() {
		TimeZone.setDefault(defaultTimeZone);
		DateTimeZone.setDefault(defaultDateTimeZone);
		DateFormatter.refreshDefaultTimeZone();
	}
}
