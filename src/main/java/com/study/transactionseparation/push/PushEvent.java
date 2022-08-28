package com.study.transactionseparation.push;

import lombok.Getter;

@Getter
public class PushEvent {

	private final Object entity;

	public PushEvent(Object entity) {
		this.entity = entity;
	}
}
