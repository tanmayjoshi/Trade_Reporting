package com.jpmorgan.org.modal;

import java.io.Serializable;
import java.time.LocalDate;


public class TradeDataVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8645490971189133048L;
	
	private String entity;
	private Integer entityId;
	private String byeSellFlag;
	private double agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Integer units;
	private double pricePerUnit;
	
	private LocalDate actualSettlementDate;
	private double usdTradeAmt;
	
	public TradeDataVO() {
		super();
	}

	public TradeDataVO(String entity, Integer entityId, String byeSellFlag, double agreedFx, String currency,
			LocalDate instructionDate, LocalDate settlementDate, Integer units, double pricePerUnit) {
		super();
		this.entity = entity;
		this.entityId = entityId;
		this.byeSellFlag = byeSellFlag;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getByeSellFlag() {
		return byeSellFlag;
	}
	public void setByeSellFlag(String byeSellFlag) {
		this.byeSellFlag = byeSellFlag;
	}
	public double getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	public LocalDate getActualSettlementDate() {
		return actualSettlementDate;
	}
	public void setActualSettlementDate(LocalDate actualSettlementDate) {
		this.actualSettlementDate = actualSettlementDate;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public double getUsdTradeAmt() {
		return usdTradeAmt;
	}
	public void setUsdTradeAmt(double usdTradeAmt) {
		this.usdTradeAmt = usdTradeAmt;
	}

	@Override
	public String toString() {
		return "TradeDataVO [entity=" + entity + ", entityId=" + entityId + ", byeSellFlag=" + byeSellFlag
				+ ", agreedFx=" + agreedFx + ", currency=" + currency + ", instructionDate=" + instructionDate
				+ ", settlementDate=" + settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit
				+ ", actualSettlementDate=" + actualSettlementDate + ", usdTradeAmt=" + usdTradeAmt + "]";
	}
	
}
