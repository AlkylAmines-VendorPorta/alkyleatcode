package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.SendInvitationDao;
import com.novelerp.appbase.master.dto.MBPartnerInvitationDto;
import com.novelerp.appbase.master.entity.MBPartnerInvitation;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class SendInvitationDaoImpl extends AbstractJpaDAO<MBPartnerInvitation, MBPartnerInvitationDto> implements SendInvitationDao{

private static final String ENTITY_NAME="m_bpartner_invitation";
	
@PersistenceContext
private EntityManager em;
	@PostConstruct
	private void init() {
		setClazz(MBPartnerInvitation.class, MBPartnerInvitationDto.class);
	}
	
	public String getInvitationByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from MBPartnerInvitation i ")
		.append(" WHERE i.partner.bPartnerId = :BPartnerId ORDER BY i.updated DESC");
		/*.append(" AND i.isInvitationApproved = 'N'");*/
		
		return jpql.toString();
	}
	public String getInvitationBPartnerByPan(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from MBPartnerInvitation i ")
		.append(" LEFT JOIN FETCH i.partner p")
		.append(" WHERE i.vendorPancardNo = :PanNo")
		.append(" AND i.isInvitationApproved ='N'");
		
		return jpql.toString();
	}
	public String getQueryForInvitedVendor(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from MBPartnerInvitation i ")
		.append(" LEFT JOIN FETCH i.partner p")
		.append(" LEFT JOIN FETCH i.requestedPartner rp ")
		.append(" WHERE upper(i.vendorPancardNo) = upper(:PanNo) AND i.partner.bPartnerId=:partnerId");
		
		return jpql.toString();
	}
	public String getQueryForInvitedVendorByEmail(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from MBPartnerInvitation i ")
		.append(" LEFT JOIN FETCH i.partner p")
		.append(" LEFT JOIN FETCH i.requestedPartner rp ")
		.append(" WHERE upper(i.vendorEmailId) = upper(:email) AND i.partner.bPartnerId=:partnerId");
		
		return jpql.toString();
	}
	public String getInvitationListByID(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from MBPartnerInvitation i ")
		.append(" LEFT JOIN FETCH i.partner p")
		.append(" WHERE i.mBPartnerInvitationId = :invitationID")
		.append(" AND i.isInvitationApproved ='N'");
		
		return jpql.toString();
	}
}
