package com.novelerp.appbase.master.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name="c_currency")
public class Currency extends ContextPO {

	private static final long serialVersionUID = -3614215073799629429L;

	private Long currencyId;
	private String isoCode;
	private String curSymbol;
	private String description;
	private int stdPrecision;
	private int costingPrecision;
	private String isEuro;
	private String isEmember;
	private Timestamp emuentryDate;
	private int emuRate;
	private int roundOffFactor;
	

	@Id
	@Column(name="c_currency_id")
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	@Column(name="iso_code")
	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	@Column(name="cur_symbol")
	public String getCurSymbol() {
		return curSymbol;
	}

	public void setCurSymbol(String curSymbol) {
		this.curSymbol = curSymbol;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="std_precision")
	public int getStdPrecision() {
		return stdPrecision;
	}

	public void setStdPrecision(int stdPrecision) {
		this.stdPrecision = stdPrecision;
	}
	@Column(name="costing_precision")
	public int getCostingPrecision() {
		return costingPrecision;
	}

	public void setCostingPrecision(int costingPrecision) {
		this.costingPrecision = costingPrecision;
	}
	@Column(name="iseuro")
	public String getIsEuro() {
		return isEuro;
	}

	public void setIsEuro(String isEuro) {
		this.isEuro = isEuro;
	}
	@Column(name="isemumember")
	public String getIsEmember() {
		return isEmember;
	}

	public void setIsEmember(String isEmember) {
		this.isEmember = isEmember;
	}
	@Column(name="emuentry_date")
	public Timestamp getEmuentryDate() {
		return emuentryDate;
	}

	public void setEmuentryDate(Timestamp emuentryDate) {
		this.emuentryDate = emuentryDate;
	}
	@Column(name="emurate")
	public int getEmuRate() {
		return emuRate;
	}

	public void setEmuRate(int emuRate) {
		this.emuRate = emuRate;
	}
	@Column(name="round_off_factor")
	public int getRoundOffFactor() {
		return roundOffFactor;
	}

	public void setRoundOffFactor(int roundOffFactor) {
		this.roundOffFactor = roundOffFactor;
	}
	
}
