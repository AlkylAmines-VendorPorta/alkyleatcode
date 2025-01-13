package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZVendor_User", propOrder = {
    "name",
    "mob",
    "telf1",
    "smtpaddr",
    "pref",
    "Des",
    "Dept",
    "enq",
    "po",
    "ac"
})
public class ZVendorUser_Type {
	
	  @XmlElement(name = "NAME", required = true)
	    protected String name;
	    @XmlElement(name = "MOB", required = true)
	    protected String mob;
	    @XmlElement(name = "TELF1", required = true)
	    protected String telf1;
	    @XmlElement(name = "SMTP_ADDR", required = true)
	    protected String pref;
	    @XmlElement(name = "PREF", required = true)
	    protected String Des;
	    @XmlElement(name = "DES", required = true)
	    protected String Dept;
	    @XmlElement(name = "DEPT", required = true)
	    protected String enq;
	    @XmlElement(name = "ENQ", required = true)
	    protected String po;
	    @XmlElement(name = "PO", required = true)
	    protected String ac;
	    @XmlElement(name = "AC", required = true)
	    protected String smtpaddr;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMob() {
			return mob;
		}
		public void setMob(String mob) {
			this.mob = mob;
		}
		public String getTelf1() {
			return telf1;
		}
		public void setTelf1(String telf1) {
			this.telf1 = telf1;
		}
		public String getSmtpaddr() {
			return smtpaddr;
		}
		public void setSmtpaddr(String smtpaddr) {
			this.smtpaddr = smtpaddr;
		}
		public String getPref() {
			return pref;
		}
		public void setPref(String pref) {
			this.pref = pref;
		}
		public String getDes() {
			return Des;
		}
		public void setDes(String des) {
			Des = des;
		}
		public String getDept() {
			return Dept;
		}
		public void setDept(String dept) {
			Dept = dept;
		}
		public String getEnq() {
			return enq;
		}
		public void setEnq(String enq) {
			this.enq = enq;
		}
		public String getPo() {
			return po;
		}
		public void setPo(String po) {
			this.po = po;
		}
		public String getAc() {
			return ac;
		}
		public void setAc(String ac) {
			this.ac = ac;
		}
	    
	    

}
