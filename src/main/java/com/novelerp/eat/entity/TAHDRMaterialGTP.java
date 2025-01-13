/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_tahdr_material_gtp")
public class TAHDRMaterialGTP extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long tahdrMaterialGtpId;
	private String isPublish;
	private TAHDRMaterial tahdrMaterial;
	private GtpParameter gtp;
	private TAHDRDetail tahdrDetail;
	
	@Id
	@SequenceGenerator(name="t_tahdr_material_gtp_seq",sequenceName="t_tahdr_material_gtp_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_tahdr_material_gtp_seq")	
	@Column(name = "t_tahdr_material_gtp_id", updatable=false)
	public Long getTahdrMaterialGtpId() {
		return tahdrMaterialGtpId;
	}
	public void setTahdrMaterialGtpId(Long tahdrMaterialGtpId) {
		this.tahdrMaterialGtpId = tahdrMaterialGtpId;
	}
	
	@Column(name="ispublish")
	public String getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_material_id")
	public TAHDRMaterial getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterial tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_gtp_id")
	public GtpParameter getGtp() {
		return gtp;
	}
	public void setGtp(GtpParameter gtp) {
		this.gtp = gtp;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
	
}
