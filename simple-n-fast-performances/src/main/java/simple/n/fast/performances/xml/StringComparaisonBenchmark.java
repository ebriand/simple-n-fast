package simple.n.fast.performances.xml;

import java.util.Arrays;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class StringComparaisonBenchmark extends SimpleBenchmark {

    @Param("10000")
    private int size;

    public static void main(final String[] args) throws Exception {
        Runner.main(StringComparaisonBenchmark.class, args);
    }

    public int timeArraysEquals(final int reps) {
        final char[] blah = { 'B', 'L', 'A', 'H' };
        final char[] sameBlah = { 'B', 'L', 'A', 'H' };
        int count = 0;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                if (Arrays.equals(blah, sameBlah)) {
                    count += j;
                }
            }
        }
        return count;
    }

    public int timeSpecificArraysEquals(final int reps) {
        final char[] blah = { 'B', 'L', 'A', 'H' };
        final char[] sameBlah = { 'B', 'L', 'A', 'H' };
        int count = 0;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                if (arraysEquals(blah, sameBlah)) {
                    count += j;
                }
            }
        }
        return count;
    }

    private boolean arraysEquals(final char[] pLeft, final char[] pRight) {
        final int length = pLeft.length;
        for (int i = 0; i < length; i++) {
            if (pLeft[i] != pRight[i]) {
                return false;
            }
        }
        return true;
    }

    public int timeStringsEquals(final int reps) {
        final String blah = "blah";
        final String sameBlah = "blah";
        int count = 0;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                if (blah.equals(sameBlah)) {
                    count += j;
                }
            }
        }
        return count;
    }

    public int timeStringEqualsNewString(final int reps) {
        final String blah = "blah";
        final char[] sameBlah = { 'B', 'L', 'A', 'H' };
        int count = 0;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                if (blah.equals(new String(sameBlah))) {
                    count += j;
                }
            }
        }
        return count;
    }

    public int timeNewStringEqualsNewString(final int reps) {
        final char[] blah = { 'B', 'L', 'A', 'H' };
        final char[] sameBlah = { 'B', 'L', 'A', 'H' };
        int count = 0;
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                if (new String(blah).equals(new String(sameBlah))) {
                    count += j;
                }
            }
        }
        return count;
    }
}
