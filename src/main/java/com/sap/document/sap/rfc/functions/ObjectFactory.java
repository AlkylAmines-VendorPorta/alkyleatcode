
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.springframework.stereotype.Component;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sap.document.sap.rfc.functions package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
@Component
public class ObjectFactory {

    private final static QName _ZMMVENDORPORTALException_QNAME = new QName("urn:sap-com:document:sap:rfc:functions", "ZMM_VENDOR_PORTAL.Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sap.document.sap.rfc.functions
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZMMVENDORPORTAL }
     * 
     */
    public ZMMVENDORPORTAL createZMMVENDORPORTAL() {
        return new ZMMVENDORPORTAL();
    }

    /**
     * Create an instance of {@link TABLEOFZVENDORPORTAL }
     * 
     */
    public TABLEOFZVENDORPORTAL createTABLEOFZVENDORPORTAL() {
        return new TABLEOFZVENDORPORTAL();
    }

    /**
     * Create an instance of {@link ZMMVENDORPORTALRfcException }
     * 
     */
    public ZMMVENDORPORTALRfcException createZMMVENDORPORTALRfcException() {
        return new ZMMVENDORPORTALRfcException();
    }

    /**
     * Create an instance of {@link ZMMVENDORPORTALResponse }
     * 
     */
    public ZMMVENDORPORTALResponse createZMMVENDORPORTALResponse() {
        return new ZMMVENDORPORTALResponse();
    }

    /**
     * Create an instance of {@link ZVENDORPORTAL_Type }
     * 
     */
    public ZVENDORPORTAL_Type createZVENDORPORTAL_Type() {
        return new ZVENDORPORTAL_Type();
    }

    /**
     * Create an instance of {@link RfcExceptionMessage }
     * 
     */
    public RfcExceptionMessage createRfcExceptionMessage() {
        return new RfcExceptionMessage();
    }
    
    public ZQCFAnnexute_Type createZQCFAnnexute_Type() {
        return new ZQCFAnnexute_Type();
    }
    public ZQCFVENDOR_Type createZQCFVENDOR_Type() {
        return new ZQCFVENDOR_Type();
    }
    public ZQCFRFQLINE_Type createZQCFRFQLINE_Type() {
        return new ZQCFRFQLINE_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZMMVENDORPORTALRfcException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sap-com:document:sap:rfc:functions", name = "ZMM_VENDOR_PORTAL.Exception")
    public JAXBElement<ZMMVENDORPORTALRfcException> createZMMVENDORPORTALException(ZMMVENDORPORTALRfcException value) {
        return new JAXBElement<ZMMVENDORPORTALRfcException>(_ZMMVENDORPORTALException_QNAME, ZMMVENDORPORTALRfcException.class, null, value);
    }

}
