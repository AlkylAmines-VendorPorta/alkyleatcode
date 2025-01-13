
package com.sap.document.sap.rfc.grn;

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
 *         &lt;element name="INPUT" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_ZMM_GRN"/>
 *         &lt;element name="LINEITEM" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_BAPI2017_GM_ITEM_CREATE"/>
 *         &lt;element name="OUTPUT" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_BAPI2017_GM_HEAD_RET"/>
 *         &lt;element name="RETURN" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_BAPIRET2"/>
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
    "lineitem",
    "output",
    "_return"
})
@XmlRootElement(name = "ZMM_GRN_PORTALResponse")
public class ZMMGRNPORTALResponse {

    @XmlElement(name = "INPUT", required = true)
    protected TABLEOFZMMGRN input;
    @XmlElement(name = "LINEITEM", required = true)
    protected TABLEOFBAPI2017GMITEMCREATE lineitem;
    @XmlElement(name = "OUTPUT", required = true)
    protected TABLEOFBAPI2017GMHEADRET output;
    @XmlElement(name = "RETURN", required = true)
    protected TABLEOFBAPIRET2 _return;

    /**
     * Gets the value of the input property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFZMMGRN }
     *     
     */
    public TABLEOFZMMGRN getINPUT() {
        return input;
    }

    /**
     * Sets the value of the input property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFZMMGRN }
     *     
     */
    public void setINPUT(TABLEOFZMMGRN value) {
        this.input = value;
    }

    /**
     * Gets the value of the lineitem property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFBAPI2017GMITEMCREATE }
     *     
     */
    public TABLEOFBAPI2017GMITEMCREATE getLINEITEM() {
        return lineitem;
    }

    /**
     * Sets the value of the lineitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFBAPI2017GMITEMCREATE }
     *     
     */
    public void setLINEITEM(TABLEOFBAPI2017GMITEMCREATE value) {
        this.lineitem = value;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFBAPI2017GMHEADRET }
     *     
     */
    public TABLEOFBAPI2017GMHEADRET getOUTPUT() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFBAPI2017GMHEADRET }
     *     
     */
    public void setOUTPUT(TABLEOFBAPI2017GMHEADRET value) {
        this.output = value;
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

}
