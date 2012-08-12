package simple.n.fast.date;

import java.util.Date;
import java.util.TimeZone;

public final class DateParser {

	private static TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();

	static long[] yearsMillis = { 946684800000L, 978307200000L, 1009843200000L, 1041379200000L, 1072915200000L, 1104537600000L, 1136073600000L, 1167609600000L, 1199145600000L, 1230768000000L, 1262304000000L, 1293840000000L, 1325376000000L, 1356998400000L, 1388534400000L, 1420070400000L, 1451606400000L, 1483228800000L, 1514764800000L, 1546300800000L, 1577836800000L, 1609459200000L, 1640995200000L, 1672531200000L, 1704067200000L, 1735689600000L, 1767225600000L, 1798761600000L, 1830297600000L, 1861920000000L, 1893456000000L, 1924992000000L, 1956528000000L, 1988150400000L, 2019686400000L, 2051222400000L, 2082758400000L, 2114380800000L, 2145916800000L, 2177452800000L, 2208988800000L, 2240611200000L, 2272147200000L, 2303683200000L, 2335219200000L, 2366841600000L, 2398377600000L, 2429913600000L, 2461449600000L, 2493072000000L, 2524608000000L, 2556144000000L, 2587680000000L, 2619302400000L, 2650838400000L, 2682374400000L, 2713910400000L, 2745532800000L, 2777068800000L, 2808604800000L, 2840140800000L, 2871763200000L, 2903299200000L, 2934835200000L, 2966371200000L, 2997993600000L, 3029529600000L, 3061065600000L, 3092601600000L, 3124224000000L, 0L, 31536000000L, 63072000000L, 94694400000L, 126230400000L, 157766400000L, 189302400000L, 220924800000L, 252460800000L, 283996800000L, 315532800000L, 347155200000L, 378691200000L, 410227200000L, 441763200000L, 473385600000L, 504921600000L, 536457600000L, 567993600000L, 599616000000L, 631152000000L, 662688000000L, 694224000000L, 725846400000L, 757382400000L, 788918400000L, 820454400000L, 852076800000L, 883612800000L, 915148800000L };

	static boolean[] leapYears = { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false };

	static long[] monthMillis = { 0L, 2678400000L, 5097600000L, 7776000000L, 10368000000L, 13046400000L, 15638400000L, 18316800000L, 20995200000L, 23587200000L, 26265600000L, 28857600000L };

	static long[] monthMillisInLeapYear = { 0L, 2678400000L, 5184000000L, 7862400000L, 10454400000L, 13132800000L, 15724800000L, 18403200000L, 21081600000L, 23673600000L, 26352000000L, 28944000000L };

	static int[] charToInt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	protected DateParser() {
	}

	public static void refreshDefaultTimeZone() {
		DEFAULT_TIME_ZONE = TimeZone.getDefault();
	}

	public static Date parseISODate(final String pDate) {
		return new Date(parseISODateToMillis(pDate));
	}

	public static long parseISODateToMillis(final String pDate) {
		final long dateMillis = internalParseISODateToMillis(pDate);
		return dateMillis - DEFAULT_TIME_ZONE.getOffset(dateMillis);
	}

	public static long internalParseISODateToMillis(final String pDate) {
		final int year = (charToInt[pDate.charAt(2)] * 10) + charToInt[pDate.charAt(3)];
		final int month = ((charToInt[pDate.charAt(5)] * 10) + charToInt[pDate.charAt(6)]) - 1;
		final long dateMillis = yearsMillis[year]
				+ (leapYears[year] ? monthMillisInLeapYear[month] : monthMillis[month])
				+ ((((charToInt[pDate.charAt(8)] * 10) + charToInt[pDate.charAt(9)]) - 1) * 86400000L);
		return dateMillis;
	}

	public static Date parseISODateTime(final String pDateTime) {
		return new Date(parseISODateTimeToMillis(pDateTime));
	}

	public static long parseISODateTimeToMillis(final String pDateTime) {
		final long dateTimeMillis = internalParseISODateTimeToMillis(pDateTime);
		return dateTimeMillis - DEFAULT_TIME_ZONE.getOffset(dateTimeMillis);
	}

	private static long internalParseISODateTimeToMillis(final String pDateTime) {
		final long dateTimeMillis = internalParseISODateToMillis(pDateTime)
				+ (((charToInt[pDateTime.charAt(11)] * 10) + charToInt[pDateTime.charAt(12)]) * 3600000)
				+ (((charToInt[pDateTime.charAt(14)] * 10) + charToInt[pDateTime.charAt(15)]) * 60000)
				+ (((charToInt[pDateTime.charAt(17)] * 10) + charToInt[pDateTime.charAt(18)]) * 1000);
		return dateTimeMillis;
	}

	public static Date parseISODateTimeWithOffset(final String pDateTimeWithOffset) {
		return null;
	}

	public static Date parseISOUnknowDate(final String pDate) {
		return null;
	}
}
