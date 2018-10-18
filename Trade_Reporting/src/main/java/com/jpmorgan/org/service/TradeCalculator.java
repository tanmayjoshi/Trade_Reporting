package com.jpmorgan.org.service;

import java.time.LocalDate;
import java.util.List;

import com.jpmorgan.org.exception.TradingException;
import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.utilities.Constants;

public class TradeCalculator {

	
	/**
	 * 
	 * @param tradeDataList
	 * 
	 * Alter the records and set USD Trade Amount & Settelement Date
	 */
	public void formatReportData(List<TradeDataVO> tradeDataList) {
		try {
			for(TradeDataVO data : tradeDataList) {
				data.setUsdTradeAmt(data.getPricePerUnit()*data.getUnits()*data.getAgreedFx()); 
				alterSettlementDate(data);
			}
		}catch (TradingException exception) {
			throw new TradingException(Constants.ErrorCodes.TRADE003.getValue(), exception );
		} 
	}
	
	
	/**
	 * 
	 * @param tradeDataVO
	 * 
	 * Calculate Settelement depending on the region and working days
	 * 
	 * MON 1,TUE 2, WED 3, THU 4, FRI 5, SAT 6, SUN 7
	 */
	public void alterSettlementDate(TradeDataVO tradeDataVO) {

		final String SAUDI_RIYAL_CURRENCY= "SAR";
		final String UAE_CURRENCY = "AED";
		try {
			LocalDate date =  tradeDataVO.getSettlementDate();
			final int dayOfSettlementDate = date.getDayOfWeek().getValue();
			
			tradeDataVO.setActualSettlementDate(date);
			if((SAUDI_RIYAL_CURRENCY.equalsIgnoreCase(tradeDataVO.getCurrency()) ||
					UAE_CURRENCY.equalsIgnoreCase(tradeDataVO.getCurrency())) &&
					Constants.arabWeekDays.contains(dayOfSettlementDate)) {
				date = dayOfSettlementDate == 5 ?date.plusDays(2): date.plusDays(1);
				
			}else if((!SAUDI_RIYAL_CURRENCY.equalsIgnoreCase(tradeDataVO.getCurrency()) ||
					!UAE_CURRENCY.equalsIgnoreCase(tradeDataVO.getCurrency())) && 
					Constants.englishWeekDays.contains(dayOfSettlementDate)) {
				date = dayOfSettlementDate == 6 ?date.plusDays(2): date.plusDays(1);
			}
			tradeDataVO.setSettlementDate(date);
			
		} catch (TradingException exception) {
			throw new TradingException(Constants.ErrorCodes.TRADE001.getValue(), exception );
		} 
		
	}
}
