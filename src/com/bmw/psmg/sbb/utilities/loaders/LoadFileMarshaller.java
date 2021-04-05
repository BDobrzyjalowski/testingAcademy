package com.bmw.psmg.sbb.utilities.loaders;

import org.apache.log4j.Logger;
import wt.jmx.core.MBeanUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * The LoadFileMarshaller class is responsible for creating XML file adjust to Windchill Load From FIle framework.
 * This class use JAXB Marshaller for correct working.
 *
 * @author Karol Poliszkiewicz
 */
public class LoadFileMarshaller {

    private static final Logger LOGGER = Logger.getLogger(LoadFileMarshaller.class.getName());

    private static final String XML_DECLARATION_PROPERTY = "com.sun.xml.bind.xmlDeclaration";
    private static final String XML_HEADERS_PROPERTY = "com.sun.xml.bind.xmlHeaders";
    private static final String XML_DOCTYPE = "\n<!DOCTYPE NmLoader SYSTEM \"standard11_1.dtd\">";
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + XML_DOCTYPE;

    private static final String OUTPUT_FOLDER = new File(MBeanUtilities.getProperties().getProperty("wt.home")).getAbsolutePath() + File.separator + "tmp";
    private static final String OUTPUT_EXTENSION = ".xml";

    /**
     * A method generate and save Windchill LoadFile XML file using JAXB framework.
     *
     * @param fileName    - name of target XML file
     * @param jaxbContext - JAXBContext to map Java Pojo into XML
     * @param toMarshal   - a object which will be marshaller
     * @return an absolute path of created XML file or empty String when throws saving error
     * @throws JAXBException when cannot create Marshaller
     */
    public String marshal(String fileName, JAXBContext jaxbContext, Object toMarshal) throws JAXBException {
        String output = "";
        if (fileName != null && !fileName.isEmpty()) {
            fileName = adjustFilename(fileName);
            try (FileOutputStream stream = new FileOutputStream(fileName)) {
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(XML_DECLARATION_PROPERTY, false);
                jaxbMarshaller.setProperty(XML_HEADERS_PROPERTY, XML_HEADER);
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(toMarshal, stream);
                output = new File(fileName).getAbsolutePath();
            } catch (IOException e) {
                LOGGER.error("Error with output file: " + e.getLocalizedMessage(), e);
            }
        }
        return output;
    }

    private String adjustFilename(String fileName) {
        return OUTPUT_FOLDER + File.separator + fileName.replaceAll("[.^:, ]", "_") + OUTPUT_EXTENSION;
    }

}
