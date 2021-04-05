package com.bmw.psmg.sbb.utilities.loaders.folder;

import com.bmw.psmg.sbb.utilities.loaders.folder.readers.FolderStructureExcelParser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import wt.method.RemoteMethodServer;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;

/**
 * Class FolderStructureGeneratorTool. Used for execution of xml generation from command line
 *
 * @author Pawel Miron
 */
public class FolderStructureExcelToLoaderXml {

    private static final String GENERATOR_TARGET_METHOD_NAME = "generateXMLLoadFile";
    private static final String USERNAME_OPTION = "u";
    private static final String PASS_OPTION = "p";
    private static final String XML_LOCATION_OUTPUT = "out";
    private static final String EXCEL_LOCATION_INPUT = "in";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ParseException            the parse exception
     * @throws InvocationTargetException the invocation target exception
     * @throws RemoteException           the remote exception
     */
    public static void main(String[] args) throws ParseException, InvocationTargetException, RemoteException {
        Options options = getCommandLineOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLineOptions = parser.parse(options, args);
        String username = commandLineOptions.getOptionValue(USERNAME_OPTION);
        String password = commandLineOptions.getOptionValue(PASS_OPTION);
        String xmlFileOutLocation = commandLineOptions.getOptionValue(XML_LOCATION_OUTPUT);
        String excelFileInLocation = commandLineOptions.getOptionValue(EXCEL_LOCATION_INPUT);
        checkAllAttributesSet(username, password, xmlFileOutLocation, excelFileInLocation);
        RemoteMethodServer rms = RemoteMethodServer.getDefault();
        Class<?>[] obj = new Class[]{String.class, String.class};
        Object[] params = new Object[]{excelFileInLocation, xmlFileOutLocation};
        rms.setUserName(username);
        rms.setPassword(password);
        rms.invoke(GENERATOR_TARGET_METHOD_NAME, FolderStructureExcelParser.class.getName(), null, obj, params);
    }

    /**
     * Methods for checking if all attributes are set before folders load file generation
     *
     * @param args list of attributes
     * @throws ParseException exception
     */
    private static void checkAllAttributesSet(String... args) throws ParseException {
        for (String string : args) {
            if (string == null) {
                throw new ParseException("Not all required parameters are set");
            }
        }
    }

    /**
     * Method for setting all command line options
     *
     * @return command line options
     */
    private static Options getCommandLineOptions() {
        Option usernameOption = new Option(USERNAME_OPTION, true, "username (required)");
        usernameOption.setRequired(true);
        usernameOption.setType(String.class);
        Option passwordOption = new Option(PASS_OPTION, true, "password (required)");
        passwordOption.setRequired(true);
        passwordOption.setType(String.class);
        Option xmlOutputLocationOption = new Option(XML_LOCATION_OUTPUT, true, "Output xml location (required)");
        passwordOption.setRequired(true);
        passwordOption.setType(String.class);
        Option excelInputLocationOption = new Option(EXCEL_LOCATION_INPUT, true, "Input excel location (required)");
        excelInputLocationOption.setRequired(true);
        excelInputLocationOption.setType(String.class);
        Options options = new Options();
        options.addOption(usernameOption);
        options.addOption(passwordOption);
        options.addOption(xmlOutputLocationOption);
        options.addOption(excelInputLocationOption);
        return options;
    }
}
