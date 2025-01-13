package com.novelerp.alkyl.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.alkyl.entity.PurchaseOrderLine;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;


public class SecurityPOItemDto extends CommonContextDto{
private static final long serialVersionUID = 1L;

private String name;
private String code;
private String description;
@JsonIdentityInfo(generator=PropertyGenerator.class,property="securityAsnLineId",scope=Long.class)
private Long securityAsnLineId;
private String lineItemNo;
private Double deliveryQuantity=new Double(0);
private PurchaseOrderLine poLine; 
private SecurityPOHeaderDto securityPOHeaderDto;

public Long getsecurityAsnLineId() {
	return securityAsnLineId;
}
public void setsecurityAsnLineId(Long securityAsnLineId) {
	this.securityAsnLineId = securityAsnLineId;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getLineItemNo() {
	return lineItemNo;
}
public void setLineItemNo(String lineItemNo) {
	this.lineItemNo = lineItemNo;
}
public Double getDeliveryQuantity() {
	if(null==this.deliveryQuantity){
		return new Double(0);
	}
	return deliveryQuantity;
}
public void setDeliveryQuantity(Double deliveryQuantity) {
	this.deliveryQuantity = deliveryQuantity;
}

public SecurityPOHeaderDto getSecurityPOHeaderDto() {
	return securityPOHeaderDto;
}
public void setSecurityPOHeaderDto(SecurityPOHeaderDto securityPOHeaderDto) {
	this.securityPOHeaderDto = securityPOHeaderDto;
}
public PurchaseOrderLine getPoLine() {
	return poLine;
}
public void setPoLine(PurchaseOrderLine poLine) {
	this.poLine = poLine;
}



}
