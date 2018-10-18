package com.jpmorgan.org.service;

import java.util.List;

import com.jpmorgan.org.modal.TradeDataVO;

public interface IReportGenerator {

	/**
	 * 
	 * @param tradeDataVOs
	 * 
	 * get reports for trades
	 */
	public void generateInstructionsReport(List<TradeDataVO> tradeDataVOs);

	void formatInstructions(List<TradeDataVO> tradeDataVOs);
}
