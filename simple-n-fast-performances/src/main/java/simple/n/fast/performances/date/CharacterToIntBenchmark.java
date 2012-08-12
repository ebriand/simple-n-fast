package simple.n.fast.performances.date;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class CharacterToIntBenchmark extends SimpleBenchmark {

	private final int[] charToInt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	@Param("100000")
	private int size;

	public static void main(final String[] args) throws Exception {
		Runner.main(CharacterToIntBenchmark.class, args);
	}

	public int timeCharacterDigit(final int reps) {
		int count = 0;
		for (int i = 0; i < reps; i++) {
			for (int j = 0; j < size; j++) {
				count += Character.digit('1', 10);
			}
		}
		return count;
	}

	public int timeCharacterArray(final int reps) {
		int count = 0;
		for (int i = 0; i < reps; i++) {
			for (int j = 0; j < size; j++) {
				count += charToInt['1'];
			}
		}
		return count;
	}

}
