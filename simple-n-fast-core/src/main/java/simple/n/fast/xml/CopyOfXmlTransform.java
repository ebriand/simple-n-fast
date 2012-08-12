package simple.n.fast.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CopyOfXmlTransform {

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
        boolean isNodeContent = false;
        boolean isContent = false;
        boolean otherCharactersSinceControl = false;
        int character = getNextNonBlankCharacter(pReader).nonBlankCharacter;
        int previousNonBlankCharacter = -1;
        int previousCharacter = -1;
        while (character != -1) {
            switch (character) {
                case 60:
                    final List<Character> charactersToAppend = new ArrayList<>();
                    if (level == 0) {
                        level++;
                    } else if (!isContent) {
                        final int nextCharacter = getNextNonBlankCharacter(pReader).nonBlankCharacter;
                        charactersToAppend.add((char) nextCharacter);
                        if (nextCharacter == 47) {
                            level--;
                        } else if (nextCharacter == 33) {

                        }
                        pWriter.append('\n');
                        for (int i = 0; i < level; i++) {
                            pWriter.append("    ");
                        }
                        if (nextCharacter != 47) {
                            level++;
                        }
                    } else {
                        level--;
                    }
                    pWriter.append((char) character);
                    if (!charactersToAppend.isEmpty()) {
                        for (final Character nextCharacter : charactersToAppend) {
                            pWriter.append(nextCharacter);
                            character = nextCharacter;
                        }
                    }
                    isContent = false;
                    isNodeContent = true;
                    otherCharactersSinceControl = false;
                    break;
                case 62:
                    if (previousNonBlankCharacter == 47) {
                        level--;
                    }
                    pWriter.append((char) character);
                    isContent = false;
                    isNodeContent = false;
                    break;
                default:
                    if (!isBlankCharacter(character)) {
                        if (isNodeContent) {
                            if ((!otherCharactersSinceControl)) {
                                pWriter.append((char) character);
                                otherCharactersSinceControl = true;
                            } else if (otherCharactersSinceControl && isBlankCharacter(previousCharacter)) {
                                pWriter.append(' ');
                                pWriter.append((char) character);
                            } else {
                                pWriter.append((char) character);
                            }
                        } else {
                            isContent = true;
                            pWriter.append((char) character);
                        }
                    } else if (isContent) {
                        final NonBlankParseResult nextCharacterNonBlank = getNextNonBlankCharacter(pReader);
                        if (!isControlCharacter(nextCharacterNonBlank.nonBlankCharacter)) {
                            pWriter.append((char) character);
                            pWriter.append(nextCharacterNonBlank.skippedCharactersAsString());
                        }
                        pWriter.append((char) nextCharacterNonBlank.nonBlankCharacter);
                        character = nextCharacterNonBlank.nonBlankCharacter;
                        if (character == 60) {
                            level--;
                        }
                    }
                    break;
            }
            if (!isBlankCharacter(character)) {
                previousNonBlankCharacter = character;
            }
            previousCharacter = character;
            character = pReader.read();
        }
    }

    private static NonBlankParseResult getNextNonBlankCharacter(final Reader pReader) throws IOException {
        final NonBlankParseResult parseResult = new NonBlankParseResult();
        parseResult.nonBlankCharacter = pReader.read();
        while ((parseResult.nonBlankCharacter != -1) && isBlankCharacter(parseResult.nonBlankCharacter)) {
            parseResult.skippedCharacters.add(parseResult.nonBlankCharacter);
            parseResult.nonBlankCharacter = pReader.read();
        }
        return parseResult;
    }

    private static boolean isBlankCharacter(final int pCharacter) {
        return (pCharacter == -1) || (pCharacter == 32) || (pCharacter == 9) || (pCharacter == 10);
    }

    private static boolean isControlCharacter(final int pCharacter) {
        return (pCharacter == 60) || (pCharacter == 62) || (pCharacter == 47);
    }

    static class NonBlankParseResult {
        int nonBlankCharacter;
        List<Integer> skippedCharacters = new ArrayList<>();

        String skippedCharactersAsString() {
            final char[] characters = new char[skippedCharacters.size()];
            int count = 0;
            for (final int skippedCharacter : skippedCharacters) {
                characters[count] = (char) skippedCharacter;
                count++;
            }
            return new String(characters);
        }
    }
}
