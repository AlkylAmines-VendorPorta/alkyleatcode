
package com.sap.document.sap.rfc.grn;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ZMM_GRN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZMM_GRN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PSTNG_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="DOC_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="REF_DOC_NO" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="BILL_OF_LADING" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="GR_GI_SLIP_NO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PR_UNAME" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="HEADER_TXT" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="VER_GR_GI_SLIP" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="VER_GR_GI_SLIPX" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="EXT_WMS" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="REF_DOC_NO_LONG" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="BILL_OF_LADING_LONG" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="GATE_ENO" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="PONO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="EBELP" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="V_NO" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="T_COD" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="VEND_NAME" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="R_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="R_TIME" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="V_TYP" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="GIN_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="GIN_TIME" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="US_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="US_TIME" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="UE_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="UE_TIME" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="GO_TIME" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="GO_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="GR_WGHT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="TR_WGHT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="NT_WGHT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="MAKTX" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="BSART" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="STATUS" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PO_QTY" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="TEXT" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="GR_WGHT_UNIT" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="TR_WGHT_UNIT" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="NT_WGHT_UNIT" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="MBLNR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MJAHR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="WERKS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="LRNO" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="TP_LA_NUM" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="KNUMV" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="RATE" type="{urn:sap-com:document:sap:rfc:functions}curr11.2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZMM_GRN", propOrder = {
    "pstngdate",
    "docdate",
    "refdocno",
    "billoflading",
    "grgislipno",
    "pruname",
    "headertxt",
    "vergrgislip",
    "vergrgislipx",
    "extwms",
    "refdocnolong",
    "billofladinglong",
    "gateeno",
    "pono",
    "ebelp",
    "vno",
    "tcod",
    "vendname",
    "rdate",
    "rtime",
    "vtyp",
    "gindate",
    "gintime",
    "usdate",
    "ustime",
    "uedate",
    "uetime",
    "gotime",
    "godate",
    "grwght",
    "trwght",
    "ntwght",
    "matnr",
    "maktx",
    "bsart",
    "status",
    "poqty",
    "text",
    "grwghtunit",
    "trwghtunit",
    "ntwghtunit",
    "mblnr",
    "mjahr",
    "werks",
    "lrno",
    "tplanum",
    "knumv",
    "rate",
    "grndate"
})
public class ZMMGRN {

    @XmlElement(name = "PSTNG_DATE", required = true)
    protected String pstngdate;
    @XmlElement(name = "DOC_DATE", required = true)
    protected String docdate;
    @XmlElement(name = "REF_DOC_NO", required = true)
    protected String refdocno;
    @XmlElement(name = "BILL_OF_LADING", required = true)
    protected String billoflading;
    @XmlElement(name = "GR_GI_SLIP_NO", required = true)
    protected String grgislipno;
    @XmlElement(name = "PR_UNAME", required = true)
    protected String pruname;
    @XmlElement(name = "HEADER_TXT", required = true)
    protected String headertxt;
    @XmlElement(name = "VER_GR_GI_SLIP", required = true)
    protected String vergrgislip;
    @XmlElement(name = "VER_GR_GI_SLIPX", required = true)
    protected String vergrgislipx;
    @XmlElement(name = "EXT_WMS", required = true)
    protected String extwms;
    @XmlElement(name = "REF_DOC_NO_LONG", required = true)
    protected String refdocnolong;
    @XmlElement(name = "BILL_OF_LADING_LONG", required = true)
    protected String billofladinglong;
    @XmlElement(name = "GATE_ENO", required = true)
    protected String gateeno;
    @XmlElement(name = "PONO", required = true)
    protected String pono;
    @XmlElement(name = "EBELP", required = true)
    protected String ebelp;
    @XmlElement(name = "V_NO", required = true)
    protected String vno;
    @XmlElement(name = "T_COD", required = true)
    protected String tcod;
    @XmlElement(name = "VEND_NAME", required = true)
    protected String vendname;
    @XmlElement(name = "R_DATE", required = true)
    protected String rdate;
    @XmlElement(name = "R_TIME", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar rtime;
    @XmlElement(name = "V_TYP", required = true)
    protected String vtyp;
    @XmlElement(name = "GIN_DATE", required = true)
    protected String gindate;
    @XmlElement(name = "GIN_TIME", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar gintime;
    @XmlElement(name = "US_DATE", required = true)
    protected String usdate;
    @XmlElement(name = "US_TIME", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar ustime;
    @XmlElement(name = "UE_DATE", required = true)
    protected String uedate;
    @XmlElement(name = "UE_TIME", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar uetime;
    @XmlElement(name = "GO_TIME", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar gotime;
    @XmlElement(name = "GO_DATE", required = true)
    protected String godate;
    @XmlElement(name = "GR_WGHT", required = true)
    protected BigDecimal grwght;
    @XmlElement(name = "TR_WGHT", required = true)
    protected BigDecimal trwght;
    @XmlElement(name = "NT_WGHT", required = true)
    protected BigDecimal ntwght;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "MAKTX", required = true)
    protected String maktx;
    @XmlElement(name = "BSART", required = true)
    protected String bsart;
    @XmlElement(name = "STATUS", required = true)
    protected String status;
    @XmlElement(name = "PO_QTY", required = true)
    protected BigDecimal poqty;
    @XmlElement(name = "TEXT", required = true)
    protected String text;
    @XmlElement(name = "GR_WGHT_UNIT", required = true)
    protected String grwghtunit;
    @XmlElement(name = "TR_WGHT_UNIT", required = true)
    protected String trwghtunit;
    @XmlElement(name = "NT_WGHT_UNIT", required = true)
    protected String ntwghtunit;
    @XmlElement(name = "MBLNR", required = true)
    protected String mblnr;
    @XmlElement(name = "MJAHR", required = true)
    protected String mjahr;
    @XmlElement(name = "WERKS", required = true)
    protected String werks;
    @XmlElement(name = "LRNO", required = true)
    protected String lrno;
    @XmlElement(name = "TP_LA_NUM", required = true)
    protected String tplanum;
    @XmlElement(name = "KNUMV", required = true)
    protected String knumv;
    @XmlElement(name = "RATE", required = true)
    protected BigDecimal rate;
    @XmlElement(name = "grndate", required = true)
    protected String grnDate;
    /**
     * Gets the value of the pstngdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSTNGDATE() {
        return pstngdate;
    }

    /**
     * Sets the value of the pstngdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSTNGDATE(String value) {
        this.pstngdate = value;
    }

    /**
     * Gets the value of the docdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCDATE() {
        return docdate;
    }

    /**
     * Sets the value of the docdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCDATE(String value) {
        this.docdate = value;
    }

    /**
     * Gets the value of the refdocno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOCNO() {
        return refdocno;
    }

    /**
     * Sets the value of the refdocno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOCNO(String value) {
        this.refdocno = value;
    }

    /**
     * Gets the value of the billoflading property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBILLOFLADING() {
        return billoflading;
    }

    /**
     * Sets the value of the billoflading property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBILLOFLADING(String value) {
        this.billoflading = value;
    }

    /**
     * Gets the value of the grgislipno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRGISLIPNO() {
        return grgislipno;
    }

    /**
     * Sets the value of the grgislipno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRGISLIPNO(String value) {
        this.grgislipno = value;
    }

    /**
     * Gets the value of the pruname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRUNAME() {
        return pruname;
    }

    /**
     * Sets the value of the pruname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRUNAME(String value) {
        this.pruname = value;
    }

    /**
     * Gets the value of the headertxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEADERTXT() {
        return headertxt;
    }

    /**
     * Sets the value of the headertxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEADERTXT(String value) {
        this.headertxt = value;
    }

    /**
     * Gets the value of the vergrgislip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVERGRGISLIP() {
        return vergrgislip;
    }

    /**
     * Sets the value of the vergrgislip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVERGRGISLIP(String value) {
        this.vergrgislip = value;
    }

    /**
     * Gets the value of the vergrgislipx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVERGRGISLIPX() {
        return vergrgislipx;
    }

    /**
     * Sets the value of the vergrgislipx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVERGRGISLIPX(String value) {
        this.vergrgislipx = value;
    }

    /**
     * Gets the value of the extwms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXTWMS() {
        return extwms;
    }

    /**
     * Sets the value of the extwms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXTWMS(String value) {
        this.extwms = value;
    }

    /**
     * Gets the value of the refdocnolong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOCNOLONG() {
        return refdocnolong;
    }

    /**
     * Sets the value of the refdocnolong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOCNOLONG(String value) {
        this.refdocnolong = value;
    }

    /**
     * Gets the value of the billofladinglong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBILLOFLADINGLONG() {
        return billofladinglong;
    }

    /**
     * Sets the value of the billofladinglong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBILLOFLADINGLONG(String value) {
        this.billofladinglong = value;
    }

    /**
     * Gets the value of the gateeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGATEENO() {
        return gateeno;
    }

    /**
     * Sets the value of the gateeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGATEENO(String value) {
        this.gateeno = value;
    }

    /**
     * Gets the value of the pono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPONO() {
        return pono;
    }

    /**
     * Sets the value of the pono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPONO(String value) {
        this.pono = value;
    }

    /**
     * Gets the value of the ebelp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBELP() {
        return ebelp;
    }

    /**
     * Sets the value of the ebelp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBELP(String value) {
        this.ebelp = value;
    }

    /**
     * Gets the value of the vno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVNO() {
        return vno;
    }

    /**
     * Sets the value of the vno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVNO(String value) {
        this.vno = value;
    }

    /**
     * Gets the value of the tcod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTCOD() {
        return tcod;
    }

    /**
     * Sets the value of the tcod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTCOD(String value) {
        this.tcod = value;
    }

    /**
     * Gets the value of the vendname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENDNAME() {
        return vendname;
    }

    /**
     * Sets the value of the vendname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENDNAME(String value) {
        this.vendname = value;
    }

    /**
     * Gets the value of the rdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRDATE() {
        return rdate;
    }

    /**
     * Sets the value of the rdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRDATE(String value) {
        this.rdate = value;
    }

    /**
     * Gets the value of the rtime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRTIME() {
        return rtime;
    }

    /**
     * Sets the value of the rtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRTIME(XMLGregorianCalendar value) {
        this.rtime = value;
    }

    /**
     * Gets the value of the vtyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTYP() {
        return vtyp;
    }

    /**
     * Sets the value of the vtyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTYP(String value) {
        this.vtyp = value;
    }

    /**
     * Gets the value of the gindate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGINDATE() {
        return gindate;
    }

    /**
     * Sets the value of the gindate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGINDATE(String value) {
        this.gindate = value;
    }

    /**
     * Gets the value of the gintime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGINTIME() {
        return gintime;
    }

    /**
     * Sets the value of the gintime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGINTIME(XMLGregorianCalendar value) {
        this.gintime = value;
    }

    /**
     * Gets the value of the usdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSDATE() {
        return usdate;
    }

    /**
     * Sets the value of the usdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSDATE(String value) {
        this.usdate = value;
    }

    /**
     * Gets the value of the ustime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUSTIME() {
        return ustime;
    }

    /**
     * Sets the value of the ustime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUSTIME(XMLGregorianCalendar value) {
        this.ustime = value;
    }

    /**
     * Gets the value of the uedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUEDATE() {
        return uedate;
    }

    /**
     * Sets the value of the uedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUEDATE(String value) {
        this.uedate = value;
    }

    /**
     * Gets the value of the uetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUETIME() {
        return uetime;
    }

    /**
     * Sets the value of the uetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUETIME(XMLGregorianCalendar value) {
        this.uetime = value;
    }

    /**
     * Gets the value of the gotime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGOTIME() {
        return gotime;
    }

    /**
     * Sets the value of the gotime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGOTIME(XMLGregorianCalendar value) {
        this.gotime = value;
    }

    /**
     * Gets the value of the godate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGODATE() {
        return godate;
    }

    /**
     * Sets the value of the godate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGODATE(String value) {
        this.godate = value;
    }

    /**
     * Gets the value of the grwght property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGRWGHT() {
        return grwght;
    }

    /**
     * Sets the value of the grwght property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGRWGHT(BigDecimal value) {
        this.grwght = value;
    }

    /**
     * Gets the value of the trwght property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTRWGHT() {
        return trwght;
    }

    /**
     * Sets the value of the trwght property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTRWGHT(BigDecimal value) {
        this.trwght = value;
    }

    /**
     * Gets the value of the ntwght property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNTWGHT() {
        return ntwght;
    }

    /**
     * Sets the value of the ntwght property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNTWGHT(BigDecimal value) {
        this.ntwght = value;
    }

    /**
     * Gets the value of the matnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATNR() {
        return matnr;
    }

    /**
     * Sets the value of the matnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATNR(String value) {
        this.matnr = value;
    }

    /**
     * Gets the value of the maktx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAKTX() {
        return maktx;
    }

    /**
     * Sets the value of the maktx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAKTX(String value) {
        this.maktx = value;
    }

    /**
     * Gets the value of the bsart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBSART() {
        return bsart;
    }

    /**
     * Sets the value of the bsart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBSART(String value) {
        this.bsart = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the poqty property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPOQTY() {
        return poqty;
    }

    /**
     * Sets the value of the poqty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPOQTY(BigDecimal value) {
        this.poqty = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXT() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXT(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the grwghtunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRWGHTUNIT() {
        return grwghtunit;
    }

    /**
     * Sets the value of the grwghtunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRWGHTUNIT(String value) {
        this.grwghtunit = value;
    }

    /**
     * Gets the value of the trwghtunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRWGHTUNIT() {
        return trwghtunit;
    }

    /**
     * Sets the value of the trwghtunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRWGHTUNIT(String value) {
        this.trwghtunit = value;
    }

    /**
     * Gets the value of the ntwghtunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNTWGHTUNIT() {
        return ntwghtunit;
    }

    /**
     * Sets the value of the ntwghtunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNTWGHTUNIT(String value) {
        this.ntwghtunit = value;
    }

    /**
     * Gets the value of the mblnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMBLNR() {
        return mblnr;
    }

    /**
     * Sets the value of the mblnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMBLNR(String value) {
        this.mblnr = value;
    }

    /**
     * Gets the value of the mjahr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMJAHR() {
        return mjahr;
    }

    /**
     * Sets the value of the mjahr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMJAHR(String value) {
        this.mjahr = value;
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
     * Gets the value of the lrno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLRNO() {
        return lrno;
    }

    /**
     * Sets the value of the lrno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLRNO(String value) {
        this.lrno = value;
    }

    /**
     * Gets the value of the tplanum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPLANUM() {
        return tplanum;
    }

    /**
     * Sets the value of the tplanum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPLANUM(String value) {
        this.tplanum = value;
    }

    /**
     * Gets the value of the knumv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKNUMV() {
        return knumv;
    }

    /**
     * Sets the value of the knumv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKNUMV(String value) {
        this.knumv = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRATE() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRATE(BigDecimal value) {
        this.rate = value;
    }

	public String getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(String grnDate) {
		this.grnDate = grnDate;
	}

}
