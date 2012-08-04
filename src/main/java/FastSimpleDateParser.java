import java.util.Date;

public final class FastSimpleDateParser {

    private static class YearInMillisCache {
        static long[] cache = new long[] { 0L,
                31622400000L,
                63158400000L,
                94780800000L,
                126403200000L,
                158025600000L,
                189561600000L,
                221184000000L,
                252806400000L,
                284428800000L,
                315964800000L,
                347587200000L,
                379209600000L,
                410832000000L,
                442368000000L,
                473990400000L,
                505612800000L,
                537235200000L,
                568771200000L,
                600393600000L,
                632016000000L,
                663638400000L,
                695174400000L,
                726796800000L,
                758419200000L,
                790041600000L,
                821577600000L,
                853200000000L,
                884822400000L,
                916444800000L,
                947980800000L,
                979603200000L,
                1011225600000L,
                1042848000000L,
                1074384000000L,
                1106006400000L,
                1137628800000L,
                1169251200000L,
                1200787200000L,
                1232409600000L,
                1264032000000L,
                1295654400000L,
                1327190400000L,
                1358812800000L,
                1390435200000L,
                1422057600000L,
                1453593600000L,
                1485216000000L,
                1516838400000L,
                1548460800000L,
                1579996800000L
        };
    }

    private static class MonthInMillisCache {
        static long[] cache = new long[] { 0L,
                2678400000L,
                5097600000L,
                7776000000L,
                10368000000L,
                13046400000L,
                15638400000L,
                18316800000L,
                20995200000L,
                23587200000L,
                26265600000L,
                28857600000L };
        static long[] cacheLeapYear = new long[] { 0L,
                2678400000L,
                5184000000L,
                7862400000L,
                10454400000L,
                13132800000L,
                15724800000L,
                18403200000L,
                21081600000L,
                23673600000L,
                26352000000L,
                28944000000L };
    }

    protected FastSimpleDateParser() {
    }

    public static Date parseDate(final String pDate) {
        final int year = Integer.parseInt(pDate.substring(0, 4));
        final long yearMillis = YearInMillisCache.cache[year - 1970];
        final int month = Integer.parseInt(pDate.substring(5, 7)) - 1;
        final long monthMillis = ((year & 3) == 0) && (((year % 100) != 0) || ((year % 400) == 0)) ? MonthInMillisCache.cacheLeapYear[month] : MonthInMillisCache.cache[month];
        final long dayMillis = 86400000L * (Integer.parseInt(pDate.substring(8, 10)) - 1);
        return new Date(yearMillis + monthMillis + dayMillis);
    }
}
