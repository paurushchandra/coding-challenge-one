package com.abide.coding.challenge;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abide.coding.challenge.handler.publisher.Publisher;
import com.abide.coding.challenge.listener.DefaultListenerContainer;
import com.abide.coding.challenge.processor.Processor;
import com.abide.coding.challenge.service.ReportGenerationService;

public class AppMultiThreadedTwo {

	private static Logger LOGGER = Logger.getLogger(AppMultiThreadedTwo.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/application-context-multi-threaded-two.xml");
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

		AtomicInteger prescriptionMessageCounter = context.getBean("prescriptionMessageCounter", AtomicInteger.class);
		LOGGER.info("syncronizing threads");
		while (prescriptionMessageCounter.get() < i) {
			LOGGER.info("waiting for a sec. total processed so far: " + prescriptionMessageCounter.get());
			Thread.sleep(1);
		}
		LOGGER.info("total number of prescription processed: " + prescriptionMessageCounter.get());

		LOGGER.info("publishing results to datastore");
		Collection<Publisher> publishers = context.getBeansOfType(Publisher.class).values();
		for (Publisher pub : publishers) {
			pub.publish();
		}
		LOGGER.info("data published");

		LOGGER.info("generating report");
		ReportGenerationService reportGenerationService = context.getBean(ReportGenerationService.class);
		LOGGER.info(reportGenerationService.generateReport());

		long endTime = System.nanoTime();
		double difference = (endTime - startTime) / 1e6;

		context.getBean(DefaultListenerContainer.class).shutdown();
		LOGGER.info("exiting main - " + difference);

	}
}
