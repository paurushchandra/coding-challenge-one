package com.abide.coding.challenge.handler;

import java.util.List;

public class CompositeHandler<T> implements Handler<T> {

	private List<Handler<T>> handlers;

	public CompositeHandler(List<Handler<T>> handlers) {
		super();
		this.handlers = handlers;
	}

	@Override
	public void handle(T input) {
		for (Handler<T> handler : handlers) {
			handler.handle(input);
		}
	}

}
