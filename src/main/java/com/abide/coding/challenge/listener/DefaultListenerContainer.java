package com.abide.coding.challenge.listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DefaultListenerContainer<T> implements ApplicationContextAware {

	private static final Logger LOGGER = Logger.getLogger(DefaultListenerContainer.class);
	private String listenerBeanName;
	private int numberOfListeners;
	private ExecutorService executorService;
	private ApplicationContext context;

	public DefaultListenerContainer(String listenerBeanName, int numberOfListeners) {
		super();
		this.listenerBeanName = listenerBeanName;
		this.numberOfListeners = numberOfListeners;
		this.executorService = Executors.newFixedThreadPool(numberOfListeners);
	}

	@SuppressWarnings("unchecked")
	public void start() {
		LOGGER.info("starting listener container");
		for (int i = 1; i <= numberOfListeners; i++) {
			LOGGER.info("submitting listener no. " + i);
			DefaultListener<T> defaultListener = context.getBean(listenerBeanName, DefaultListener.class);
			executorService.submit(defaultListener);
		}
	}

	public void shutdown() {
		executorService.shutdownNow();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

}
