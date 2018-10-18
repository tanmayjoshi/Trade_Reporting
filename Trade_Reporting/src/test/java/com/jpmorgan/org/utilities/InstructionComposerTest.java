package com.jpmorgan.org.utilities;

import static org.junit.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.service.IReportGenerator;
import com.jpmorgan.org.service.ReportGeneratorImpl;

public class InstructionComposerTest {

	@Test
	public void testGetInstructions() {
		List<TradeDataVO> tradeDataVOs = InstructionComposer.getInstructions();
		 assertNotNull(tradeDataVOs);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testTradeAmountCalc() throws Exception {
		List<TradeDataVO> tradeDataVOs = new ArrayList<TradeDataVO>(); 
		IReportGenerator reportGenerator = new ReportGeneratorImpl();
		tradeDataVOs.add(new TradeDataVO("Etherum", 
				1,
				"B",
				0.50,
				"ETH",
				LocalDate.of(2018, 10, 01),
				LocalDate.of(2018, 10, 13),
				2,
				16850.25));
			assertEquals(0.5, tradeDataVOs.get(0).getAgreedFx());
	        assertEquals(16850.25, tradeDataVOs.get(0).getPricePerUnit());
	        
	        reportGenerator.formatInstructions(tradeDataVOs);
	        
	        assertEquals(16850.25,tradeDataVOs.get(0).getUsdTradeAmt());
	}

}
