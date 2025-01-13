package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_scrutiny")
public class Scrutiny extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long scrutinyId;
	private Bidder bidder;
	private String scrutinyStatusCode;
	private String tenderSecCode;
	private String scrutinyTypeCode;
	/*private Set<ItemScrutiny> itemScrutinyList;*/
	
	@Id
	@SequenceGenerator(name="t_scrutiny_seq",sequenceName="t_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_scrutiny_seq")	
	@Column(name = "t_scrutiny_id", updatable=false)
	public Long getScrutinyId() {
		return scrutinyId;
	}
	public void setScrutinyId(Long scrutinyId) {
		this.scrutinyId = scrutinyId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	@Column(name="scrutiny_code")
	public String getScrutinyStatusCode() {
		return scrutinyStatusCode;
	}
	public void setScrutinyStatusCode(String scrutinyStatusCode) {
		this.scrutinyStatusCode = scrutinyStatusCode;
	}
	@Column(name="tender_sec_code")
	public String getTenderSecCode() {
		return tenderSecCode;
	}
	public void setTenderSecCode(String tenderSecCode) {
		this.tenderSecCode = tenderSecCode;
	}
	@Column(name="scrutiny_type_code")
	public String getScrutinyTypeCode() {
		return scrutinyTypeCode;
	}
	public void setScrutinyTypeCode(String scrutinyTypeCode) {
		this.scrutinyTypeCode = scrutinyTypeCode;
	}
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="scrutiny")
	public Set<ItemScrutiny> getItemScrutinyList() {
		return itemScrutinyList;
	}
	public void setItemScrutinyList(Set<ItemScrutiny> itemScrutinyList) {
		this.itemScrutinyList = itemScrutinyList;
	}*/

}
