package com.novelerp.eat.entity;

import java.util.Set;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
/*@Entity
@Table(name="t_commercial_scrutiny")*/
public class CommercialScrutiny extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long commercialScrutinyId;
	private ItemScrutiny itemScrutiny;
	private String deviationStatus;
	private Set<CommercialScrutinyPoint> commercialScrutinyPoint;
	
	/*@Id
	@SequenceGenerator(name="t_commercial_scrutiny_seq",sequenceName="t_commercial_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_commercial_scrutiny_seq")	
	@Column(name = "t_commercial_scrutiny_id", updatable=false)
	public Long getCommercialScrutinyId() {
		return commercialScrutinyId;
	}
	public void setCommercialScrutinyId(Long commercialScrutinyId) {
		this.commercialScrutinyId = commercialScrutinyId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_scrutiny_id")
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="commercialScrutiny")
	public Set<CommercialScrutinyPoint> getCommercialScrutinyPoint() {
		return commercialScrutinyPoint;
	}
	public void setCommercialScrutinyPoint(Set<CommercialScrutinyPoint> commercialScrutinyPoint) {
		this.commercialScrutinyPoint = commercialScrutinyPoint;
	}
	@Column(name="deviation_status")
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}*/
	
	
	
	
}
