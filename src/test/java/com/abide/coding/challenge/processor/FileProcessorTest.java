package com.abide.coding.challenge.processor;

import org.junit.Test;
import org.mockito.Mockito;

import com.abide.coding.challenge.handler.Handler;
import com.abide.coding.challenge.mapper.RegionMapper;
import com.abide.coding.challenge.model.Region;

import junit.framework.Assert;

public class FileProcessorTest {

	Handler<Region> handler = Mockito.mock(Handler.class);
	FileProcessor<Region> fileProcessor = new FileProcessor<Region>("/input/england-regions.csv", new RegionMapper(),
			handler, true);

	@Test
	public void test_processor() {
		int count = fileProcessor.process();
		Mockito.verify(handler, Mockito.atLeast(6)).handle(Mockito.any(Region.class));
		Assert.assertEquals(6, count);
	}

}
