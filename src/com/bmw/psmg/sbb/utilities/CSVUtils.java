package com.bmw.psmg.sbb.utilities;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CSVUtils.
 */
public final class CSVUtils {

    private static final Logger LOG = Logger.getLogger(CSVUtils.class.getName());
    private static final char DEFAULT_SEPARATOR = ';';
    private static final char DEFAULT_QUOTE = '"';

    private CSVUtils() {
    }

    /**
     * Write line.
     *
     * @param filePath the file path
     * @param values   the values
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeLine(String filePath, List<String> values) throws IOException {
        writeLine(filePath, values, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    /**
     * Write line.
     *
     * @param filePath   the file path
     * @param values     the values
     * @param separators the separators
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeLine(String filePath, List<String> values, char separators) throws IOException {
        writeLine(filePath, values, separators, DEFAULT_QUOTE);
    }

    /**
     * Follow cvs format.
     *
     * @param value the value
     * @return the string
     */
    private static String followCVSFormat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    /**
     * Write line.
     *
     * @param filePath    the file path
     * @param values      the values
     * @param separators  the separators
     * @param customQuote the custom quote
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static synchronized void writeLine(String filePath, List<String> values, char separators, char customQuote) throws IOException {

        createLogFile(filePath);

        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSFormat(value));
            } else {
                sb.append(customQuote).append(followCVSFormat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    private static synchronized void createLogFile(String filePath) throws IOException {
        File logFile = new File(filePath);
        File parentDir = logFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!Files.exists(Paths.get(filePath))) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (FileAlreadyExistsException fae) {
                LOG.info("File: " + filePath + " already exists. Creation skipped.\n" + fae);
            }
        }
    }

    /**
     * Parses the line.
     *
     * @param cvsLine the cvs line
     * @return the list
     */
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    /**
     * Parses the line.
     *
     * @param cvsLine     the cvs line
     * @param separators  the separators
     * @param customQuote the custom quote
     * @return the list
     */
    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        // if empty, return!
        if (cvsLine == null || cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder curVal = new StringBuilder();
        boolean inQuotes = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    // Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;
                } else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuilder();
                } else if (ch == '\r') {
                    // ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    // the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}
