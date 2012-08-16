package simple.n.fast.benchmarks.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import simple.n.fast.xml.XmlTransform;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class XmlTransformBenchmark extends SimpleBenchmark {

    @Param("10000")
    private int size;

    private final String xml = "<?xml version=\"1.0\" ?><blah blah=\"true\"><!--baoiha\nbiohbioh --><foo/><bar></bar><blahblah><blah1><blah2\ntruc=\"blah blah\"><![CDATA[blah\t\t\nblah]]></blah2></blah1></blahblah></blah>";

    public static void main(final String[] args) throws Exception {
        Runner.main(XmlTransformBenchmark.class, args);
    }

    public void timeSimpleNFastPrettify(final int reps) {
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                XmlTransform.prettify(xml);
            }
        }
    }

    public void timeJDKPrettify(final int reps) throws TransformerException {
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                jdkPrint(xml);
            }
        }
    }

    public void timeXercesPrettify(final int reps) {
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < size; j++) {
                format(xml);
            }
        }
    }

    private static Transformer transformer;
    static {
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    }

    public final String jdkPrint(final String pXml) throws TransformerException {
        // Instantiate transformer input
        final Source xmlInput = new StreamSource(new StringReader(pXml));
        final StreamResult xmlOutput = new StreamResult(new StringWriter());
        // Configure transformer
        transformer.transform(xmlInput, xmlOutput);
        return xmlOutput.getWriter().toString();
    }

    public final String format(final String unformattedXml) {
        try {
            final Document document = parseXmlFile(unformattedXml);

            final OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(4);
            final Writer out = new StringWriter();
            final XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);

            return out.toString();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Document parseXmlFile(final String in) {
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (final ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (final SAXException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
