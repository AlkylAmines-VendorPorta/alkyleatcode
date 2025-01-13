
package com.sap.document.sap.rfc.ses;

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
 *         &lt;element name="PURCHASENO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="RETURN" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_BAPIRET2"/>
 *         &lt;element name="YENTRYSHEET" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_YENTRYSHEET"/>
 *         &lt;element name="YSERVICE" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_YSERVICE"/>
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
    "purchaseno",
    "_return",
    "yentrysheet",
    "yservice"
})
@XmlRootElement(name = "ZMM_SERVICEENTRY_PORTAL")
public class ZMMSERVICEENTRYPORTAL_Type {

    @XmlElement(name = "PURCHASENO", required = true)
    protected String purchaseno;
    @XmlElement(name = "RETURN", required = true)
    protected TABLEOFBAPIRET2 _return;
    @XmlElement(name = "YENTRYSHEET", required = true)
    protected TABLEOFYENTRYSHEET yentrysheet;
    @XmlElement(name = "YSERVICE", required = true)
    protected TABLEOFYSERVICE yservice;

    /**
     * Gets the value of the purchaseno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPURCHASENO() {
        return purchaseno;
    }

    /**
     * Sets the value of the purchaseno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPURCHASENO(String value) {
        this.purchaseno = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFBAPIRET2 }
     *     
     */
    public TABLEOFBAPIRET2 getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFBAPIRET2 }
     *     
     */
    public void setRETURN(TABLEOFBAPIRET2 value) {
        this._return = value;
    }

    /**
     * Gets the value of the yentrysheet property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFYENTRYSHEET }
     *     
     */
    public TABLEOFYENTRYSHEET getYENTRYSHEET() {
        return yentrysheet;
    }

    /**
     * Sets the value of the yentrysheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFYENTRYSHEET }
     *     
     */
    public void setYENTRYSHEET(TABLEOFYENTRYSHEET value) {
        this.yentrysheet = value;
    }

    /**
     * Gets the value of the yservice property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFYSERVICE }
     *     
     */
    public TABLEOFYSERVICE getYSERVICE() {
        return yservice;
    }

    /**
     * Sets the value of the yservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFYSERVICE }
     *     
     */
    public void setYSERVICE(TABLEOFYSERVICE value) {
        this.yservice = value;
    }

}
