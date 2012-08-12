package simple.n.fast.performances.date;

import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class LeapYearBenchmark extends SimpleBenchmark {

	@Param("10000")
	private int size;

	private final boolean[] leapYears = { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false };

	private int[] years;

	@Override
	protected void setUp() throws Exception {
		final Random random = new Random();
		years = new int[size];
		for (int i = 0; i < years.length; i++) {
			years[i] = random.nextInt(100);
		}
	}

	public static void main(final String[] args) throws Exception {
		Runner.main(LeapYearBenchmark.class, args);
	}

	public int timeLeapYear(final int reps) {
		int count = 0;
		for (int i = 0; i < reps; i++) {
			for (final int year : years) {
				if (((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0))) {
					count++;
				}
			}
		}
		return count;
	}

	public int timeArrayLeapYear(final int reps) {
		int count = 0;
		for (int i = 0; i < reps; i++) {
			for (final int year : years) {
				if (leapYears[year]) {
					count++;
				}
			}
		}
		return count;
	}

}
