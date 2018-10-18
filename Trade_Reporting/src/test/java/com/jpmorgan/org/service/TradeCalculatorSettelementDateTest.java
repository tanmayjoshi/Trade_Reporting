package com.jpmorgan.org.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNoException;

import com.jpmorgan.org.exception.TradingException;
import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.utilities.Constants;

public class TradeCalculatorSettelementDateTest {

	
	@Test
    public void calculateSettlementDateForMonday() throws Exception {
		List<TradeDataVO> tradeDataVOs = new ArrayList<TradeDataVO>();
        IReportGenerator reportGenerator = new ReportGeneratorImpl();
        
        final LocalDate dummySettlementDate = LocalDate.of(2018, 10, 8); // It is a Monday
        tradeDataVOs.add(new TradeDataVO("Etherum", 
				1,
				"B",
				0.50,
				"ETH",
				LocalDate.of(2018, 10, 01),
				dummySettlementDate,
				2,
				16850.25));
        
        //Format Data calculating settelement date
        reportGenerator.formatInstructions(tradeDataVOs);
        
        //Should be same its Monday so no change in settelement Date
        tradeDataVOs.forEach(data -> {
            assertEquals(dummySettlementDate, data.getSettlementDate());
        });
	}
	
	@Test
    public void calculateSettlementDateForWeekend() throws Exception {
		List<TradeDataVO> tradeDataVOs = new ArrayList<TradeDataVO>();
        IReportGenerator reportGenerator = new ReportGeneratorImpl();
        
        final LocalDate dummySettlementDate = LocalDate.of(2018, 10, 15); // It is a Monday
        tradeDataVOs.add(new TradeDataVO("Ripple", 
				1,
				"B",
				0.50,
				"ETH",
				LocalDate.of(2018, 10, 01),
				LocalDate.of(2018, 10, 13), // It is saturday
				2,
				16850.25));
        
        //Format Data calculating settelement date
        reportGenerator.formatInstructions(tradeDataVOs);
        
        //Should be same its saturday so  change in settelement Date to Monday
        tradeDataVOs.forEach(data -> {
            assertEquals(dummySettlementDate, data.getSettlementDate());
        });
	}
	
	@Test
    public void calculateSettlementDateForIslamicWeekend() throws Exception {
		List<TradeDataVO> tradeDataVOs = new ArrayList<TradeDataVO>();
        IReportGenerator reportGenerator = new ReportGeneratorImpl();
        
        final LocalDate dummySettlementDate = LocalDate.of(2018, 10, 21); // It is a Friday
        tradeDataVOs.add(new TradeDataVO("Ripple", 
				1,
				"B",
				0.50,
				"SAR",
				LocalDate.of(2018, 10, 01),
				LocalDate.of(2018, 10, 19), // It is saturday
				2,
				16850.25));
        
        //Format Data calculating settelement date
        reportGenerator.formatInstructions(tradeDataVOs);
        
        //Should be same its Friday so change in settelement Date to Sunday
        tradeDataVOs.forEach(data -> {
            assertEquals(dummySettlementDate, data.getSettlementDate());
        });
	}


}
