package simple.n.fast.xml;

import junit.framework.Assert;

import org.junit.Test;

public class XmlTransformTest {

    @Test
    public void prettifyTest() {
        final String xml = "<blah><foo>bar</foo>  <blahblah><blah1><blah2>blah</blah2></blah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah>\n" +
                "    <foo>bar</foo>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2>blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest2() {
        final String xml = "<blah><foo>bar</foo>  <blahblah><blah1><blah2>blah</blah2>< / blah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah>\n" +
                "    <foo>bar</foo>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2>blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest3() {
        final String xml = "<blah><foo>bar\t</foo><blahblah><\tblah1><blah2>blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah>\n" +
                "    <foo>bar</foo>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2>blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest4() {
        final String xml = "<blah><foo>bar\n</foo><\nblahblah><\tblah1><blah2>blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah>\n" +
                "    <foo>bar</foo>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2>blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest5() {
        final String xml = "<blah blah=\"true\"><foo>bar\n</foo><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\">blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo>bar</foo>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\">blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest6() {
        final String xml = "<blah blah=\"true\"><foo/><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\">blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\">blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest7() {
        final String xml = "<blah blah=\"true\"><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\">blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\">blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest8() {
        final String xml = "<blah blah=\"true\"><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\">blah blah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\">blah blah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest9() {
        final String xml = "<blah blah=\"true\"><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\">blah\t\t\nblah</blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\">blah\t\t\nblah</blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest10() {
        final String xml = "<blah blah=\"true\"><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest11() {
        final String xml = "<blah blah=\"true\"><!--baoiha\nbiohbioh --><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<blah blah=\"true\">\n" +
                "    <!--baoiha\n" +
                "biohbioh -->\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest12() {
        final String xml = "<?xml version=\"1.0\" ?><blah blah=\"true\"><!--baoiha\nbiohbioh --><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<?xml version=\"1.0\" ?>\n" +
                "<blah blah=\"true\">\n" +
                "    <!--baoiha\n" +
                "biohbioh -->\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest13() {
        final String xml = "<?xml version=\"1.0\" ?><!DOCTYPE blah SYSTEM \"blah.dtd\"><blah blah=\"true\"><!--baoiha\nbiohbioh --><foo/\t\t><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<?xml version=\"1.0\" ?>\n" +
                "<!DOCTYPE blah SYSTEM \"blah.dtd\">\n" +
                "<blah blah=\"true\">\n" +
                "    <!--baoiha\n" +
                "biohbioh -->\n" +
                "    <foo/>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest14() {
        final String xml = "<?xml version=\"1.0\" ?><!DOCTYPE blah SYSTEM \"blah.dtd\"><blah blah=\"true\"\t><!--baoiha\nbiohbioh --><foo/\t\t><bar></bar><\nblahblah><\tblah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>< /\tblah1></blahblah></blah>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<?xml version=\"1.0\" ?>\n" +
                "<!DOCTYPE blah SYSTEM \"blah.dtd\">\n" +
                "<blah blah=\"true\">\n" +
                "    <!--baoiha\n" +
                "biohbioh -->\n" +
                "    <foo/>\n" +
                "    <bar></bar>\n" +
                "    <blahblah>\n" +
                "        <blah1>\n" +
                "            <blah2 truc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2>\n" +
                "        </blah1>\n" +
                "    </blahblah>\n" +
                "</blah>", preffitiedXml);
    }

    @Test
    public void prettifyTest15() {
        final String xml = "<?xml version=\"1.0\" ?><blah2>blah</blah2>";
        final String preffitiedXml = XmlTransform.prettify(xml);
        Assert.assertEquals("<?xml version=\"1.0\" ?>\n" +
                "<blah2>blah</blah2>", preffitiedXml);
    }
}
