package simple.n.fast.performances.date;

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
                final char[] date = new char[19];
                date[0] = '1';
                date[1] = '9';
                date[2] = '8';
                date[3] = '2';
                date[4] = '-';
                date[5] = '0';
                date[6] = '2';
                date[7] = '-';
                date[8] = '0';
                date[9] = '2';
                date[10] = 'T';
                date[11] = '1';
                date[12] = '8';
                date[13] = ':';
                date[14] = '1';
                date[15] = '5';
                date[16] = ':';
                date[17] = '3';
                date[18] = '8';
                result = new String(date);
            }
        }
        return result;
    }
}
