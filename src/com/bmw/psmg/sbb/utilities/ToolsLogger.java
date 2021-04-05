package com.bmw.psmg.sbb.utilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import wt.util.WTProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nonnull;

/**
 * The Class ToolsLogger. Custom logger should be used for custom tools, like extension tools. Output is stored in separated file.
 */
public final class ToolsLogger extends PrintWriter {

    private static final Logger LOG = Logger.getLogger(ToolsLogger.class.getSimpleName());
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH-mm-ss";
    private static final String FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH-mm-ss.SSS";
    private static final String TOOLS_LOG_FILE_DIRECTORY;
    private static final String WT_HOME;
    private static final char DOT = '.';
    private static final String FILE_EXTENSION = "log";
    private static WTProperties properties;
    private static boolean logToConsole = false;
    private static String logsDir;

    static {
        try {
            properties = new WTProperties(WTProperties.getLocalProperties());
        } catch (IOException e) {
            LOG.error("Error occurred during initializing property variable.", e);
        }
        WT_HOME = properties.getProperty("wt.home");
        TOOLS_LOG_FILE_DIRECTORY = WT_HOME + File.separator + "logs" + File.separator + "BMWToolLogs" + File.separator;
        logsDir = TOOLS_LOG_FILE_DIRECTORY;
    }

    private ToolsLogger(OutputStreamWriter outputStreamWriter, boolean append) {
        super(outputStreamWriter, append);
    }

    /**
     * Instantiates a new tools logger.
     *
     * @param fileName      the file name
     * @param addTimestamp  the add timestamp
     * @param folderName    the folder name
     * @param fileExtension the file extension
     * @return ToolsLogger instance
     * @throws FileNotFoundException file not found
     */
    private static synchronized ToolsLogger initializeInstance(String fileName, boolean addTimestamp, boolean logToConsole, String folderName, boolean addTimestampToFolderName, String fileExtension)
            throws FileNotFoundException {
        if (StringUtils.isBlank(fileName)) {
            LOG.error("fileName needs to be provided!");
        }

        ToolsLogger.logToConsole = logToConsole;

        StringBuilder filePath = new StringBuilder();
        filePath.append(TOOLS_LOG_FILE_DIRECTORY);

        if (StringUtils.isNotBlank(folderName)) {
            filePath.append(folderName.trim());
            if (addTimestampToFolderName) {
                addTimestamp(filePath);
            }
            filePath.append(File.separator);
        }

        logsDir = filePath.toString();

        filePath.append(fileName);

        if (addTimestamp) {
            addTimestamp(filePath);
        }

        filePath.append(DOT);
        if (StringUtils.isBlank(fileExtension)) {
            filePath.append(ToolsLogger.FILE_EXTENSION);
        } else {
            filePath.append(fileExtension);
        }

        File logFile = new File(filePath.toString());
        File parentDir = logFile.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new FileNotFoundException("Cannot create fallowing directory " + parentDir.getAbsolutePath());
        }

        ToolsLogger writer = new ToolsLogger(new OutputStreamWriter(new FileOutputStream(logFile), StandardCharsets.UTF_8), true);

        LOG.setLevel(Level.INFO);
        LOG.info("Logs output will be stored in file " + filePath);
        return writer;
    }

    /**
     * Instantiates a new tools logger.
     *
     * @param absolutePath the absolute path
     * @return the tools logger
     * @throws FileNotFoundException the file not found exception
     */
    public static ToolsLogger initializeInstanceByFilePath(String absolutePath) throws FileNotFoundException {
        File logFile = new File(absolutePath);
        File parentDir = logFile.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new FileNotFoundException("Cannot create fallowing directory " + parentDir.getAbsolutePath());
        }

        ToolsLogger writer = new ToolsLogger(new OutputStreamWriter(new FileOutputStream(logFile), StandardCharsets.UTF_8), true);

        LOG.setLevel(Level.INFO);
        LOG.info("Logs output will be stored in file " + absolutePath);
        return writer;
    }

    /**
     * Sets the up instance.
     *
     * @param fileName the file name
     * @return the tools logger
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static ToolsLogger setUpInstance(String fileName) throws IOException {
        return setUpInstance(fileName, false, logToConsole);
    }

    /**
     * Sets the up instance.
     *
     * @param fileName     the file name
     * @param addTimestamp add timestamp to the fileName
     * @return the tools logger
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static ToolsLogger setUpInstance(String fileName, boolean addTimestamp) throws IOException {
        return setUpInstance(fileName, addTimestamp, logToConsole, "", true);
    }

    /**
     * Sets the up instance.
     *
     * @param fileName     the file name
     * @param addTimestamp add timestamp to the fileName
     * @param logToConsole the logs will be showed up in ms log too
     * @return the tools logger
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static ToolsLogger setUpInstance(String fileName, boolean addTimestamp, boolean logToConsole) throws IOException {
        return setUpInstance(fileName, addTimestamp, logToConsole, "", true);
    }

    public static ToolsLogger setUpInstance(String fileName, boolean addTimestamp, boolean logToConsole, String folderName) throws IOException {
        return setUpInstance(fileName, addTimestamp, logToConsole, folderName, true, "");
    }

    /**
     * Sets the up instance.
     *
     * @param fileName                 the file name
     * @param addTimestamp             add timestamp to the fileName
     * @param logToConsole             the logs will be showed up in ms log too
     * @param folderName               the folder name in BMWToolLogs directory
     * @param addTimestampToFolderName tadd timestamp to the folderName
     * @return the tools logger
     * @throws FileNotFoundException file not found exception
     */
    public static ToolsLogger setUpInstance(String fileName, boolean addTimestamp, boolean logToConsole, String folderName, boolean addTimestampToFolderName)
            throws FileNotFoundException {
        return setUpInstance(fileName, addTimestamp, logToConsole, folderName, addTimestampToFolderName, "");
    }

    /**
     * Sets the up instance.
     *
     * @param fileName      the file name (required)
     * @param addTimestamp  add timestamp to the fileName (default false)
     * @param logToConsole  the logs will be showed up in ms log too
     * @param folderName    the folder name in BMWToolLogs directory (default "")
     * @param fileExtension the custom extension for file (default "log")
     * @return the tools logger
     * @throws FileNotFoundException file not found exception
     */
    public static ToolsLogger setUpInstance(String fileName, boolean addTimestamp, boolean logToConsole, String folderName, boolean addTimestampToFolderName, String fileExtension)
            throws FileNotFoundException {
        return initializeInstance(fileName, addTimestamp, logToConsole, folderName, addTimestampToFolderName, fileExtension);
    }

    /**
     * Log to console.
     *
     * @param x the text which will be showed in ms log
     */
    private static void isLogToConsole(String x) {
        if (logToConsole) {
            LOG.info(x);
        }
    }

    /**
     * Adds the timestamp.
     *
     * @param fileName the file name
     * @return the string builder
     */
    private static StringBuilder addTimestamp(StringBuilder fileName) {
        String date = getSimpleTimestamp();
        return fileName.append("_").append(date);
    }

    public static String getSimpleTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        return dateFormat.format(new Date());
    }

    public static String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DATE_FORMAT);
        return dateFormat.format(new Date());
    }

    public static String getLogPath() {
        return logsDir;
    }

    /**
     * Prints a string. If the argument is null then the string "null" is printed. Otherwise, the string's characters are converted into bytes according to the
     * platform's default character encoding, and these bytes are written in exactly the manner of the write(int) method.
     *
     * @param s The String to be printed
     */
    @Override
    public void print(String s) {
        super.print(s);
        isLogToConsole(s);
    }

    /**
     * Prints a string to file and console. If the argument is null then the string "null" is printed. Otherwise, the string's characters are converted into
     * bytes according to the platform's default character encoding, and these bytes are written in exactly the manner of the write(int) method.
     *
     * @param s The String to be printed
     */
    public void printToConsole(String s) {
        super.print(s);
        LOG.info(s);
    }

    /**
     * Writes a string. This method cannot be inherited from the Writer class because it must suppress I/O exceptions.
     *
     * @param s String to be written
     */
    @Override
    public void write(String s) {
        super.write(s);
        isLogToConsole(s);
    }

    /**
     * Writes a string to file and console. This method cannot be inherited from the Writer class because it must suppress I/O exceptions.
     *
     * @param s String to be written
     */
    public void writeToConsole(String s) {
        super.write(s);
        LOG.info(s);
    }

    /**
     * Prints a String to file and console, then terminates the line. This method behaves as though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x the <code>String</code> value to be printed
     */
    @Override
    public void println(String x) {
        super.println(getTimestamp() + " " + x);
        isLogToConsole(x);
    }

    /**
     * Prints a String to file and console, then terminates the line and flush.
     *
     * @param x the <code>String</code> value to be printed
     */
    public synchronized void printlnFlush(String x) {
        super.println(x);
        isLogToConsole(x);
        flush();
    }

    /**
     * Prints a String to file and console, then terminates the line and flush.
     *
     * @param x the <code>String</code> value to be printed
     */
    public synchronized void printFlush(String x) {
        super.print(x);
        isLogToConsole(x);
        flush();
    }

    /**
     * Prints a String to file and console and then terminates the line. This method behaves as though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x the <code>String</code> value to be printed
     */
    public void printlnToConsole(String x) {
        super.println(x);
        LOG.info(x);
    }

    /**
     * A convenience method to write a formatted string to this writer using the specified format string and arguments. If automatic flushing is enabled, calls
     * to this method will flush the output buffer.
     * <p>
     * An invocation of this method of the form out.printf(format, args) behaves in exactly the same way as the invocation
     * <p>
     * out.format(format, args)
     *
     * @param format A format string as described in Format string syntax.
     * @param args   Arguments referenced by the format specifiers in the format string. If there are more arguments than format specifiers, the extra arguments
     *               are ignored. The number of arguments is variable and may be zero. The maximum number of arguments is limited by the maximum dimension of a Java
     *               array as defined by The Java™ Virtual Machine Specification. The behaviour on a null argument depends on the conversion.
     * @return PrintWriter
     */
    @Override
    public PrintWriter printf(@Nonnull String format, Object... args) {
        isLogToConsole(String.format(format, args));
        return super.printf(format, args);
    }

}
