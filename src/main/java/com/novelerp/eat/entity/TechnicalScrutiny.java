package com.novelerp.eat.entity;

import java.util.Set;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
/*@Entity
@Table(name="t_tech_scrutiny")*/

public class TechnicalScrutiny extends ContextPO{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long technicalScrutinyId;
	private ItemScrutiny itemScrutiny;
	private String deviationStatus;
	private Set<GtpScrutiny> gtpScrutinyList;
	
	/*@Id
	@SequenceGenerator(name="t_tech_scrutiny_seq",sequenceName="t_tech_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_tech_scrutiny_seq")	
	@Column(name = "t_tech_scrutiny_id", updatable=false)
	public Long getTechnicalScrutinyId() {
		return technicalScrutinyId;
	}
	public void setTechnicalScrutinyId(Long technicalScrutinyId) {
		this.technicalScrutinyId = technicalScrutinyId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_scrutiny_id")
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	@Column(name="deviation_status")
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="technicalScrutiny")
	public Set<GtpScrutiny> getGtpScrutinyList() {
		return gtpScrutinyList;
	}
	public void setGtpScrutinyList(Set<GtpScrutiny> gtpScrutinyList) {
		this.gtpScrutinyList = gtpScrutinyList;
	}
	*/
}
