package com.jpmorgan.org.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;

import org.junit.Test;

import com.jpmorgan.org.exception.TradingException;
import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.utilities.Constants;
import com.jpmorgan.org.utilities.Constants.TradeType;

public class TradeCalculatorSameDayDailyTradeTest {

	
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
    public void dailyBuyTest() throws Exception {
		ShowcaseReport showcaseReport = new ShowcaseReport();
		Map<LocalDate, Double> settlementPerday = showcaseReport.settlementPerday(getReadyDataToTest(),  TradeType.BUY.getValue());
		
		 assertEquals(3, settlementPerday.size());
		 assertTrue(Objects.equals(settlementPerday.get(LocalDate.of(2018, 10, 15)), 16850.25));
		 assertTrue(Objects.equals(settlementPerday.get(LocalDate.of(2018, 11, 18)), 224988.7545));
		 
		//{2018-10-15=16850.25, 2018-11-18=224988.7545, 2018-10-17=114469.2}
	}
	
	@Test
    public void dailySellTest() throws Exception {
		ShowcaseReport showcaseReport = new ShowcaseReport();
		Map<LocalDate, Double> settlementPerday = showcaseReport.settlementPerday(getReadyDataToTest(),  TradeType.SELL.getValue());
		
		 assertEquals(3, settlementPerday.size());
		 assertTrue(Objects.equals(settlementPerday.get(LocalDate.of(2018, 10, 14)), 7111.5));
		 assertTrue(Objects.equals(settlementPerday.get(LocalDate.of(2018, 10, 03)), 359.7));
		 
		//{2018-10-14=7111.5, 2018-10-07=1216.0, 2018-10-03=359.7}
	}
	

	@Test
    public void dailyRankSellTest() throws Exception {
		ShowcaseReport showcaseReport = new ShowcaseReport();
		Map<String, SortedMap<LocalDate, List<TradeDataVO>>> resultSet =showcaseReport.getRankData(getReadyDataToTest());
		SortedMap<LocalDate, List<TradeDataVO>> sellMap =  resultSet.get("Selling"); 
		assertEquals(3, sellMap.size());
		assertEquals(1, sellMap.get(LocalDate.of(2018, 10, 03)).size());
	    assertEquals(1, sellMap.get(LocalDate.of(2018, 10, 14)).size());
		//[2018-10-03, 2018-10-07, 2018-10-14]
	}
	
	@Test
    public void dailyRankBuyTest() throws Exception {
		ShowcaseReport showcaseReport = new ShowcaseReport();
		Map<String, SortedMap<LocalDate, List<TradeDataVO>>> resultSet =showcaseReport.getRankData(getReadyDataToTest());
		SortedMap<LocalDate, List<TradeDataVO>> buyMap =  resultSet.get("Buying"); 
		buyMap.keySet();
		assertEquals(3, buyMap.size());
		assertEquals(1, buyMap.get(LocalDate.of(2018, 10, 15)).size());
	    assertEquals(1, buyMap.get(LocalDate.of(2018, 11, 18)).size());
		//[2018-10-15, 2018-10-17, 2018-11-18]
	}
	

	@Test(expected = TradingException.class)
    public void exceptionTest() {
		try {
			List<TradeDataVO> dataVOs = getReadyDataToTest();
			dataVOs = null;
			dataVOs.get(5);
		}catch(Exception exception) {
			throw new TradingException(Constants.ErrorCodes.TRADE003.getValue(), exception );
		}
	}
	
}
