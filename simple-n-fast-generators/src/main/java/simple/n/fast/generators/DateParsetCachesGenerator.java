package simple.n.fast.generators;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class DateParsetCachesGenerator {

	public static void main(final String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		final List<Long> times = Lists.newArrayList();
		int i = 0;
		for (; i < 100; i++) {
			final int year = 1970 + i;
			final DateTime date = DateTime.parse(year + "-01-01");
			final long dateInMillis = date.getMillis();
			times.add(dateInMillis);
		}
		System.out.println("Years in millis from 1970 to " + ((1970 + i) - 1) + " { " + Joiner.on("L, ").join(times) + "L }");

		final List<Integer> leapYears = Lists.newArrayList();
		int j = 0;
		for (; j < 100; j++) {
			final int year = 1970 + j;
			if (((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0))) {
				leapYears.add(year);
			}
		}
		System.out.println("Leap years from 1970 to " + ((1970 + i) - 1) + " { " + Joiner.on(", ").join(leapYears) + " }");
	}
}
