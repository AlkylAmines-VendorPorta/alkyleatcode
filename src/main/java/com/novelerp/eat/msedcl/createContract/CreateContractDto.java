package com.novelerp.eat.msedcl.createContract;

import java.util.List;

import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.dto.ContractServiceDto;

public class CreateContractDto {

	private ContractHeaderDto zmmcontractheader;
	private List<ContractItemDto> contractItemList;
	private List<ContractConditionDto> contractItemCondList;
	private List<ContractServiceDto> contractItemserviceList;
	

	public ContractHeaderDto getZmmcontractheader() {
		return zmmcontractheader;
	}

	public void setZmmcontractheader(ContractHeaderDto zmmcontractheader) {
		this.zmmcontractheader = zmmcontractheader;
	}

	public List<ContractItemDto> getContractItemList() {
		return contractItemList;
	}

	public void setContractItemList(List<ContractItemDto> contractItemList) {
		this.contractItemList = contractItemList;
	}

	public List<ContractConditionDto> getContractItemCondList() {
		return contractItemCondList;
	}

	public void setContractItemCondList(List<ContractConditionDto> contractItemCondList) {
		this.contractItemCondList = contractItemCondList;
	}

	public List<ContractServiceDto> getContractItemserviceList() {
		return contractItemserviceList;
	}

	public void setContractItemserviceList(List<ContractServiceDto> contractItemserviceList) {
		this.contractItemserviceList = contractItemserviceList;
	}

}
