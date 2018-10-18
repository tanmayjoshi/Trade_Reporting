package com.jpmorgan.org.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Test;

import com.jpmorgan.org.modal.TradeDataVO;

public class ShowcaseReportTest {

	
public static List<TradeDataVO> getInstructions(){
		
		List<TradeDataVO> tradeDataVOs = new ArrayList<TradeDataVO>(); 
		
		tradeDataVOs.add(new TradeDataVO("Etherum", 
				1,
				"B",
				0.50,
				"ETH",
				LocalDate.of(2018, 10, 01),
				LocalDate.of(2018, 10, 13),
				2,
				16850.25));
		tradeDataVOs.add(new TradeDataVO("Ripple",
				2, 
				"S", 
				0.22, 
				"AED", 
				LocalDate.of(2018, 10, 03),
				LocalDate.of(2018, 10, 12),
				750, 
				43.10));
		
		tradeDataVOs.add(new TradeDataVO("GOLEM", 
				3,
				"S", 
				0.22, 
				"SAR", 
				LocalDate.of(2018, 10, 03),
				LocalDate.of(2018, 10, 03),
				150, 
				10.90));
		tradeDataVOs.add(new TradeDataVO("Tron", 
				4,
				"S", 
				0.32, 
				"AED", 
				LocalDate.of(2018, 10, 03),
				LocalDate.of(2018, 10, 05),
				2000, 
				1.90));
		tradeDataVOs.add(new TradeDataVO("EOS",
				5, 
				"B", 
				0.60, 
				"AED", 
				LocalDate.of(2018, 10, 03),
				LocalDate.of(2018, 10, 17),
				450, 
				423.96));
		tradeDataVOs.add(new TradeDataVO("BITCOIN", 
				6,
				"B", 
				0.15, 
				"SAR", 
				LocalDate.of(2018, 11, 13),
				LocalDate.of(2018, 11, 17),
				3, 
				499975.01));
		
		return tradeDataVOs;
	}

	public List<TradeDataVO> getReadyDataToTest() {
		IReportGenerator reportGenerator = new ReportGeneratorImpl();
		List<TradeDataVO> tradeDataVOs = getInstructions();
		reportGenerator.formatInstructions(tradeDataVOs);
		
		return tradeDataVOs;
	}
	
	@Test
	public void generateSettlementPerdayTest() {
		ShowcaseReport report = new  ShowcaseReport();
		report.generateSettlementPerday(getReadyDataToTest());
	}
	
	@Test
	public void generateDailyRanksTest() {
		ShowcaseReport report = new  ShowcaseReport();
		 report.generateDailyRanks(getReadyDataToTest());
	}

}
