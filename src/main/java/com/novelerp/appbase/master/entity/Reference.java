/**
	 * @author Ankush
	 *
	 */
package com.novelerp.appbase.master.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_reference")
public class Reference extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long referenceId;
	private String name;
	private String description;
	private String code;
	private Set<ReferenceList> referenceList;
	
	@Id
	@SequenceGenerator(name="m_reference_seq",sequenceName="m_reference_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_reference_seq")	
	@Column(name = "m_reference_id", updatable=false)
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy= "reference")
	public Set<ReferenceList> getReferenceList() {
		return referenceList;
	}
	public void setReferenceList(Set<ReferenceList> referenceList) {
		this.referenceList = referenceList;
	}
	
}