package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.TileMaster;

/** 
 * @author Aman
 *
 */

@Entity
@Table(name="ad_role_access_master")
public class RoleAccessMaster  extends ContextPO {

	private static final long serialVersionUID = 7991344774735272396L;
	
	private Long roleAccessMasterId;
	private Role role;
	private TileMaster tile;
	
	private String viewOnly;
	private String modifyAccess;
	private String deleteAccess;
	/*private String url;*/

	@Id
	@SequenceGenerator(name="ad_role_access_master_seq",sequenceName="ad_role_access_master_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_role_access_master_seq")	
	@Column(name = "ad_role_access_master_id", updatable=false)
	public Long getRoleAccessMasterId() {
		return roleAccessMasterId;
	}

	public void setRoleAccessMasterId(Long roleAccessMasterId) {
		this.roleAccessMasterId = roleAccessMasterId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_tile_id")
	public TileMaster getTile() {
		return tile;
	}
	public void setTile(TileMaster tile) {
		this.tile = tile;
	}
	@Column(name = "view_only")
	public String getViewOnly() {
		return viewOnly;
	}
	public void setViewOnly(String viewOnly) {
		this.viewOnly = viewOnly;
	}
	@Column(name = "modify_access")
	public String getModifyAccess() {
		return modifyAccess;
	}
	public void setModifyAccess(String modifyAccess) {
		this.modifyAccess = modifyAccess;
	}
	@Column(name = "delete_access")
	public String getDeleteAccess() {
		return deleteAccess;
	}
	public void setDeleteAccess(String deleteAccess) {
		this.deleteAccess = deleteAccess;
	}
	/*@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}*/
	
}
