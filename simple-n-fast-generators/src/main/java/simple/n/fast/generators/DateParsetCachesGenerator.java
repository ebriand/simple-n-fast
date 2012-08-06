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
		System.out.println("For paser : Years in millis from 1970 to " + ((1970 + k) - 1) + " { " + Joiner.on("L, ").join(times) + "L }");

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
		System.out.println("For paser : Leap years from 1970 to " + ((1970 + k) - 1) + " { " + Joiner.on(", ").join(leapYears) + " }");

		final List<String> minutes = Lists.newArrayList();
		for (int blah = 0; blah < 60; blah++) {
			minutes.add(blah < 10 ? "0" + blah : blah + "");
		}

		System.out.println(" { " + Joiner.on("\", \"").join(minutes) + " }");
	}
}
