package simple.n.fast.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class XmlTransform {

    public static String prettify(final String pXml) {
        final StringWriter stringWriter = new StringWriter(pXml.length());
        try {
            prettify(new StringReader(pXml), stringWriter);
        } catch (final IOException e) {
            // cannot happen here.
        }
        return stringWriter.toString();
    }

    private static void prettify(final Reader pReader, final Writer pWriter) throws IOException {
        int level = 0;
        boolean firstNode = true;
        boolean textAfterLastNode = false;
        boolean inNode = false;
        final boolean inCdata = false;
        ParseResult previousCharacter = new ParseResult();
        ParseResult character = parseToNextNonBlankChar(pReader);
        ParseResult nextCharacter = parseToNextNonBlankChar(pReader);
        while (character.nonBlankChar != -1) {
            if (character.nonBlankChar == 60) {
                if ((nextCharacter.nonBlankChar == 47)) {
                    level--;
                    if (!textAfterLastNode) {
                        indent(pWriter, level);
                    }
                } else if (!textAfterLastNode) {
                    if (!firstNode) {
                        indent(pWriter, level);
                    } else {
                        firstNode = false;
                    }
                    level++;
                }
                inNode = true;
                textAfterLastNode = false;
            } else {
                if (inNode && (character.nonBlankChar == 62)) {
                    inNode = false;
                    if (previousCharacter.nonBlankChar == 47) {
                        level--;
                    }
                } else if ((!inNode) && (!isBlankCharacter(character.nonBlankChar))) {
                    textAfterLastNode = true;
                }
            }
            if ((character.nonBlankChar != 60) && (character.nonBlankChar != 47) && (previousCharacter.nonBlankChar != 60) && (previousCharacter.nonBlankChar != 47) && (inNode) && (character.skippedBlankChars.length() != 0)) {
                pWriter.write(' ');
            }
            if ((previousCharacter.nonBlankChar != 47) && (!inNode || inCdata)) {
                pWriter.write(character.skippedBlankChars.toString());
            }
            pWriter.write(character.nonBlankChar);
            previousCharacter = character;
            character = nextCharacter;
            nextCharacter = parseToNextNonBlankChar(pReader);
        }
    }

    private static void indent(final Writer pWriter, final int level) throws IOException {
        pWriter.append('\n');
        for (int i = 0; i < level; i++) {
            pWriter.append("    ");
        }
    }

    private static boolean isBlankCharacter(final int pCharacter) {
        return (pCharacter == -1) || (pCharacter == 32) || (pCharacter == 9) || (pCharacter == 10);
    }

    private static ParseResult parseToNextNonBlankChar(final Reader pReader) throws IOException {
        final ParseResult parseResult = new ParseResult();
        parseResult.nonBlankChar = pReader.read();
        while ((parseResult.nonBlankChar != -1) && (isBlankCharacter(parseResult.nonBlankChar))) {
            parseResult.skippedBlankChars.append((char) parseResult.nonBlankChar);
            parseResult.nonBlankChar = pReader.read();
        }
        return parseResult;
    }

    static class ParseResult {
        int nonBlankChar;

        StringBuilder skippedBlankChars = new StringBuilder();
    }
}
