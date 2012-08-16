package simple.n.fast.benchmarks.date;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class StringBuilderBenchmark extends SimpleBenchmark {

    @Param("10000")
    private int size;

    public static void main(final String[] args) {
        Runner.main(StringBuilderBenchmark.class, args);
    }

    public String timeStringBuilder(final int reps) {
        String result = null;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                final StringBuilder stringBuilder = new StringBuilder(19)
                        .append("1982-")
                        .append("04-02")
                        .append('T')
                        .append("18")
                        .append(':')
                        .append("15")
                        .append(':')
                        .append("38");
                result = stringBuilder.toString();
            }
        }
        return result;
    }

    public String timeCharArray(final int reps) {

        String result = null;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                final char[] year = { '1', '9', '8', '2' };
                final char[] month = { '0', '4' };
                final char[] day = { '0', '2' };
                final char[] hour = { '1', '8' };
                final char[] minute = { '1', '5' };
                final char[] second = { '3', '8' };
                final char[] date = { year[0], year[1], year[2], year[3], '-', month[0], month[1], '-', day[0], day[1], 'T', hour[0], hour[1], ':', minute[0], minute[1], ':', second[0], second[1] };
                result = new String(date);
            }
        }
        return result;
    }
}
