package simple.n.fast.performances.date;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class ArrayCopyBenchmark extends SimpleBenchmark {

    @Param("100000")
    private int size;

    public static void main(final String[] args) throws Exception {
        Runner.main(ArrayCopyBenchmark.class, args);
    }

    public void timeSystemArrayCopy(final int reps) {
        final char[] year = { '1', '9', '7', '0' };
        final char[] date = new char[19];
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                System.arraycopy(year, 0, date, 0, 4);
            }
        }
    }

    public void timeCharByChar(final int reps) {
        final char[] year = { '1', '9', '7', '0' };
        final char[] date = new char[19];
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                date[0] = year[0];
                date[1] = year[1];
                date[2] = year[2];
                date[3] = year[3];
            }
        }
    }
}
