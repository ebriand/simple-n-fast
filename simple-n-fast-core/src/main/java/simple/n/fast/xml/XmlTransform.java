package simple.n.fast.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class XmlTransform {

    private static final char[] CDATA_ENTITY = { '[', 'C', 'D', 'A', 'T', 'A', '[' };

    private static final char[] DOCTYPE_ENTITY = { 'D', 'O', 'C', 'T', 'Y', 'P', 'E' };

    public static String prettify(final String pXml) {
        final StringWriter stringWriter = new StringWriter(pXml.length());
        try {
            prettify(new StringReader(pXml), stringWriter);
        } catch (final IOException e) {
            // cannot happen here.
        }
        return stringWriter.toString();
    }

    public static void prettify(final Reader pReader, final Writer pWriter) throws IOException {
        int level = 0;
        boolean previousNodeWasClosing = false;
        boolean textAfterLastNode = false;
        boolean inNode = false;
        int previousCharacter = -1;
        final ParseResult character = new ParseResult();
        parseToNextNonBlankChar(pReader, character);
        final ParseResult nextCharacter = new ParseResult();
        parseToNextNonBlankChar(pReader, nextCharacter);
        final char[] entityChars = new char[7];
        while (character.nonBlankChar != -1) {
            if (character.nonBlankChar == 60) {
                if ((nextCharacter.nonBlankChar == 47)) {
                    level--;
                    if ((!textAfterLastNode) && (previousNodeWasClosing)) {
                        indent(pWriter, level);
                    }
                    inNode = true;
                    textAfterLastNode = false;
                    previousNodeWasClosing = true;
                } else if ((nextCharacter.nonBlankChar == 33)) {
                    pReader.read(entityChars, 0, 7);
                    if (arraysEquals(CDATA_ENTITY, entityChars)) {
                        pWriter.write(character.nonBlankChar);
                        pWriter.write(nextCharacter.nonBlankChar);
                        pWriter.write(entityChars);
                        writeRaw(pReader, pWriter, ']', new char[] { ']', '>' });
                        textAfterLastNode = true;
                    } else if (('-' == entityChars[0]) && ('-' == entityChars[1])) {
                        indent(pWriter, level);
                        pWriter.write(character.nonBlankChar);
                        pWriter.write(nextCharacter.nonBlankChar);
                        pWriter.write(entityChars);
                        writeRaw(pReader, pWriter, '-', new char[] { '-', '>' });
                    } else if (arraysEquals(DOCTYPE_ENTITY, entityChars)) {
                        pWriter.write(character.nonBlankChar);
                        pWriter.write(nextCharacter.nonBlankChar);
                        pWriter.write(entityChars);
                        writeRaw(pReader, pWriter, '>');
                        pWriter.write("\n");
                    }
                    character.nonBlankChar = -1;
                    parseToNextNonBlankChar(pReader, nextCharacter);
                    inNode = false;
                } else if ((nextCharacter.nonBlankChar == 63)) {
                    pWriter.write(character.nonBlankChar);
                    pWriter.write(nextCharacter.nonBlankChar);
                    writeRaw(pReader, pWriter, '?', '>');
                    pWriter.write("\n");
                    character.nonBlankChar = -1;
                    parseToNextNonBlankChar(pReader, nextCharacter);
                    inNode = false;
                } else if (!textAfterLastNode) {
                    if (level != 0) {
                        indent(pWriter, level);
                    }
                    level++;
                    inNode = true;
                    textAfterLastNode = false;
                    previousNodeWasClosing = false;
                }

            } else {
                if (inNode && (character.nonBlankChar == 62)) {
                    inNode = false;
                    if (previousCharacter == 47) {
                        level--;
                        previousNodeWasClosing = true;
                    }
                } else if (!inNode) {
                    textAfterLastNode = true;
                    pWriter.write(character.skippedBlankChars.toString());
                }
            }
            if ((character.nonBlankChar != 60) && (character.nonBlankChar != 47) && (previousCharacter != 60) && (previousCharacter != 47) && (inNode) && (character.skippedBlankChars.length() != 0)) {
                pWriter.write(' ');
            }
            if (character.nonBlankChar != -1) {
                pWriter.write(character.nonBlankChar);
            }
            previousCharacter = character.nonBlankChar;
            character.nonBlankChar = nextCharacter.nonBlankChar;
            character.skippedBlankChars.setLength(0);
            character.skippedBlankChars.append(nextCharacter.skippedBlankChars);
            parseToNextNonBlankChar(pReader, nextCharacter);
        }
    }

    private static void writeRaw(final Reader pReader, final Writer pWriter, final char pRupture) throws IOException {
        int character = pReader.read();
        while (character != -1) {
            if (character == pRupture) {
                pWriter.write(character);
                break;
            }
            pWriter.write(character);
            character = pReader.read();
        }
    }

    private static void writeRaw(final Reader pReader, final Writer pWriter, final char pFirstCharacterOfRupture, final char... pRupture) throws IOException {
        final int ruptureLength = pRupture.length;
        final char[] rupture = pRupture;
        int character = pReader.read();
        final char[] nextChars = new char[ruptureLength];
        while (character != -1) {
            if (character == pFirstCharacterOfRupture) {
                pReader.read(nextChars);
                pWriter.write(character);
                if (arraysEquals(rupture, nextChars)) {
                    pWriter.write(nextChars);
                    break;
                }
                pWriter.write(nextChars);
            } else {
                pWriter.write(character);
            }
            character = pReader.read();
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

    private static void parseToNextNonBlankChar(final Reader pReader, final ParseResult pParseResult) throws IOException {
        pParseResult.nonBlankChar = pReader.read();
        pParseResult.skippedBlankChars.setLength(0);
        while ((pParseResult.nonBlankChar != -1) && (isBlankCharacter(pParseResult.nonBlankChar))) {
            pParseResult.skippedBlankChars.append((char) pParseResult.nonBlankChar);
            pParseResult.nonBlankChar = pReader.read();
        }
    }

    private static boolean arraysEquals(final char[] pLeft, final char[] pRight) {
        final int length = pLeft.length;
        for (int i = 0; i < length; i++) {
            if (pLeft[i] != pRight[i]) {
                return false;
            }
        }
        return true;
    }

    static class ParseResult {
        int nonBlankChar;

        StringBuilder skippedBlankChars = new StringBuilder(256);
    }
}
