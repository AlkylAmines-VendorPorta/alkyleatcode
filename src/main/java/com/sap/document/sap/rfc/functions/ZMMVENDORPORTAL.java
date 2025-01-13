
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INPUT" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_ZVENDOR_PORTAL"/>
 *         &lt;element name="OUTPUT" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_ZVENDOR_PORTAL"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "ZMM_VENDOR_PORTAL")
public class ZMMVENDORPORTAL {

    @XmlElement(name = "INPUT", required = true)
    protected TABLEOFZVENDORPORTAL input;
    @XmlElement(name = "OUTPUT", required = true)
    protected TABLEOFZVENDORPORTAL output;

    /**
     * Gets the value of the input property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFZVENDORPORTAL }
     *     
     */
    public TABLEOFZVENDORPORTAL getINPUT() {
        return input;
    }

    /**
     * Sets the value of the input property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFZVENDORPORTAL }
     *     
     */
    public void setINPUT(TABLEOFZVENDORPORTAL value) {
        this.input = value;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFZVENDORPORTAL }
     *     
     */
    public TABLEOFZVENDORPORTAL getOUTPUT() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFZVENDORPORTAL }
     *     
     */
    public void setOUTPUT(TABLEOFZVENDORPORTAL value) {
        this.output = value;
    }

}
