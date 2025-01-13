
package com.sap.document.sap.rfc.functions;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for ZVENDOR_PORTAL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZVENDOR_PORTAL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NAME1" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="LIFNR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="STREET" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="STREET2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="STREET3" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="ORT01" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="PSTLZ" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="REGIO" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="LAND1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="TITLE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="TELF1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="TELF2" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="TEL_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="TELFX" type="{urn:sap-com:document:sap:rfc:functions}char31"/>
 *         &lt;element name="SMTP_ADDR" type="{urn:sap-com:document:sap:rfc:functions}char241"/>
 *         &lt;element name="BANKL" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="BANKA" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="BANKN" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="KOINH" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="BRNCH" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="PROVZ" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="J_1IPANNO" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="STCD1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="BUKRS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="EKORG" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="KTOKK" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="AKONT" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="KALSK" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="EKGRP" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="WAERS" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="ZTERM" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="INCO1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="SPERZ" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlType(name = "ZVENDOR_PORTAL", propOrder = {
    "name1",
    "lifnr",
    "street",
    "street2",
    "street3",
    "ort01",
    "pstlz",
    "regio",
    "land1",
    "title",
    "telf1",
    "telf2",
    "telnumber",
    "telfx",
    "smtpaddr",
    "bankl",
    "banka",
    "bankn",
    "koinh",
    "brnch",
    "provz",
    "j1IPANNO",
    "stcd1",
    "bukrs",
    "ekorg",
    "ktokk",
    "akont",
    "kalsk",
    "ekgrp",
    "waers",
    "zterm",
    "inco1",
    "inco2",
    "sperz",
    "ismsme",
    "msmeno",
    "user",
    "brsch",
    "msmeType"
    
})
public class ZVENDORPORTAL_Type {

    @XmlElement(name = "NAME1", required = true)
    protected String name1;
    @XmlElement(name = "LIFNR", required = true)
    protected String lifnr;
    @XmlElement(name = "STREET", required = true)
    protected String street;
    @XmlElement(name = "STREET2", required = true)
    protected String street2;
    @XmlElement(name = "STREET3", required = true)
    protected String street3;
    @XmlElement(name = "ORT01", required = true)
    protected String ort01;
    @XmlElement(name = "PSTLZ", required = true)
    protected String pstlz;
    @XmlElement(name = "REGIO", required = true)
    protected String regio;
    @XmlElement(name = "LAND1", required = true)
    protected String land1;
    @XmlElement(name = "TITLE", required = true)
    protected String title;
    @XmlElement(name = "TELF1", required = true)
    protected String telf1;
    @XmlElement(name = "TELF2", required = true)
    protected String telf2;
    @XmlElement(name = "TEL_NUMBER", required = true)
    protected String telnumber;
    @XmlElement(name = "TELFX", required = true)
    protected String telfx;
    @XmlElement(name = "SMTP_ADDR", required = true)
    protected String smtpaddr;
    @XmlElement(name = "BANKL", required = true)
    protected String bankl;
    @XmlElement(name = "BANKA", required = true)
    protected String banka;
    @XmlElement(name = "BANKN", required = true)
    protected String bankn;
    @XmlElement(name = "KOINH", required = true)
    protected String koinh;
    @XmlElement(name = "BRNCH", required = true)
    protected String brnch;
    @XmlElement(name = "PROVZ", required = true)
    protected String provz;
    @XmlElement(name = "J_1IPANNO", required = true)
    protected String j1IPANNO;
    @XmlElement(name = "STCD1", required = true)
    protected String stcd1;
    @XmlElement(name = "BUKRS", required = true)
    protected String bukrs;
    @XmlElement(name = "EKORG", required = true)
    protected String ekorg;
    @XmlElement(name = "KTOKK", required = true)
    protected String ktokk;
    @XmlElement(name = "AKONT", required = true)
    protected String akont;
    @XmlElement(name = "KALSK", required = true)
    protected String kalsk;
    @XmlElement(name = "EKGRP", required = true)
    protected String ekgrp;
    @XmlElement(name = "WAERS", required = true)
    protected String waers;
    @XmlElement(name = "ZTERM", required = true)
    protected String zterm;
    @XmlElement(name = "INCO1", required = true)
    protected String inco1;
    @XmlElement(name = "INCO2", required = true)
    protected String inco2;
    @XmlElement(name = "SPERZ", required = true)
    protected String sperz;
    @XmlElement(name = "ISMSME", required = false)
    protected String ismsme;
    @XmlElement(name = "MSMEType", required = false)
    protected String msmeType;
    @XmlElement(name = "MSMENO", required = false)
    protected String msmeno;
    @XmlElement(name = "ZVendor_User", required = false)
    protected List<ZVendorUser_Type> user;
   /* @XmlElement(name = "Vendor_Code", required = false)
    protected String vendorCode;*/
    
    @XmlElement(name = "BRSCH", required = false)
    @JsonIgnoreProperties(ignoreUnknown=true)
    protected String brsch;
   
    /**
     * Gets the value of the name1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME1() {
        return name1;
    }

    /**
     * Sets the value of the name1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME1(String value) {
        this.name1 = value;
    }

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
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTREET() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTREET(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the street2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTREET2() {
        return street2;
    }

    /**
     * Sets the value of the street2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTREET2(String value) {
        this.street2 = value;
    }

    /**
     * Gets the value of the street3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTREET3() {
        return street3;
    }

    /**
     * Sets the value of the street3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTREET3(String value) {
        this.street3 = value;
    }

    /**
     * Gets the value of the ort01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORT01() {
        return ort01;
    }

    /**
     * Sets the value of the ort01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORT01(String value) {
        this.ort01 = value;
    }

    /**
     * Gets the value of the pstlz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSTLZ() {
        return pstlz;
    }

    /**
     * Sets the value of the pstlz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSTLZ(String value) {
        this.pstlz = value;
    }

    /**
     * Gets the value of the regio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGIO() {
        return regio;
    }

    /**
     * Sets the value of the regio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGIO(String value) {
        this.regio = value;
    }

    /**
     * Gets the value of the land1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLAND1() {
        return land1;
    }

    /**
     * Sets the value of the land1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLAND1(String value) {
        this.land1 = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITLE() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITLE(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the telf1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELF1() {
        return telf1;
    }

    /**
     * Sets the value of the telf1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELF1(String value) {
        this.telf1 = value;
    }

    /**
     * Gets the value of the telf2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELF2() {
        return telf2;
    }

    /**
     * Sets the value of the telf2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELF2(String value) {
        this.telf2 = value;
    }

    /**
     * Gets the value of the telnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELNUMBER() {
        return telnumber;
    }

    /**
     * Sets the value of the telnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELNUMBER(String value) {
        this.telnumber = value;
    }

    /**
     * Gets the value of the telfx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELFX() {
        return telfx;
    }

    /**
     * Sets the value of the telfx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELFX(String value) {
        this.telfx = value;
    }

    /**
     * Gets the value of the smtpaddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMTPADDR() {
        return smtpaddr;
    }

    /**
     * Sets the value of the smtpaddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMTPADDR(String value) {
        this.smtpaddr = value;
    }

    /**
     * Gets the value of the bankl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANKL() {
        return bankl;
    }

    /**
     * Sets the value of the bankl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANKL(String value) {
        this.bankl = value;
    }

    /**
     * Gets the value of the banka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANKA() {
        return banka;
    }

    /**
     * Sets the value of the banka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANKA(String value) {
        this.banka = value;
    }

    /**
     * Gets the value of the bankn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANKN() {
        return bankn;
    }

    /**
     * Sets the value of the bankn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANKN(String value) {
        this.bankn = value;
    }

    /**
     * Gets the value of the koinh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKOINH() {
        return koinh;
    }

    /**
     * Sets the value of the koinh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKOINH(String value) {
        this.koinh = value;
    }

    /**
     * Gets the value of the brnch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBRNCH() {
        return brnch;
    }

    /**
     * Sets the value of the brnch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBRNCH(String value) {
        this.brnch = value;
    }

    /**
     * Gets the value of the provz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVZ() {
        return provz;
    }

    /**
     * Sets the value of the provz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVZ(String value) {
        this.provz = value;
    }

    /**
     * Gets the value of the j1IPANNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJ1IPANNO() {
        return j1IPANNO;
    }

    /**
     * Sets the value of the j1IPANNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJ1IPANNO(String value) {
        this.j1IPANNO = value;
    }

    /**
     * Gets the value of the stcd1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTCD1() {
        return stcd1;
    }

    /**
     * Sets the value of the stcd1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTCD1(String value) {
        this.stcd1 = value;
    }

    /**
     * Gets the value of the bukrs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUKRS() {
        return bukrs;
    }

    /**
     * Sets the value of the bukrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUKRS(String value) {
        this.bukrs = value;
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
     * Gets the value of the ktokk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKTOKK() {
        return ktokk;
    }

    /**
     * Sets the value of the ktokk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKTOKK(String value) {
        this.ktokk = value;
    }

    /**
     * Gets the value of the akont property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAKONT() {
        return akont;
    }

    /**
     * Sets the value of the akont property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAKONT(String value) {
        this.akont = value;
    }

    /**
     * Gets the value of the kalsk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKALSK() {
        return kalsk;
    }

    /**
     * Sets the value of the kalsk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKALSK(String value) {
        this.kalsk = value;
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
     * Gets the value of the waers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWAERS() {
        return waers;
    }

    /**
     * Sets the value of the waers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWAERS(String value) {
        this.waers = value;
    }

    /**
     * Gets the value of the zterm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZTERM() {
        return zterm;
    }

    /**
     * Sets the value of the zterm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZTERM(String value) {
        this.zterm = value;
    }

    /**
     * Gets the value of the inco1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCO1() {
        return inco1;
    }

    /**
     * Sets the value of the inco1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCO1(String value) {
        this.inco1 = value;
    }
    public String getInco2() {
		return inco2;
	}

	public void setInco2(String inco2) {
		this.inco2 = inco2;
	}

    /**
     * Gets the value of the sperz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPERZ() {
        return sperz;
    }

    /**
     * Sets the value of the sperz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPERZ(String value) {
        this.sperz = value;
    }

	public String getIsmsme() {
		return ismsme;
	}

	public void setIsmsme(String ismsme) {
		this.ismsme = ismsme;
	}

	public String getMsmeno() {
		return msmeno;
	}

	public void setMsmeno(String msmeno) {
		this.msmeno = msmeno;
	}

	public List<ZVendorUser_Type> getUser() {
		return user;
	}

	public void setUser(List<ZVendorUser_Type> user) {
		this.user = user;
	}

	public String getBrsch() {
		return brsch;
	}

	public void setBrsch(String brsch) {
		this.brsch = brsch;
	}



	public String getMsmeType() {
		return msmeType;
	}

	public void setMsmeType(String msmeType) {
		this.msmeType = msmeType;
	}

	@Override
	public String toString() {
		return "ZVENDORPORTAL_Type [name1=" + name1 + ", lifnr=" + lifnr + ", street=" + street + ", street2=" + street2
				+ ", street3=" + street3 + ", ort01=" + ort01 + ", pstlz=" + pstlz + ", regio=" + regio + ", land1="
				+ land1 + ", title=" + title + ", telf1=" + telf1 + ", telf2=" + telf2 + ", telnumber=" + telnumber
				+ ", telfx=" + telfx + ", smtpaddr=" + smtpaddr + ", bankl=" + bankl + ", banka=" + banka + ", bankn="
				+ bankn + ", koinh=" + koinh + ", brnch=" + brnch + ", provz=" + provz + ", j1IPANNO=" + j1IPANNO
				+ ", stcd1=" + stcd1 + ", bukrs=" + bukrs + ", ekorg=" + ekorg + ", ktokk=" + ktokk + ", akont=" + akont
				+ ", kalsk=" + kalsk + ", ekgrp=" + ekgrp + ", waers=" + waers + ", zterm=" + zterm + ", inco1=" + inco1
				+ ", inco2=" + inco2 + ", sperz=" + sperz + ", ismsme=" + ismsme + ", msmeType=" + msmeType
				+ ", msmeno=" + msmeno + ", user=" + user + ", brsch=" + brsch + "]";
	}

//	@Override
//	public String toString() {
//		return "ZVENDORPORTAL_Type [name1=" + name1 + ", lifnr=" + lifnr + ", street=" + street + ", street2=" + street2
//				+ ", street3=" + street3 + ", ort01=" + ort01 + ", pstlz=" + pstlz + ", regio=" + regio + ", land1="
//				+ land1 + ", title=" + title + ", telf1=" + telf1 + ", telf2=" + telf2 + ", telnumber=" + telnumber
//				+ ", telfx=" + telfx + ", smtpaddr=" + smtpaddr + ", bankl=" + bankl + ", banka=" + banka + ", bankn="
//				+ bankn + ", koinh=" + koinh + ", brnch=" + brnch + ", provz=" + provz + ", j1IPANNO=" + j1IPANNO
//				+ ", stcd1=" + stcd1 + ", bukrs=" + bukrs + ", ekorg=" + ekorg + ", ktokk=" + ktokk + ", akont=" + akont
//				+ ", kalsk=" + kalsk + ", ekgrp=" + ekgrp + ", waers=" + waers + ", zterm=" + zterm + ", inco1=" + inco1
//				+ ", inco2=" + inco2 + ", sperz=" + sperz + ", ismsme=" + ismsme + ", msmeno=" + msmeno + ", user="
//				+ user + ", brsch=" + brsch + "]";
//	}

	

	
}
