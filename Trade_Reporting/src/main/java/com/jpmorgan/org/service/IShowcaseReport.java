package com.jpmorgan.org.service;

import java.util.List;

import com.jpmorgan.org.modal.TradeDataVO;

public interface IShowcaseReport {

	/**
	 * 
	 * @param tradeDataVOs
	 * 
	 * Generate incoming(Buy) and utgoing(Sell) Settelments report
	 */
	public void generateSettlementPerday(List<TradeDataVO> tradeDataVOs);
	
	/**
	 * 
	 * @param tradeDataVOs
	 * 
	 * Generate Entities Ranked on Buying/selling settlements
	 */
	public void generateDailyRanks(List<TradeDataVO> tradeDataVOs);
}
