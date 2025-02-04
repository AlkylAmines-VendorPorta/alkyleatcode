//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.28 at 12:03:59 PM IST 
//


package com.novelerp.eat.msedcl.createContract;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZMM_CONTRACT_HEADER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZMM_CONTRACT_HEADER"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LIFNR" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="EVART" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="EKORG" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="EKGRP" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="WERKS" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="LGORT" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="KNTTP" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="KDATB" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="KDATE" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="KTWRT" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="ZZBG_ZSEC_DEP" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="ZZBG_ZPFM_DEP" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZMM_CONTRACT_HEADER", propOrder = {
    "lifnr",
    "evart",
    "ekorg",
    "ekgrp",
    "werks",
    "lgort",
    "knttp",
    "kdatb",
    "kdate",
    "ktwrt",
    "zzbgzsecdep",
    "zzbgzpfmdep"
})
public class ZMMCONTRACTHEADER {

    @XmlElement(name = "LIFNR", required = true)
    protected String lifnr;
    @XmlElement(name = "EVART", required = true)
    protected String evart;
    @XmlElement(name = "EKORG", required = true)
    protected String ekorg;
    @XmlElement(name = "EKGRP", required = true)
    protected String ekgrp;
    @XmlElement(name = "WERKS", required = true)
    protected String werks;
    @XmlElement(name = "LGORT", required = true)
    protected String lgort;
    @XmlElement(name = "KNTTP", required = true)
    protected String knttp;
    @XmlElement(name = "KDATB", required = true)
    protected String kdatb;
    @XmlElement(name = "KDATE", required = true)
    protected String kdate;
    @XmlElement(name = "KTWRT", required = true)
    protected BigDecimal ktwrt;
    @XmlElement(name = "ZZBGZSEC_DEP", required = true)
    protected String zzbgzsecdep;
    @XmlElement(name = "ZZBG_ZPFM_DEP", required = true)
    protected String zzbgzpfmdep;

    /**
     * Gets the value of the lifnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLIFNR() {
        return lifnr;
    }

    /**
     * Sets the value of the lifnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIFNR(String value) {
        this.lifnr = value;
    }

    /**
     * Gets the value of the evart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEVART() {
        return evart;
    }

    /**
     * Sets the value of the evart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEVART(String value) {
        this.evart = value;
    }

    /**
     * Gets the value of the ekorg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKORG() {
        return ekorg;
    }

    /**
     * Sets the value of the ekorg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKORG(String value) {
        this.ekorg = value;
    }

    /**
     * Gets the value of the ekgrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKGRP() {
        return ekgrp;
    }

    /**
     * Sets the value of the ekgrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKGRP(String value) {
        this.ekgrp = value;
    }

    /**
     * Gets the value of the werks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWERKS() {
        return werks;
    }

    /**
     * Sets the value of the werks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWERKS(String value) {
        this.werks = value;
    }

    /**
     * Gets the value of the lgort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLGORT() {
        return lgort;
    }

    /**
     * Sets the value of the lgort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLGORT(String value) {
        this.lgort = value;
    }

    /**
     * Gets the value of the knttp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKNTTP() {
        return knttp;
    }

    /**
     * Sets the value of the knttp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKNTTP(String value) {
        this.knttp = value;
    }

    /**
     * Gets the value of the kdatb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKDATB() {
        return kdatb;
    }

    /**
     * Sets the value of the kdatb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKDATB(String value) {
        this.kdatb = value;
    }

    /**
     * Gets the value of the kdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKDATE() {
        return kdate;
    }

    /**
     * Sets the value of the kdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKDATE(String value) {
        this.kdate = value;
    }

    /**
     * Gets the value of the ktwrt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKTWRT() {
        return ktwrt;
    }

    /**
     * Sets the value of the ktwrt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKTWRT(BigDecimal value) {
        this.ktwrt = value;
    }

    /**
     * Gets the value of the zzbgzsecdep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZBGZSECDEP() {
        return zzbgzsecdep;
    }

    /**
     * Sets the value of the zzbgzsecdep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZBGZSECDEP(String value) {
        this.zzbgzsecdep = value;
    }

    /**
     * Gets the value of the zzbgzpfmdep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZBGZPFMDEP() {
        return zzbgzpfmdep;
    }

    /**
     * Sets the value of the zzbgzpfmdep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZBGZPFMDEP(String value) {
        this.zzbgzpfmdep = value;
    }

}
