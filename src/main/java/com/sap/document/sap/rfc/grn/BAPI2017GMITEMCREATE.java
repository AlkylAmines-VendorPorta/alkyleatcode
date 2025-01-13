
package com.sap.document.sap.rfc.grn;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.sap.document.sap.rfc.ses.YSERVICECOSTCENTER;


/**
 * <p>Java class for BAPI2017_GM_ITEM_CREATE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BAPI2017_GM_ITEM_CREATE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MATERIAL" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="PLANT" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="STGE_LOC" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="BATCH" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MOVE_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="STCK_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SPEC_STOCK" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="VENDOR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="CUSTOMER" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="SALES_ORD" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="S_ORD_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="SCHED_LINE" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="VAL_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ENTRY_QNT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="ENTRY_UOM" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="ENTRY_UOM_ISO" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PO_PR_QNT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="ORDERPR_UN" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="ORDERPR_UN_ISO" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PO_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PO_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="SHIPPING" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="COMP_SHIP" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="NO_MORE_GR" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ITEM_TEXT" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="GR_RCPT" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="UNLOAD_PT" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="COSTCENTER" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ORDERID" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ORDER_ITNO" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="CALC_MOTIVE" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="ASSET_NO" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="SUB_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="RESERV_NO" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="RES_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="RES_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="WITHDRAWN" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MOVE_MAT" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="MOVE_PLANT" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MOVE_STLOC" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MOVE_BATCH" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MOVE_VAL_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MVT_IND" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MOVE_REAS" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="RL_EST_KEY" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="REF_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="COST_OBJ" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="PROFIT_SEGM_NO" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="PROFIT_CTR" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="WBS_ELEM" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="NETWORK" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ACTIVITY" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="PART_ACCT" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AMOUNT_LC" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="AMOUNT_SV" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="REF_DOC_YR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="REF_DOC" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="REF_DOC_IT" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="EXPIRYDATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="PROD_DATE" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="FUND" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="FUNDS_CTR" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="CMMT_ITEM" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="VAL_SALES_ORD" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="VAL_S_ORD_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="VAL_WBS_ELEM" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="GL_ACCOUNT" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IND_PROPOSE_QUANX" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="XSTOB" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="EAN_UPC" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="DELIV_NUMB_TO_SEARCH" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="DELIV_ITEM_TO_SEARCH" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="SERIALNO_AUTO_NUMBERASSIGNMENT" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="VENDRBATCH" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="STGE_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="STGE_BIN" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="SU_PL_STCK_1" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="ST_UN_QTYY_1" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="ST_UN_QTYY_1_ISO" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="UNITTYPE_1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="SU_PL_STCK_2" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="ST_UN_QTYY_2" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="ST_UN_QTYY_2_ISO" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="UNITTYPE_2" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="STGE_TYPE_PC" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="STGE_BIN_PC" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="NO_PST_CHGNT" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="GR_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="STGE_TYPE_ST" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="STGE_BIN_ST" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MATDOC_TR_CANCEL" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MATITEM_TR_CANCEL" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="MATYEAR_TR_CANCEL" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="NO_TRANSFER_REQ" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CO_BUSPROC" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ACTTYPE" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="SUPPL_VEND" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MATERIAL_EXTERNAL" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="MATERIAL_GUID" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="MATERIAL_VERSION" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="MOVE_MAT_EXTERNAL" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="MOVE_MAT_GUID" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="MOVE_MAT_VERSION" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="FUNC_AREA" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="TR_PART_BA" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="PAR_COMPCO" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="DELIV_NUMB" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="DELIV_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="NB_SLIPS" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="NB_SLIPSX" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="GR_RCPTX" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="UNLOAD_PTX" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SPEC_MVMT" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="GRANT_NBR" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="CMMT_ITEM_LONG" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="FUNC_AREA_LONG" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="LINE_ID" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="PARENT_ID" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="LINE_DEPTH" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="QUANTITY" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="BASE_UOM" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="LONGNUM" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="BUDGET_PERIOD" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="EARMARKED_NUMBER" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="EARMARKED_ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="STK_SEGMENT" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="MOVE_SEGMENT" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BAPI2017_GM_ITEM_CREATE", propOrder = {
    "material",
    "plant",
    "stgeloc",
    "batch",
    "movetype",
    "stcktype",
    "specstock",
    "vendor",
    "customer",
    "salesord",
    "sorditem",
    "schedline",
    "valtype",
    "entryqnt",
    "deliveryqnt",
    "entryuom",
    "entryuomiso",
    "poprqnt",
    "orderprun",
    "orderpruniso",
    "ponumber",
    "poitem",
    "shipping",
    "compship",
    "nomoregr",
    "itemtext",
    "grrcpt",
    "unloadpt",
    "costcenter",
    "orderid",
    "orderitno",
    "calcmotive",
    "assetno",
    "subnumber",
    "reservno",
    "resitem",
    "restype",
    "withdrawn",
    "movemat",
    "moveplant",
    "movestloc",
    "movebatch",
    "movevaltype",
    "mvtind",
    "movereas",
    "rlestkey",
    "refdate",
    "costobj",
    "profitsegmno",
    "profitctr",
    "wbselem",
    "network",
    "activity",
    "partacct",
    "amountlc",
    "amountsv",
    "refdocyr",
    "refdoc",
    "refdocit",
    "expirydate",
    "proddate",
    "fund",
    "fundsctr",
    "cmmtitem",
    "valsalesord",
    "valsorditem",
    "valwbselem",
    "glaccount",
    "indproposequanx",
    "xstob",
    "eanupc",
    "delivnumbtosearch",
    "delivitemtosearch",
    "serialnoautonumberassignment",
    "vendrbatch",
    "stgetype",
    "stgebin",
    "suplstck1",
    "stunqtyy1",
    "stunqtyy1ISO",
    "unittype1",
    "suplstck2",
    "stunqtyy2",
    "stunqtyy2ISO",
    "unittype2",
    "stgetypepc",
    "stgebinpc",
    "nopstchgnt",
    "grnumber",
    "stgetypest",
    "stgebinst",
    "matdoctrcancel",
    "matitemtrcancel",
    "matyeartrcancel",
    "notransferreq",
    "cobusproc",
    "acttype",
    "supplvend",
    "materialexternal",
    "materialguid",
    "materialversion",
    "movematexternal",
    "movematguid",
    "movematversion",
    "funcarea",
    "trpartba",
    "parcompco",
    "delivnumb",
    "delivitem",
    "nbslips",
    "nbslipsx",
    "grrcptx",
    "unloadptx",
    "specmvmt",
    "grantnbr",
    "cmmtitemlong",
    "funcarealong",
    "lineid",
    "parentid",
    "linedepth",
    "quantity",
    "baseuom",
    "longnum",
    "budgetperiod",
    "earmarkednumber",
    "earmarkeditem",
    "stksegment",
    "movesegment",
    "yservicecostcenter"
})
public class BAPI2017GMITEMCREATE {

    @XmlElement(name = "MATERIAL", required = true)
    protected String material;
    @XmlElement(name = "PLANT", required = true)
    protected String plant;
    @XmlElement(name = "STGE_LOC", required = true)
    protected String stgeloc;
    @XmlElement(name = "BATCH", required = true)
    protected String batch;
    @XmlElement(name = "MOVE_TYPE", required = true)
    protected String movetype;
    @XmlElement(name = "STCK_TYPE", required = true)
    protected String stcktype;
    @XmlElement(name = "SPEC_STOCK", required = true)
    protected String specstock;
    @XmlElement(name = "VENDOR", required = true)
    protected String vendor;
    @XmlElement(name = "CUSTOMER", required = true)
    protected String customer;
    @XmlElement(name = "SALES_ORD", required = true)
    protected String salesord;
    @XmlElement(name = "S_ORD_ITEM", required = true)
    protected String sorditem;
    @XmlElement(name = "SCHED_LINE", required = true)
    protected String schedline;
    @XmlElement(name = "VAL_TYPE", required = true)
    protected String valtype;
    @XmlElement(name = "ENTRY_QNT", required = true)
    protected BigDecimal entryqnt;
    @XmlElement(name = "DELIVERY_QNT", required = true)
    protected BigDecimal deliveryqnt;
    @XmlElement(name = "ENTRY_UOM", required = true)
    protected String entryuom;
    @XmlElement(name = "ENTRY_UOM_ISO", required = true)
    protected String entryuomiso;
    @XmlElement(name = "PO_PR_QNT", required = true)
    protected BigDecimal poprqnt;
    @XmlElement(name = "ORDERPR_UN", required = true)
    protected String orderprun;
    @XmlElement(name = "ORDERPR_UN_ISO", required = true)
    protected String orderpruniso;
    @XmlElement(name = "PO_NUMBER", required = true)
    protected String ponumber;
    @XmlElement(name = "PO_ITEM", required = true)
    protected String poitem;
    @XmlElement(name = "SHIPPING", required = true)
    protected String shipping;
    @XmlElement(name = "COMP_SHIP", required = true)
    protected String compship;
    @XmlElement(name = "NO_MORE_GR", required = true)
    protected String nomoregr;
    @XmlElement(name = "ITEM_TEXT", required = true)
    protected String itemtext;
    @XmlElement(name = "GR_RCPT", required = true)
    protected String grrcpt;
    @XmlElement(name = "UNLOAD_PT", required = true)
    protected String unloadpt;
    @XmlElement(name = "COSTCENTER", required = true)
    protected String costcenter;
    @XmlElement(name = "ORDERID", required = true)
    protected String orderid;
    @XmlElement(name = "ORDER_ITNO", required = true)
    protected String orderitno;
    @XmlElement(name = "CALC_MOTIVE", required = true)
    protected String calcmotive;
    @XmlElement(name = "ASSET_NO", required = true)
    protected String assetno;
    @XmlElement(name = "SUB_NUMBER", required = true)
    protected String subnumber;
    @XmlElement(name = "RESERV_NO", required = true)
    protected String reservno;
    @XmlElement(name = "RES_ITEM", required = true)
    protected String resitem;
    @XmlElement(name = "RES_TYPE", required = true)
    protected String restype;
    @XmlElement(name = "WITHDRAWN", required = true)
    protected String withdrawn;
    @XmlElement(name = "MOVE_MAT", required = true)
    protected String movemat;
    @XmlElement(name = "MOVE_PLANT", required = true)
    protected String moveplant;
    @XmlElement(name = "MOVE_STLOC", required = true)
    protected String movestloc;
    @XmlElement(name = "MOVE_BATCH", required = true)
    protected String movebatch;
    @XmlElement(name = "MOVE_VAL_TYPE", required = true)
    protected String movevaltype;
    @XmlElement(name = "MVT_IND", required = true)
    protected String mvtind;
    @XmlElement(name = "MOVE_REAS", required = true)
    protected String movereas;
    @XmlElement(name = "RL_EST_KEY", required = true)
    protected String rlestkey;
    @XmlElement(name = "REF_DATE", required = true)
    protected String refdate;
    @XmlElement(name = "COST_OBJ", required = true)
    protected String costobj;
    @XmlElement(name = "PROFIT_SEGM_NO", required = true)
    protected String profitsegmno;
    @XmlElement(name = "PROFIT_CTR", required = true)
    protected String profitctr;
    @XmlElement(name = "WBS_ELEM", required = true)
    protected String wbselem;
    @XmlElement(name = "NETWORK", required = true)
    protected String network;
    @XmlElement(name = "ACTIVITY", required = true)
    protected String activity;
    @XmlElement(name = "PART_ACCT", required = true)
    protected String partacct;
    @XmlElement(name = "AMOUNT_LC", required = true)
    protected BigDecimal amountlc;
    @XmlElement(name = "AMOUNT_SV", required = true)
    protected BigDecimal amountsv;
    @XmlElement(name = "REF_DOC_YR", required = true)
    protected String refdocyr;
    @XmlElement(name = "REF_DOC", required = true)
    protected String refdoc;
    @XmlElement(name = "REF_DOC_IT", required = true)
    protected String refdocit;
    @XmlElement(name = "EXPIRYDATE", required = true)
    protected String expirydate;
    @XmlElement(name = "PROD_DATE", required = true)
    protected String proddate;
    @XmlElement(name = "FUND", required = true)
    protected String fund;
    @XmlElement(name = "FUNDS_CTR", required = true)
    protected String fundsctr;
    @XmlElement(name = "CMMT_ITEM", required = true)
    protected String cmmtitem;
    @XmlElement(name = "VAL_SALES_ORD", required = true)
    protected String valsalesord;
    @XmlElement(name = "VAL_S_ORD_ITEM", required = true)
    protected String valsorditem;
    @XmlElement(name = "VAL_WBS_ELEM", required = true)
    protected String valwbselem;
    @XmlElement(name = "GL_ACCOUNT", required = true)
    protected String glaccount;
    @XmlElement(name = "IND_PROPOSE_QUANX", required = true)
    protected String indproposequanx;
    @XmlElement(name = "XSTOB", required = true)
    protected String xstob;
    @XmlElement(name = "EAN_UPC", required = true)
    protected String eanupc;
    @XmlElement(name = "DELIV_NUMB_TO_SEARCH", required = true)
    protected String delivnumbtosearch;
    @XmlElement(name = "DELIV_ITEM_TO_SEARCH", required = true)
    protected String delivitemtosearch;
    @XmlElement(name = "SERIALNO_AUTO_NUMBERASSIGNMENT", required = true)
    protected String serialnoautonumberassignment;
    @XmlElement(name = "VENDRBATCH", required = true)
    protected String vendrbatch;
    @XmlElement(name = "STGE_TYPE", required = true)
    protected String stgetype;
    @XmlElement(name = "STGE_BIN", required = true)
    protected String stgebin;
    @XmlElement(name = "SU_PL_STCK_1", required = true)
    protected BigDecimal suplstck1;
    @XmlElement(name = "ST_UN_QTYY_1", required = true)
    protected BigDecimal stunqtyy1;
    @XmlElement(name = "ST_UN_QTYY_1_ISO", required = true)
    protected String stunqtyy1ISO;
    @XmlElement(name = "UNITTYPE_1", required = true)
    protected String unittype1;
    @XmlElement(name = "SU_PL_STCK_2", required = true)
    protected BigDecimal suplstck2;
    @XmlElement(name = "ST_UN_QTYY_2", required = true)
    protected BigDecimal stunqtyy2;
    @XmlElement(name = "ST_UN_QTYY_2_ISO", required = true)
    protected String stunqtyy2ISO;
    @XmlElement(name = "UNITTYPE_2", required = true)
    protected String unittype2;
    @XmlElement(name = "STGE_TYPE_PC", required = true)
    protected String stgetypepc;
    @XmlElement(name = "STGE_BIN_PC", required = true)
    protected String stgebinpc;
    @XmlElement(name = "NO_PST_CHGNT", required = true)
    protected String nopstchgnt;
    @XmlElement(name = "GR_NUMBER", required = true)
    protected String grnumber;
    @XmlElement(name = "STGE_TYPE_ST", required = true)
    protected String stgetypest;
    @XmlElement(name = "STGE_BIN_ST", required = true)
    protected String stgebinst;
    @XmlElement(name = "MATDOC_TR_CANCEL", required = true)
    protected String matdoctrcancel;
    @XmlElement(name = "MATITEM_TR_CANCEL", required = true)
    protected String matitemtrcancel;
    @XmlElement(name = "MATYEAR_TR_CANCEL", required = true)
    protected String matyeartrcancel;
    @XmlElement(name = "NO_TRANSFER_REQ", required = true)
    protected String notransferreq;
    @XmlElement(name = "CO_BUSPROC", required = true)
    protected String cobusproc;
    @XmlElement(name = "ACTTYPE", required = true)
    protected String acttype;
    @XmlElement(name = "SUPPL_VEND", required = true)
    protected String supplvend;
    @XmlElement(name = "MATERIAL_EXTERNAL", required = true)
    protected String materialexternal;
    @XmlElement(name = "MATERIAL_GUID", required = true)
    protected String materialguid;
    @XmlElement(name = "MATERIAL_VERSION", required = true)
    protected String materialversion;
    @XmlElement(name = "MOVE_MAT_EXTERNAL", required = true)
    protected String movematexternal;
    @XmlElement(name = "MOVE_MAT_GUID", required = true)
    protected String movematguid;
    @XmlElement(name = "MOVE_MAT_VERSION", required = true)
    protected String movematversion;
    @XmlElement(name = "FUNC_AREA", required = true)
    protected String funcarea;
    @XmlElement(name = "TR_PART_BA", required = true)
    protected String trpartba;
    @XmlElement(name = "PAR_COMPCO", required = true)
    protected String parcompco;
    @XmlElement(name = "DELIV_NUMB", required = true)
    protected String delivnumb;
    @XmlElement(name = "DELIV_ITEM", required = true)
    protected String delivitem;
    @XmlElement(name = "NB_SLIPS", required = true)
    protected String nbslips;
    @XmlElement(name = "NB_SLIPSX", required = true)
    protected String nbslipsx;
    @XmlElement(name = "GR_RCPTX", required = true)
    protected String grrcptx;
    @XmlElement(name = "UNLOAD_PTX", required = true)
    protected String unloadptx;
    @XmlElement(name = "SPEC_MVMT", required = true)
    protected String specmvmt;
    @XmlElement(name = "GRANT_NBR", required = true)
    protected String grantnbr;
    @XmlElement(name = "CMMT_ITEM_LONG", required = true)
    protected String cmmtitemlong;
    @XmlElement(name = "FUNC_AREA_LONG", required = true)
    protected String funcarealong;
    @XmlElement(name = "LINE_ID", required = true)
    protected String lineid;
    @XmlElement(name = "PARENT_ID", required = true)
    protected String parentid;
    @XmlElement(name = "LINE_DEPTH", required = true)
    protected String linedepth;
    @XmlElement(name = "QUANTITY", required = true)
    protected BigDecimal quantity;
    @XmlElement(name = "BASE_UOM", required = true)
    protected String baseuom;
    @XmlElement(name = "LONGNUM", required = true)
    protected String longnum;
    @XmlElement(name = "BUDGET_PERIOD", required = true)
    protected String budgetperiod;
    @XmlElement(name = "EARMARKED_NUMBER", required = true)
    protected String earmarkednumber;
    @XmlElement(name = "EARMARKED_ITEM", required = true)
    protected String earmarkeditem;
    @XmlElement(name = "STK_SEGMENT", required = true)
    protected String stksegment;
    @XmlElement(name = "MOVE_SEGMENT", required = true)
    protected String movesegment;
    @XmlElement(name = "YSERVICECOSTCENTER", required = true)
    protected List<YSERVICECOSTCENTER> yservicecostcenter;;

    /**
     * Gets the value of the material property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATERIAL() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATERIAL(String value) {
        this.material = value;
    }

    /**
     * Gets the value of the plant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLANT() {
        return plant;
    }

    /**
     * Sets the value of the plant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLANT(String value) {
        this.plant = value;
    }

    /**
     * Gets the value of the stgeloc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGELOC() {
        return stgeloc;
    }

    /**
     * Sets the value of the stgeloc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGELOC(String value) {
        this.stgeloc = value;
    }

    /**
     * Gets the value of the batch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBATCH() {
        return batch;
    }

    /**
     * Sets the value of the batch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBATCH(String value) {
        this.batch = value;
    }

    /**
     * Gets the value of the movetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVETYPE() {
        return movetype;
    }

    /**
     * Sets the value of the movetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVETYPE(String value) {
        this.movetype = value;
    }

    /**
     * Gets the value of the stcktype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTCKTYPE() {
        return stcktype;
    }

    /**
     * Sets the value of the stcktype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTCKTYPE(String value) {
        this.stcktype = value;
    }

    /**
     * Gets the value of the specstock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPECSTOCK() {
        return specstock;
    }

    /**
     * Sets the value of the specstock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPECSTOCK(String value) {
        this.specstock = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENDOR() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENDOR(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMER() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMER(String value) {
        this.customer = value;
    }

    /**
     * Gets the value of the salesord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALESORD() {
        return salesord;
    }

    /**
     * Sets the value of the salesord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALESORD(String value) {
        this.salesord = value;
    }

    /**
     * Gets the value of the sorditem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSORDITEM() {
        return sorditem;
    }

    /**
     * Sets the value of the sorditem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSORDITEM(String value) {
        this.sorditem = value;
    }

    /**
     * Gets the value of the schedline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCHEDLINE() {
        return schedline;
    }

    /**
     * Sets the value of the schedline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCHEDLINE(String value) {
        this.schedline = value;
    }

    /**
     * Gets the value of the valtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALTYPE() {
        return valtype;
    }

    /**
     * Sets the value of the valtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALTYPE(String value) {
        this.valtype = value;
    }

    /**
     * Gets the value of the entryqnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getENTRYQNT() {
        return entryqnt;
    }

    /**
     * Sets the value of the entryqnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setENTRYQNT(BigDecimal value) {
        this.entryqnt = value;
    }

    
    
    public BigDecimal getDELIVERYQNT() {
        return deliveryqnt;
    }

    /**
     * Sets the value of the deliveryqnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDELIVERYQNT(BigDecimal value) {
        this.deliveryqnt = value;
    }

    /**
     * Gets the value of the entryuom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTRYUOM() {
        return entryuom;
    }

    /**
     * Sets the value of the entryuom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTRYUOM(String value) {
        this.entryuom = value;
    }

    /**
     * Gets the value of the entryuomiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTRYUOMISO() {
        return entryuomiso;
    }

    /**
     * Sets the value of the entryuomiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTRYUOMISO(String value) {
        this.entryuomiso = value;
    }

    /**
     * Gets the value of the poprqnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPOPRQNT() {
        return poprqnt;
    }

    /**
     * Sets the value of the poprqnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPOPRQNT(BigDecimal value) {
        this.poprqnt = value;
    }

    /**
     * Gets the value of the orderprun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERPRUN() {
        return orderprun;
    }

    /**
     * Sets the value of the orderprun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERPRUN(String value) {
        this.orderprun = value;
    }

    /**
     * Gets the value of the orderpruniso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERPRUNISO() {
        return orderpruniso;
    }

    /**
     * Sets the value of the orderpruniso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERPRUNISO(String value) {
        this.orderpruniso = value;
    }

    /**
     * Gets the value of the ponumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPONUMBER() {
        return ponumber;
    }

    /**
     * Sets the value of the ponumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPONUMBER(String value) {
        this.ponumber = value;
    }

    /**
     * Gets the value of the poitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOITEM() {
        return poitem;
    }

    /**
     * Sets the value of the poitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOITEM(String value) {
        this.poitem = value;
    }

    /**
     * Gets the value of the shipping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHIPPING() {
        return shipping;
    }

    /**
     * Sets the value of the shipping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHIPPING(String value) {
        this.shipping = value;
    }

    /**
     * Gets the value of the compship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPSHIP() {
        return compship;
    }

    /**
     * Sets the value of the compship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPSHIP(String value) {
        this.compship = value;
    }

    /**
     * Gets the value of the nomoregr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMOREGR() {
        return nomoregr;
    }

    /**
     * Sets the value of the nomoregr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMOREGR(String value) {
        this.nomoregr = value;
    }

    /**
     * Gets the value of the itemtext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITEMTEXT() {
        return itemtext;
    }

    /**
     * Sets the value of the itemtext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITEMTEXT(String value) {
        this.itemtext = value;
    }

    /**
     * Gets the value of the grrcpt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRRCPT() {
        return grrcpt;
    }

    /**
     * Sets the value of the grrcpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRRCPT(String value) {
        this.grrcpt = value;
    }

    /**
     * Gets the value of the unloadpt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNLOADPT() {
        return unloadpt;
    }

    /**
     * Sets the value of the unloadpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNLOADPT(String value) {
        this.unloadpt = value;
    }

    /**
     * Gets the value of the costcenter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOSTCENTER() {
        return costcenter;
    }

    /**
     * Sets the value of the costcenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOSTCENTER(String value) {
        this.costcenter = value;
    }

    /**
     * Gets the value of the orderid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERID() {
        return orderid;
    }

    /**
     * Sets the value of the orderid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERID(String value) {
        this.orderid = value;
    }

    /**
     * Gets the value of the orderitno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERITNO() {
        return orderitno;
    }

    /**
     * Sets the value of the orderitno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERITNO(String value) {
        this.orderitno = value;
    }

    /**
     * Gets the value of the calcmotive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCALCMOTIVE() {
        return calcmotive;
    }

    /**
     * Sets the value of the calcmotive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCALCMOTIVE(String value) {
        this.calcmotive = value;
    }

    /**
     * Gets the value of the assetno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASSETNO() {
        return assetno;
    }

    /**
     * Sets the value of the assetno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASSETNO(String value) {
        this.assetno = value;
    }

    /**
     * Gets the value of the subnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBNUMBER() {
        return subnumber;
    }

    /**
     * Sets the value of the subnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBNUMBER(String value) {
        this.subnumber = value;
    }

    /**
     * Gets the value of the reservno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESERVNO() {
        return reservno;
    }

    /**
     * Sets the value of the reservno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESERVNO(String value) {
        this.reservno = value;
    }

    /**
     * Gets the value of the resitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESITEM() {
        return resitem;
    }

    /**
     * Sets the value of the resitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESITEM(String value) {
        this.resitem = value;
    }

    /**
     * Gets the value of the restype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESTYPE() {
        return restype;
    }

    /**
     * Sets the value of the restype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESTYPE(String value) {
        this.restype = value;
    }

    /**
     * Gets the value of the withdrawn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWITHDRAWN() {
        return withdrawn;
    }

    /**
     * Sets the value of the withdrawn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWITHDRAWN(String value) {
        this.withdrawn = value;
    }

    /**
     * Gets the value of the movemat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEMAT() {
        return movemat;
    }

    /**
     * Sets the value of the movemat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEMAT(String value) {
        this.movemat = value;
    }

    /**
     * Gets the value of the moveplant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEPLANT() {
        return moveplant;
    }

    /**
     * Sets the value of the moveplant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEPLANT(String value) {
        this.moveplant = value;
    }

    /**
     * Gets the value of the movestloc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVESTLOC() {
        return movestloc;
    }

    /**
     * Sets the value of the movestloc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVESTLOC(String value) {
        this.movestloc = value;
    }

    /**
     * Gets the value of the movebatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEBATCH() {
        return movebatch;
    }

    /**
     * Sets the value of the movebatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEBATCH(String value) {
        this.movebatch = value;
    }

    /**
     * Gets the value of the movevaltype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEVALTYPE() {
        return movevaltype;
    }

    /**
     * Sets the value of the movevaltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEVALTYPE(String value) {
        this.movevaltype = value;
    }

    /**
     * Gets the value of the mvtind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMVTIND() {
        return mvtind;
    }

    /**
     * Sets the value of the mvtind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMVTIND(String value) {
        this.mvtind = value;
    }

    /**
     * Gets the value of the movereas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEREAS() {
        return movereas;
    }

    /**
     * Sets the value of the movereas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEREAS(String value) {
        this.movereas = value;
    }

    /**
     * Gets the value of the rlestkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRLESTKEY() {
        return rlestkey;
    }

    /**
     * Sets the value of the rlestkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRLESTKEY(String value) {
        this.rlestkey = value;
    }

    /**
     * Gets the value of the refdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDATE() {
        return refdate;
    }

    /**
     * Sets the value of the refdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDATE(String value) {
        this.refdate = value;
    }

    /**
     * Gets the value of the costobj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOSTOBJ() {
        return costobj;
    }

    /**
     * Sets the value of the costobj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOSTOBJ(String value) {
        this.costobj = value;
    }

    /**
     * Gets the value of the profitsegmno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROFITSEGMNO() {
        return profitsegmno;
    }

    /**
     * Sets the value of the profitsegmno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROFITSEGMNO(String value) {
        this.profitsegmno = value;
    }

    /**
     * Gets the value of the profitctr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROFITCTR() {
        return profitctr;
    }

    /**
     * Sets the value of the profitctr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROFITCTR(String value) {
        this.profitctr = value;
    }

    /**
     * Gets the value of the wbselem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWBSELEM() {
        return wbselem;
    }

    /**
     * Sets the value of the wbselem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWBSELEM(String value) {
        this.wbselem = value;
    }

    /**
     * Gets the value of the network property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNETWORK() {
        return network;
    }

    /**
     * Sets the value of the network property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNETWORK(String value) {
        this.network = value;
    }

    /**
     * Gets the value of the activity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTIVITY() {
        return activity;
    }

    /**
     * Sets the value of the activity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTIVITY(String value) {
        this.activity = value;
    }

    /**
     * Gets the value of the partacct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARTACCT() {
        return partacct;
    }

    /**
     * Sets the value of the partacct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARTACCT(String value) {
        this.partacct = value;
    }

    /**
     * Gets the value of the amountlc property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAMOUNTLC() {
        return amountlc;
    }

    /**
     * Sets the value of the amountlc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAMOUNTLC(BigDecimal value) {
        this.amountlc = value;
    }

    /**
     * Gets the value of the amountsv property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAMOUNTSV() {
        return amountsv;
    }

    /**
     * Sets the value of the amountsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAMOUNTSV(BigDecimal value) {
        this.amountsv = value;
    }

    /**
     * Gets the value of the refdocyr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOCYR() {
        return refdocyr;
    }

    /**
     * Sets the value of the refdocyr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOCYR(String value) {
        this.refdocyr = value;
    }

    /**
     * Gets the value of the refdoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOC() {
        return refdoc;
    }

    /**
     * Sets the value of the refdoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOC(String value) {
        this.refdoc = value;
    }

    /**
     * Gets the value of the refdocit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFDOCIT() {
        return refdocit;
    }

    /**
     * Sets the value of the refdocit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFDOCIT(String value) {
        this.refdocit = value;
    }

    /**
     * Gets the value of the expirydate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXPIRYDATE() {
        return expirydate;
    }

    /**
     * Sets the value of the expirydate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXPIRYDATE(String value) {
        this.expirydate = value;
    }

    /**
     * Gets the value of the proddate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRODDATE() {
        return proddate;
    }

    /**
     * Sets the value of the proddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRODDATE(String value) {
        this.proddate = value;
    }

    /**
     * Gets the value of the fund property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFUND() {
        return fund;
    }

    /**
     * Sets the value of the fund property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFUND(String value) {
        this.fund = value;
    }

    /**
     * Gets the value of the fundsctr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFUNDSCTR() {
        return fundsctr;
    }

    /**
     * Sets the value of the fundsctr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFUNDSCTR(String value) {
        this.fundsctr = value;
    }

    /**
     * Gets the value of the cmmtitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMMTITEM() {
        return cmmtitem;
    }

    /**
     * Sets the value of the cmmtitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMMTITEM(String value) {
        this.cmmtitem = value;
    }

    /**
     * Gets the value of the valsalesord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALSALESORD() {
        return valsalesord;
    }

    /**
     * Sets the value of the valsalesord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALSALESORD(String value) {
        this.valsalesord = value;
    }

    /**
     * Gets the value of the valsorditem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALSORDITEM() {
        return valsorditem;
    }

    /**
     * Sets the value of the valsorditem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALSORDITEM(String value) {
        this.valsorditem = value;
    }

    /**
     * Gets the value of the valwbselem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALWBSELEM() {
        return valwbselem;
    }

    /**
     * Sets the value of the valwbselem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALWBSELEM(String value) {
        this.valwbselem = value;
    }

    /**
     * Gets the value of the glaccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGLACCOUNT() {
        return glaccount;
    }

    /**
     * Sets the value of the glaccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGLACCOUNT(String value) {
        this.glaccount = value;
    }

    /**
     * Gets the value of the indproposequanx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDPROPOSEQUANX() {
        return indproposequanx;
    }

    /**
     * Sets the value of the indproposequanx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDPROPOSEQUANX(String value) {
        this.indproposequanx = value;
    }

    /**
     * Gets the value of the xstob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSTOB() {
        return xstob;
    }

    /**
     * Sets the value of the xstob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSTOB(String value) {
        this.xstob = value;
    }

    /**
     * Gets the value of the eanupc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEANUPC() {
        return eanupc;
    }

    /**
     * Sets the value of the eanupc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEANUPC(String value) {
        this.eanupc = value;
    }

    /**
     * Gets the value of the delivnumbtosearch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDELIVNUMBTOSEARCH() {
        return delivnumbtosearch;
    }

    /**
     * Sets the value of the delivnumbtosearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDELIVNUMBTOSEARCH(String value) {
        this.delivnumbtosearch = value;
    }

    /**
     * Gets the value of the delivitemtosearch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDELIVITEMTOSEARCH() {
        return delivitemtosearch;
    }

    /**
     * Sets the value of the delivitemtosearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDELIVITEMTOSEARCH(String value) {
        this.delivitemtosearch = value;
    }

    /**
     * Gets the value of the serialnoautonumberassignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERIALNOAUTONUMBERASSIGNMENT() {
        return serialnoautonumberassignment;
    }

    /**
     * Sets the value of the serialnoautonumberassignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERIALNOAUTONUMBERASSIGNMENT(String value) {
        this.serialnoautonumberassignment = value;
    }

    /**
     * Gets the value of the vendrbatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVENDRBATCH() {
        return vendrbatch;
    }

    /**
     * Sets the value of the vendrbatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVENDRBATCH(String value) {
        this.vendrbatch = value;
    }

    /**
     * Gets the value of the stgetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGETYPE() {
        return stgetype;
    }

    /**
     * Sets the value of the stgetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGETYPE(String value) {
        this.stgetype = value;
    }

    /**
     * Gets the value of the stgebin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGEBIN() {
        return stgebin;
    }

    /**
     * Sets the value of the stgebin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGEBIN(String value) {
        this.stgebin = value;
    }

    /**
     * Gets the value of the suplstck1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSUPLSTCK1() {
        return suplstck1;
    }

    /**
     * Sets the value of the suplstck1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSUPLSTCK1(BigDecimal value) {
        this.suplstck1 = value;
    }

    /**
     * Gets the value of the stunqtyy1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTUNQTYY1() {
        return stunqtyy1;
    }

    /**
     * Sets the value of the stunqtyy1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTUNQTYY1(BigDecimal value) {
        this.stunqtyy1 = value;
    }

    /**
     * Gets the value of the stunqtyy1ISO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTUNQTYY1ISO() {
        return stunqtyy1ISO;
    }

    /**
     * Sets the value of the stunqtyy1ISO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTUNQTYY1ISO(String value) {
        this.stunqtyy1ISO = value;
    }

    /**
     * Gets the value of the unittype1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNITTYPE1() {
        return unittype1;
    }

    /**
     * Sets the value of the unittype1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNITTYPE1(String value) {
        this.unittype1 = value;
    }

    /**
     * Gets the value of the suplstck2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSUPLSTCK2() {
        return suplstck2;
    }

    /**
     * Sets the value of the suplstck2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSUPLSTCK2(BigDecimal value) {
        this.suplstck2 = value;
    }

    /**
     * Gets the value of the stunqtyy2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTUNQTYY2() {
        return stunqtyy2;
    }

    /**
     * Sets the value of the stunqtyy2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTUNQTYY2(BigDecimal value) {
        this.stunqtyy2 = value;
    }

    /**
     * Gets the value of the stunqtyy2ISO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTUNQTYY2ISO() {
        return stunqtyy2ISO;
    }

    /**
     * Sets the value of the stunqtyy2ISO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTUNQTYY2ISO(String value) {
        this.stunqtyy2ISO = value;
    }

    /**
     * Gets the value of the unittype2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNITTYPE2() {
        return unittype2;
    }

    /**
     * Sets the value of the unittype2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNITTYPE2(String value) {
        this.unittype2 = value;
    }

    /**
     * Gets the value of the stgetypepc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGETYPEPC() {
        return stgetypepc;
    }

    /**
     * Sets the value of the stgetypepc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGETYPEPC(String value) {
        this.stgetypepc = value;
    }

    /**
     * Gets the value of the stgebinpc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGEBINPC() {
        return stgebinpc;
    }

    /**
     * Sets the value of the stgebinpc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGEBINPC(String value) {
        this.stgebinpc = value;
    }

    /**
     * Gets the value of the nopstchgnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOPSTCHGNT() {
        return nopstchgnt;
    }

    /**
     * Sets the value of the nopstchgnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOPSTCHGNT(String value) {
        this.nopstchgnt = value;
    }

    /**
     * Gets the value of the grnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRNUMBER() {
        return grnumber;
    }

    /**
     * Sets the value of the grnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRNUMBER(String value) {
        this.grnumber = value;
    }

    /**
     * Gets the value of the stgetypest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGETYPEST() {
        return stgetypest;
    }

    /**
     * Sets the value of the stgetypest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGETYPEST(String value) {
        this.stgetypest = value;
    }

    /**
     * Gets the value of the stgebinst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTGEBINST() {
        return stgebinst;
    }

    /**
     * Sets the value of the stgebinst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTGEBINST(String value) {
        this.stgebinst = value;
    }

    /**
     * Gets the value of the matdoctrcancel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATDOCTRCANCEL() {
        return matdoctrcancel;
    }

    /**
     * Sets the value of the matdoctrcancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATDOCTRCANCEL(String value) {
        this.matdoctrcancel = value;
    }

    /**
     * Gets the value of the matitemtrcancel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATITEMTRCANCEL() {
        return matitemtrcancel;
    }

    /**
     * Sets the value of the matitemtrcancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATITEMTRCANCEL(String value) {
        this.matitemtrcancel = value;
    }

    /**
     * Gets the value of the matyeartrcancel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATYEARTRCANCEL() {
        return matyeartrcancel;
    }

    /**
     * Sets the value of the matyeartrcancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATYEARTRCANCEL(String value) {
        this.matyeartrcancel = value;
    }

    /**
     * Gets the value of the notransferreq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOTRANSFERREQ() {
        return notransferreq;
    }

    /**
     * Sets the value of the notransferreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOTRANSFERREQ(String value) {
        this.notransferreq = value;
    }

    /**
     * Gets the value of the cobusproc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOBUSPROC() {
        return cobusproc;
    }

    /**
     * Sets the value of the cobusproc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOBUSPROC(String value) {
        this.cobusproc = value;
    }

    /**
     * Gets the value of the acttype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTTYPE() {
        return acttype;
    }

    /**
     * Sets the value of the acttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTTYPE(String value) {
        this.acttype = value;
    }

    /**
     * Gets the value of the supplvend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUPPLVEND() {
        return supplvend;
    }

    /**
     * Sets the value of the supplvend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUPPLVEND(String value) {
        this.supplvend = value;
    }

    /**
     * Gets the value of the materialexternal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATERIALEXTERNAL() {
        return materialexternal;
    }

    /**
     * Sets the value of the materialexternal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATERIALEXTERNAL(String value) {
        this.materialexternal = value;
    }

    /**
     * Gets the value of the materialguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATERIALGUID() {
        return materialguid;
    }

    /**
     * Sets the value of the materialguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATERIALGUID(String value) {
        this.materialguid = value;
    }

    /**
     * Gets the value of the materialversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATERIALVERSION() {
        return materialversion;
    }

    /**
     * Sets the value of the materialversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATERIALVERSION(String value) {
        this.materialversion = value;
    }

    /**
     * Gets the value of the movematexternal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEMATEXTERNAL() {
        return movematexternal;
    }

    /**
     * Sets the value of the movematexternal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEMATEXTERNAL(String value) {
        this.movematexternal = value;
    }

    /**
     * Gets the value of the movematguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEMATGUID() {
        return movematguid;
    }

    /**
     * Sets the value of the movematguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEMATGUID(String value) {
        this.movematguid = value;
    }

    /**
     * Gets the value of the movematversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVEMATVERSION() {
        return movematversion;
    }

    /**
     * Sets the value of the movematversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVEMATVERSION(String value) {
        this.movematversion = value;
    }

    /**
     * Gets the value of the funcarea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFUNCAREA() {
        return funcarea;
    }

    /**
     * Sets the value of the funcarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFUNCAREA(String value) {
        this.funcarea = value;
    }

    /**
     * Gets the value of the trpartba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRPARTBA() {
        return trpartba;
    }

    /**
     * Sets the value of the trpartba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRPARTBA(String value) {
        this.trpartba = value;
    }

    /**
     * Gets the value of the parcompco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARCOMPCO() {
        return parcompco;
    }

    /**
     * Sets the value of the parcompco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARCOMPCO(String value) {
        this.parcompco = value;
    }

    /**
     * Gets the value of the delivnumb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDELIVNUMB() {
        return delivnumb;
    }

    /**
     * Sets the value of the delivnumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDELIVNUMB(String value) {
        this.delivnumb = value;
    }

    /**
     * Gets the value of the delivitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDELIVITEM() {
        return delivitem;
    }

    /**
     * Sets the value of the delivitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDELIVITEM(String value) {
        this.delivitem = value;
    }

    /**
     * Gets the value of the nbslips property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBSLIPS() {
        return nbslips;
    }

    /**
     * Sets the value of the nbslips property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBSLIPS(String value) {
        this.nbslips = value;
    }

    /**
     * Gets the value of the nbslipsx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBSLIPSX() {
        return nbslipsx;
    }

    /**
     * Sets the value of the nbslipsx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBSLIPSX(String value) {
        this.nbslipsx = value;
    }

    /**
     * Gets the value of the grrcptx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRRCPTX() {
        return grrcptx;
    }

    /**
     * Sets the value of the grrcptx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRRCPTX(String value) {
        this.grrcptx = value;
    }

    /**
     * Gets the value of the unloadptx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNLOADPTX() {
        return unloadptx;
    }

    /**
     * Sets the value of the unloadptx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNLOADPTX(String value) {
        this.unloadptx = value;
    }

    /**
     * Gets the value of the specmvmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPECMVMT() {
        return specmvmt;
    }

    /**
     * Sets the value of the specmvmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPECMVMT(String value) {
        this.specmvmt = value;
    }

    /**
     * Gets the value of the grantnbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRANTNBR() {
        return grantnbr;
    }

    /**
     * Sets the value of the grantnbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRANTNBR(String value) {
        this.grantnbr = value;
    }

    /**
     * Gets the value of the cmmtitemlong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMMTITEMLONG() {
        return cmmtitemlong;
    }

    /**
     * Sets the value of the cmmtitemlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMMTITEMLONG(String value) {
        this.cmmtitemlong = value;
    }

    /**
     * Gets the value of the funcarealong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFUNCAREALONG() {
        return funcarealong;
    }

    /**
     * Sets the value of the funcarealong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFUNCAREALONG(String value) {
        this.funcarealong = value;
    }

    /**
     * Gets the value of the lineid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINEID() {
        return lineid;
    }

    /**
     * Sets the value of the lineid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINEID(String value) {
        this.lineid = value;
    }

    /**
     * Gets the value of the parentid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARENTID() {
        return parentid;
    }

    /**
     * Sets the value of the parentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARENTID(String value) {
        this.parentid = value;
    }

    /**
     * Gets the value of the linedepth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINEDEPTH() {
        return linedepth;
    }

    /**
     * Sets the value of the linedepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINEDEPTH(String value) {
        this.linedepth = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQUANTITY() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQUANTITY(BigDecimal value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the baseuom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBASEUOM() {
        return baseuom;
    }

    /**
     * Sets the value of the baseuom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBASEUOM(String value) {
        this.baseuom = value;
    }

    /**
     * Gets the value of the longnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLONGNUM() {
        return longnum;
    }

    /**
     * Sets the value of the longnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLONGNUM(String value) {
        this.longnum = value;
    }

    /**
     * Gets the value of the budgetperiod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUDGETPERIOD() {
        return budgetperiod;
    }

    /**
     * Sets the value of the budgetperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUDGETPERIOD(String value) {
        this.budgetperiod = value;
    }

    /**
     * Gets the value of the earmarkednumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEARMARKEDNUMBER() {
        return earmarkednumber;
    }

    /**
     * Sets the value of the earmarkednumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEARMARKEDNUMBER(String value) {
        this.earmarkednumber = value;
    }

    /**
     * Gets the value of the earmarkeditem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEARMARKEDITEM() {
        return earmarkeditem;
    }

    /**
     * Sets the value of the earmarkeditem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEARMARKEDITEM(String value) {
        this.earmarkeditem = value;
    }

    /**
     * Gets the value of the stksegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTKSEGMENT() {
        return stksegment;
    }

    /**
     * Sets the value of the stksegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTKSEGMENT(String value) {
        this.stksegment = value;
    }

    /**
     * Gets the value of the movesegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOVESEGMENT() {
        return movesegment;
    }

    /**
     * Sets the value of the movesegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOVESEGMENT(String value) {
        this.movesegment = value;
    }

	public List<YSERVICECOSTCENTER> getYservicecostcenter() {
		return yservicecostcenter;
	}

	public void setYservicecostcenter(List<YSERVICECOSTCENTER> yservicecostcenter) {
		this.yservicecostcenter = yservicecostcenter;
	}
    

}
