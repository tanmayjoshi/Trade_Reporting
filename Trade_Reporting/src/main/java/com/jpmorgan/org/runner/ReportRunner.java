package com.jpmorgan.org.runner;

import java.util.List;

import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.service.IReportGenerator;
import com.jpmorgan.org.service.ReportGeneratorImpl;
import com.jpmorgan.org.utilities.InstructionComposer;

public class ReportRunner {

	public static void main(String[] args) {
	
		final List<TradeDataVO> tradeDataVOs = InstructionComposer.getInstructions();
		IReportGenerator reportGenerator = new ReportGeneratorImpl();
		reportGenerator.formatInstructions(tradeDataVOs);
		reportGenerator.generateInstructionsReport(tradeDataVOs);
	}

}
