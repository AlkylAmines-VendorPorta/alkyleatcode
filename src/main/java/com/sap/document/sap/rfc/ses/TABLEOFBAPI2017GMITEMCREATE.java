
package com.sap.document.sap.rfc.ses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TABLE_OF_BAPI2017_GM_ITEM_CREATE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TABLE_OF_BAPI2017_GM_ITEM_CREATE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="item" type="{urn:sap-com:document:sap:rfc:functions}BAPI2017_GM_ITEM_CREATE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TABLE_OF_BAPI2017_GM_ITEM_CREATE", propOrder = {
    "item"
})
public class TABLEOFBAPI2017GMITEMCREATE {

    protected List<BAPI2017GMITEMCREATE> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BAPI2017GMITEMCREATE }
     * 
     * 
     */
    public List<BAPI2017GMITEMCREATE> getItem() {
        if (item == null) {
            item = new ArrayList<BAPI2017GMITEMCREATE>();
        }
        return this.item;
    }

}
