package com.jpmorgan.org.service;

import java.util.List;

import com.jpmorgan.org.modal.TradeDataVO;

public class ReportGeneratorImpl implements IReportGenerator{

	/**
	 * 
	 * @param tradeDataVOs
	 * 
	 * format the Entity data such as settelment date and USD trade amount
	 */
	@Override
	public void formatInstructions(List<TradeDataVO> tradeDataVOs) {
		TradeCalculator tradeCalculator = new TradeCalculator();
		tradeCalculator.formatReportData(tradeDataVOs);
	}

	@Override
	public void generateInstructionsReport(List<TradeDataVO> tradeDataVOs) {
		IShowcaseReport showcaseReport = new ShowcaseReport();
		showcaseReport.generateSettlementPerday(tradeDataVOs);
		showcaseReport.generateDailyRanks(tradeDataVOs);
	}
	
	

}
