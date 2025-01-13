
package com.sap.document.sap.rfc.grn;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TABLE_OF_BAPI2017_GM_HEAD_RET complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TABLE_OF_BAPI2017_GM_HEAD_RET">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="item" type="{urn:sap-com:document:sap:rfc:functions}BAPI2017_GM_HEAD_RET" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TABLE_OF_BAPI2017_GM_HEAD_RET", propOrder = {
    "item"
})
public class TABLEOFBAPI2017GMHEADRET {

    protected List<BAPI2017GMHEADRET> item;

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
     * {@link BAPI2017GMHEADRET }
     * 
     * 
     */
    public List<BAPI2017GMHEADRET> getItem() {
        if (item == null) {
            item = new ArrayList<BAPI2017GMHEADRET>();
        }
        return this.item;
    }

}
