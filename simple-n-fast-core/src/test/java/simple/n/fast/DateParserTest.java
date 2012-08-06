package simple.n.fast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParserTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateParserTest.class);

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
	public void parseOriginDateTest() throws ParseException {
		parseDateTest("1970-01-01");
	}

	@Test
	public void parseNowDateTest() throws ParseException {
		parseDateTest(DateTime.now().toString(DATE_PATTERN));
	}

	@Test
	public void parseOriginDateTimeTest() throws ParseException {
		parseDateTimeTest("1970-01-01T00:00:00");
	}

	@Test
	public void parseNowDateTimeTest() throws ParseException {
		parseDateTimeTest(DateTime.now().toString(DATE_TIME_PATTERN));
	}

	private void parseDateTest(final String pDateString) throws ParseException {
		parseDateTest(pDateString, DATE_PATTERN);
	}

	private void parseDateTimeTest(final String pDateTimeString) throws ParseException {
		parseDateTest(pDateTimeString, DATE_TIME_PATTERN);
	}

	private void parseDateTest(final String pDateString, final String pPattern) throws ParseException {
		parseDate(pDateString, pPattern);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.forID("UTC"));
		DateParser.refreshDefaultTimeZone();
		parseDate(pDateString, pPattern);
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
		DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
		DateParser.refreshDefaultTimeZone();
		parseDate(pDateString, pPattern);
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		DateParser.refreshDefaultTimeZone();
		parseDate(pDateString, pPattern);
	}

	private void parseDate(final String pDateString, final String pPattern) throws ParseException {
		final Date dateParsedFromSimpleDateFormat = new SimpleDateFormat(pPattern).parse(pDateString);
		final DateTime jodaTimeDate = DateTime.parse(pDateString);

		Date dateParsed = null;
		if (DATE_TIME_PATTERN.equals(pPattern)) {
			dateParsed = DateParser.parseISODateTime(pDateString);
		} else {
			dateParsed = DateParser.parseISODate(pDateString);
		}

		logDebug(dateParsedFromSimpleDateFormat, jodaTimeDate, dateParsed);

		Assert.assertEquals("Difference with JDK parsed date.", dateParsedFromSimpleDateFormat, dateParsed);
		Assert.assertEquals("Difference with joda-time parsed date.", jodaTimeDate.toDate(), dateParsed);
	}

	private void logDebug(final Date dateParsedFromSimpleDateFormat, final DateTime jodaTimeDate, final Date dateParsed) {
		final String message = ": {}, offset: {}, millis: {}";
		LOGGER.debug("jdk" + message, new Object[] { dateParsedFromSimpleDateFormat, -TimeZone.getDefault().getOffset(dateParsedFromSimpleDateFormat.getTime()), dateParsedFromSimpleDateFormat.getTime() });
		LOGGER.debug("joda-time" + message, new Object[] { jodaTimeDate.toDate(), -jodaTimeDate.getZone().getOffset(jodaTimeDate), jodaTimeDate.getMillis() });
		LOGGER.debug("simplenfast" + message, new Object[] { dateParsed, -TimeZone.getDefault().getOffset(dateParsed.getTime()), dateParsed.getTime() });
	}

	@After
	public void resetTimeZone() {
		TimeZone.setDefault(defaultTimeZone);
		DateTimeZone.setDefault(defaultDateTimeZone);
		DateParser.refreshDefaultTimeZone();
	}
}
