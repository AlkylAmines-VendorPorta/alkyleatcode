
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZMM_VENDOR_PORTAL.RfcExceptions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ZMM_VENDOR_PORTAL.RfcExceptions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ZMM_VENDOR_PORTAL.RfcExceptions")
@XmlEnum
public enum ZMMVENDORPORTALRfcExceptions {

    ERROR;

    public String value() {
        return name();
    }

    public static ZMMVENDORPORTALRfcExceptions fromValue(String v) {
        return valueOf(v);
    }

}
