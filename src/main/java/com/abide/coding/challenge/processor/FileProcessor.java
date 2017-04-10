package com.abide.coding.challenge.processor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.abide.coding.challenge.handler.Handler;
import com.abide.coding.challenge.mapper.Mapper;

public class FileProcessor<T> implements Processor {

	private String fileName;
	private Mapper<String, T> mapper;
	private Handler<T> handler;
	private boolean excludeFirstRecord;

	public FileProcessor(String fileName, Mapper<String, T> mapper, Handler<T> handler, boolean excludeFirstRecord) {
		super();
		this.fileName = fileName;
		this.mapper = mapper;
		this.handler = handler;
		this.excludeFirstRecord = excludeFirstRecord;
	}

	@Override
	public int process() {
		InputStream source;
		int recordCount = 0;
		try {
			source = new BufferedInputStream(FileProcessor.class.getResourceAsStream(fileName));
			Scanner scanner = new Scanner(source);
			if (excludeFirstRecord) {
				scanner.nextLine();
			}
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				T record = mapper.map(line);
				handler.handle(record);
				recordCount++;
			}
			scanner.close();
			source.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordCount;
	}

}
