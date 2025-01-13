package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.InfraApprovalLevelDao;
import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.entity.InfraApprovalLevel;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class InfraApprovalLevelDaoImpl extends AbstractJpaDAO<InfraApprovalLevel, InfraApprovalLevelDto> implements InfraApprovalLevelDao {
	
	@PostConstruct
	private void init() {
		setClazz(InfraApprovalLevel.class, InfraApprovalLevelDto.class);
	}
	public String getQueryForRoleValidate(){
		StringBuilder jpql=new StringBuilder(" select l from InfraApprovalLevel l ")
		.append(" where l.role.roleId=:roleId and l.designation.designationId=:designationId ");
		return jpql.toString();
	}
}
