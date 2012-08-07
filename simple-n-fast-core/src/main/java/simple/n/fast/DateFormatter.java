package simple.n.fast;

import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

	private static TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();

	static String[] years = { "1970-", "1971-", "1972-", "1973-", "1974-", "1975-", "1976-", "1977-", "1978-", "1979-", "1980-", "1981-", "1982-", "1983-", "1984-", "1985-", "1986-", "1987-", "1988-", "1989-", "1990-", "1991-", "1992-", "1993-", "1994-", "1995-", "1996-", "1997-", "1998-", "1999-", "2000-", "2001-", "2002-", "2003-", "2004-", "2005-", "2006-", "2007-", "2008-", "2009-", "2010-", "2011-", "2012-", "2013-", "2014-", "2015-", "2016-", "2017-", "2018-", "2019-", "2020-", "2021-", "2022-", "2023-", "2024-", "2025-", "2026-", "2027-", "2028-", "2029-", "2030-", "2031-", "2032-", "2033-", "2034-", "2035-", "2036-", "2037-", "2038-", "2039-", "2040-", "2041-", "2042-", "2043-", "2044-", "2045-", "2046-", "2047-", "2048-", "2049-", "2050-", "2051-", "2052-", "2053-", "2054-", "2055-", "2056-", "2057-", "2058-", "2059-", "2060-", "2061-", "2062-", "2063-", "2064-", "2065-", "2066-", "2067-", "2068-", "2069-" };

	static long[] yearsMillis = { 0L, 31536000000L, 63072000000L, 94694400000L, 126230400000L, 157766400000L, 189302400000L, 220924800000L, 252460800000L, 283996800000L, 315532800000L, 347155200000L, 378691200000L, 410227200000L, 441763200000L, 473385600000L, 504921600000L, 536457600000L, 567993600000L, 599616000000L, 631152000000L, 662688000000L, 694224000000L, 725846400000L, 757382400000L, 788918400000L, 820454400000L, 852076800000L, 883612800000L, 915148800000L, 946684800000L, 978307200000L, 1009843200000L, 1041379200000L, 1072915200000L, 1104537600000L, 1136073600000L, 1167609600000L, 1199145600000L, 1230768000000L, 1262304000000L, 1293840000000L, 1325376000000L, 1356998400000L, 1388534400000L, 1420070400000L, 1451606400000L, 1483228800000L, 1514764800000L, 1546300800000L, 1577836800000L, 1609459200000L, 1640995200000L, 1672531200000L, 1704067200000L, 1735689600000L, 1767225600000L, 1798761600000L, 1830297600000L, 1861920000000L, 1893456000000L, 1924992000000L, 1956528000000L, 1988150400000L, 2019686400000L, 2051222400000L, 2082758400000L, 2114380800000L, 2145916800000L, 2177452800000L, 2208988800000L, 2240611200000L, 2272147200000L, 2303683200000L, 2335219200000L, 2366841600000L, 2398377600000L, 2429913600000L, 2461449600000L, 2493072000000L, 2524608000000L, 2556144000000L, 2587680000000L, 2619302400000L, 2650838400000L, 2682374400000L, 2713910400000L, 2745532800000L, 2777068800000L, 2808604800000L, 2840140800000L, 2871763200000L, 2903299200000L, 2934835200000L, 2966371200000L, 2997993600000L, 3029529600000L, 3061065600000L, 3092601600000L, 3124224000000L };

	static boolean[] leapYears = { false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false };

	static String[] days = { "01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15", "01-16", "01-17", "01-18", "01-19", "01-20", "01-21", "01-22", "01-23", "01-24", "01-25", "01-26", "01-27", "01-28", "01-29", "01-30", "01-31", "02-01", "02-02", "02-03", "02-04", "02-05", "02-06", "02-07", "02-08", "02-09", "02-10", "02-11", "02-12", "02-13", "02-14", "02-15", "02-16", "02-17", "02-18", "02-19", "02-20", "02-21", "02-22", "02-23", "02-24", "02-25", "02-26", "02-27", "02-28", "03-01", "03-02", "03-03", "03-04", "03-05", "03-06", "03-07", "03-08", "03-09", "03-10", "03-11", "03-12", "03-13", "03-14", "03-15", "03-16", "03-17", "03-18", "03-19", "03-20", "03-21", "03-22", "03-23", "03-24", "03-25", "03-26", "03-27", "03-28", "03-29", "03-30", "03-31", "04-01", "04-02", "04-03", "04-04", "04-05", "04-06", "04-07", "04-08", "04-09", "04-10", "04-11", "04-12", "04-13", "04-14", "04-15", "04-16", "04-17", "04-18", "04-19", "04-20", "04-21", "04-22", "04-23", "04-24", "04-25", "04-26", "04-27", "04-28", "04-29", "04-30", "05-01", "05-02", "05-03", "05-04", "05-05", "05-06", "05-07", "05-08", "05-09", "05-10", "05-11", "05-12", "05-13", "05-14", "05-15", "05-16", "05-17", "05-18", "05-19", "05-20", "05-21", "05-22", "05-23", "05-24", "05-25", "05-26", "05-27", "05-28", "05-29", "05-30", "05-31", "06-01", "06-02", "06-03", "06-04", "06-05", "06-06", "06-07", "06-08", "06-09", "06-10", "06-11", "06-12", "06-13", "06-14", "06-15", "06-16", "06-17", "06-18", "06-19", "06-20", "06-21", "06-22", "06-23", "06-24", "06-25", "06-26", "06-27", "06-28", "06-29", "06-30", "07-01", "07-02", "07-03", "07-04", "07-05", "07-06", "07-07", "07-08", "07-09", "07-10", "07-11", "07-12", "07-13", "07-14", "07-15", "07-16", "07-17", "07-18", "07-19", "07-20", "07-21", "07-22", "07-23", "07-24", "07-25", "07-26", "07-27", "07-28", "07-29", "07-30", "07-31", "08-01", "08-02", "08-03", "08-04", "08-05", "08-06", "08-07", "08-08", "08-09", "08-10", "08-11", "08-12", "08-13", "08-14", "08-15", "08-16", "08-17", "08-18", "08-19", "08-20", "08-21", "08-22", "08-23", "08-24", "08-25", "08-26", "08-27", "08-28", "08-29", "08-30", "08-31", "09-01", "09-02", "09-03", "09-04", "09-05", "09-06", "09-07", "09-08", "09-09", "09-10", "09-11", "09-12", "09-13", "09-14", "09-15", "09-16", "09-17", "09-18", "09-19", "09-20", "09-21", "09-22", "09-23", "09-24", "09-25", "09-26", "09-27", "09-28", "09-29", "09-30", "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07", "10-08", "10-09", "10-10", "10-11", "10-12", "10-13", "10-14", "10-15", "10-16", "10-17", "10-18", "10-19", "10-20", "10-21", "10-22", "10-23", "10-24", "10-25", "10-26", "10-27", "10-28", "10-29", "10-30", "10-31", "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11", "11-12", "11-13", "11-14", "11-15", "11-16", "11-17", "11-18", "11-19", "11-20", "11-21", "11-22", "11-23", "11-24", "11-25", "11-26", "11-27", "11-28", "11-29", "11-30", "12-01", "12-02", "12-03", "12-04", "12-05", "12-06", "12-07", "12-08", "12-09", "12-10", "12-11", "12-12", "12-13", "12-14", "12-15", "12-16", "12-17", "12-18", "12-19", "12-20", "12-21", "12-22", "12-23", "12-24", "12-25", "12-26", "12-27", "12-28", "12-29", "12-30", "12-31" };

	static String[] daysInLeapYear = { "01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15", "01-16", "01-17", "01-18", "01-19", "01-20", "01-21", "01-22", "01-23", "01-24", "01-25", "01-26", "01-27", "01-28", "01-29", "01-30", "01-31", "02-01", "02-02", "02-03", "02-04", "02-05", "02-06", "02-07", "02-08", "02-09", "02-10", "02-11", "02-12", "02-13", "02-14", "02-15", "02-16", "02-17", "02-18", "02-19", "02-20", "02-21", "02-22", "02-23", "02-24", "02-25", "02-26", "02-27", "02-28", "02-29", "03-01", "03-02", "03-03", "03-04", "03-05", "03-06", "03-07", "03-08", "03-09", "03-10", "03-11", "03-12", "03-13", "03-14", "03-15", "03-16", "03-17", "03-18", "03-19", "03-20", "03-21", "03-22", "03-23", "03-24", "03-25", "03-26", "03-27", "03-28", "03-29", "03-30", "03-31", "04-01", "04-02", "04-03", "04-04", "04-05", "04-06", "04-07", "04-08", "04-09", "04-10", "04-11", "04-12", "04-13", "04-14", "04-15", "04-16", "04-17", "04-18", "04-19", "04-20", "04-21", "04-22", "04-23", "04-24", "04-25", "04-26", "04-27", "04-28", "04-29", "04-30", "05-01", "05-02", "05-03", "05-04", "05-05", "05-06", "05-07", "05-08", "05-09", "05-10", "05-11", "05-12", "05-13", "05-14", "05-15", "05-16", "05-17", "05-18", "05-19", "05-20", "05-21", "05-22", "05-23", "05-24", "05-25", "05-26", "05-27", "05-28", "05-29", "05-30", "05-31", "06-01", "06-02", "06-03", "06-04", "06-05", "06-06", "06-07", "06-08", "06-09", "06-10", "06-11", "06-12", "06-13", "06-14", "06-15", "06-16", "06-17", "06-18", "06-19", "06-20", "06-21", "06-22", "06-23", "06-24", "06-25", "06-26", "06-27", "06-28", "06-29", "06-30", "07-01", "07-02", "07-03", "07-04", "07-05", "07-06", "07-07", "07-08", "07-09", "07-10", "07-11", "07-12", "07-13", "07-14", "07-15", "07-16", "07-17", "07-18", "07-19", "07-20", "07-21", "07-22", "07-23", "07-24", "07-25", "07-26", "07-27", "07-28", "07-29", "07-30", "07-31", "08-01", "08-02", "08-03", "08-04", "08-05", "08-06", "08-07", "08-08", "08-09", "08-10", "08-11", "08-12", "08-13", "08-14", "08-15", "08-16", "08-17", "08-18", "08-19", "08-20", "08-21", "08-22", "08-23", "08-24", "08-25", "08-26", "08-27", "08-28", "08-29", "08-30", "08-31", "09-01", "09-02", "09-03", "09-04", "09-05", "09-06", "09-07", "09-08", "09-09", "09-10", "09-11", "09-12", "09-13", "09-14", "09-15", "09-16", "09-17", "09-18", "09-19", "09-20", "09-21", "09-22", "09-23", "09-24", "09-25", "09-26", "09-27", "09-28", "09-29", "09-30", "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07", "10-08", "10-09", "10-10", "10-11", "10-12", "10-13", "10-14", "10-15", "10-16", "10-17", "10-18", "10-19", "10-20", "10-21", "10-22", "10-23", "10-24", "10-25", "10-26", "10-27", "10-28", "10-29", "10-30", "10-31", "11-01", "11-02", "11-03", "11-04", "11-05", "11-06", "11-07", "11-08", "11-09", "11-10", "11-11", "11-12", "11-13", "11-14", "11-15", "11-16", "11-17", "11-18", "11-19", "11-20", "11-21", "11-22", "11-23", "11-24", "11-25", "11-26", "11-27", "11-28", "11-29", "11-30", "12-01", "12-02", "12-03", "12-04", "12-05", "12-06", "12-07", "12-08", "12-09", "12-10", "12-11", "12-12", "12-13", "12-14", "12-15", "12-16", "12-17", "12-18", "12-19", "12-20", "12-21", "12-22", "12-23", "12-24", "12-25", "12-26", "12-27", "12-28", "12-29", "12-30", "12-31" };

	static String[] hoursAndminutesAndSeconds = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

	protected DateFormatter() {
	}

	public static void refreshDefaultTimeZone() {
		DEFAULT_TIME_ZONE = TimeZone.getDefault();
	}

	public static String formatISODate(final Date pDate) {
		final long dateMillis = pDate.getTime();
		return formatISODateMillis(dateMillis + DEFAULT_TIME_ZONE.getOffset(dateMillis));
	}

	public static String formatISODateMillis(final long pDateMillis) {
		// FIXME: When leap year days + number of days years of date > one year, BOOM !
		final int year = (int) (pDateMillis / 31536000000L);
		final String dayString;
		if (leapYears[year]) {
			dayString = daysInLeapYear[(int) ((pDateMillis - yearsMillis[year]) / (86400000L))];
		} else {
			dayString = days[(int) ((pDateMillis - yearsMillis[year]) / (86400000L))];
		}
		return years[year] + dayString;
	}

	public static String formatISODateTime(final Date pDateTime) {
		final long dateTimeMillis = pDateTime.getTime();
		return formatISODateTimeMillis(dateTimeMillis + DEFAULT_TIME_ZONE.getOffset(dateTimeMillis));
	}

	public static String formatISODateTimeMillis(final long pDateTimeMillis) {
		final int year = (int) (pDateTimeMillis / 31536000000L);
		final String dayString;
		final long dayMillis = pDateTimeMillis - yearsMillis[year];
		if (leapYears[year]) {
			dayString = daysInLeapYear[(int) (dayMillis / (86400000L))];
		} else {
			dayString = days[(int) (dayMillis / (86400000L))];
		}
		final int hour = (int) (dayMillis % 86400000L) / 3600000;
		final int minute = (int) ((dayMillis % 86400000L) % 3600000) / 60000;
		final int second = (int) (((dayMillis % 86400000L) % 3600000) % 60000) / 1000;
		return years[year] + dayString + 'T' + hoursAndminutesAndSeconds[hour] + ':' + hoursAndminutesAndSeconds[minute] + ':' + hoursAndminutesAndSeconds[second];
	}
}