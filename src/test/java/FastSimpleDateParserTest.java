import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

public class FastSimpleDateParserTest {

    @Test
    public void parseDateTest() {
        Assert.assertEquals(DateTime.parse("2012-07-01").toDate(), FastSimpleDateParser.parseDate("2012-07-01"));
    }
}
