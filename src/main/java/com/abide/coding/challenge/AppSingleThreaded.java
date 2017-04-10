package com.abide.coding.challenge;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abide.coding.challenge.processor.Processor;
import com.abide.coding.challenge.service.ReportGenerationService;

public class AppSingleThreaded {

	private static Logger LOGGER = Logger.getLogger(AppSingleThreaded.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/application-context-single-threaded.xml");
		int i = 0;

		long startTime = System.nanoTime();

		LOGGER.info("building region cache");
		Processor regionFileProcessor = context.getBean("regionFileProcessor", Processor.class);
		i = regionFileProcessor.process();
		LOGGER.info("region cache loaded with records: " + i);

		LOGGER.info("processing prescriber file and loading prescriber cache");
		Processor prescriberFileProcessor = context.getBean("prescriberFileProcessor", Processor.class);
		i = prescriberFileProcessor.process();
		LOGGER.info("prescriber cache loaded with records: " + i);

		LOGGER.info("processing prescriptions");
		Processor prescriptionFileProcessor = context.getBean("prescriptionFileProcessor", Processor.class);
		i = prescriptionFileProcessor.process();
		LOGGER.info("prescriptions processed: " + i);

		LOGGER.info("generating report");
		ReportGenerationService reportGenerationService = context.getBean(ReportGenerationService.class);
		LOGGER.info(reportGenerationService.generateReport());

		long endTime = System.nanoTime();
		double difference = (endTime - startTime) / 1e6;

		LOGGER.info("exiting main - " + difference);

	}

}
