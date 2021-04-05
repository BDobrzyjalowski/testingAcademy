package test.com.bmw.psmg.sbb.junit.parameter;

import static java.util.stream.Collectors.toList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * The reader simplify read test parameters from CSV file.
 *
 * @param <R> determine collection's object class
 * @author Karol Poliszkiewicz
 */
public class ParametersCsvReader<R> {

    public static final CSVFormat SEMICOLON_FORMAT = CSVFormat.newFormat(';');

    private static final Logger LOGGER = Logger.getLogger(ParametersCsvReader.class.getName());

    private final InputStream inputStream;
    private Function<CSVRecord, ? extends R> mapper;

    /**
     * Initialize reader.
     *
     * @param inputStream handler to CSV file
     */
    public ParametersCsvReader(InputStream inputStream) {
        LOGGER.debug("Initialize Parameters CSV Reader.");
        this.inputStream = inputStream;
    }

    /**
     * function to map CSVRecord into diamond Object.
     *
     * @param mapper is mapping function
     * @return this
     */
    public ParametersCsvReader<R> map(Function<CSVRecord, ? extends R> mapper) {
        LOGGER.debug("Set mapper function");
        this.mapper = mapper;
        return this;
    }

    /**
     * Method collect parameters from CSV file skipping comments.
     *
     * @return parameters for JUnit test
     */
    public Collection<R> read() {
        return read(CSVFormat.DEFAULT);
    }

    /**
     * Method collect parameters from CSV file in specify format skipping comments.
     *
     * @param csvFormat set csv format
     * @return parameters for JUnit test
     */
    public Collection<R> read(CSVFormat csvFormat) {
        LOGGER.debug("Starting read CSV file.");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try (CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            return csvParser.getRecords().stream().filter(strings -> !strings.hasComment()).peek(LOGGER::debug).map(mapper).collect(toList());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return List.of();
    }

    /**
     * Method automatically collect parameters from CSV file skipping comments.
     * The file is mapping to collection of template arrays.
     *
     * @return collection of parameters
     */
    public Collection<R[]> parse() {
        return parse(CSVFormat.DEFAULT);
    }

    /**
     * Method automatically collect parameters from CSV file in specify format skipping comments.
     * The file is mapping to collection of template arrays.
     *
     * @param csvFormat set csv format
     * @return collection of parameters
     */
    public Collection<R[]> parse(CSVFormat csvFormat) {
        LOGGER.debug("Starting read CSV file.");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try (CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            return csvParser.getRecords().stream().filter(strings -> !strings.hasComment()).map(this::recordToArray).collect(toList());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return List.of();
    }

    @SuppressWarnings("unchecked")
    private R[] recordToArray(CSVRecord record) {
        Object[] array = new Object[record.size()];
        int index = 0;
        for (String column : record) {
            array[index] = column;
            index++;
        }
        return (R[]) array;
    }
}
