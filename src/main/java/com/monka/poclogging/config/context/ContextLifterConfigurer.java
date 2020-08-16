package com.monka.poclogging.config.context;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

public abstract class ContextLifterConfigurer {
	
	public abstract String getContextKey();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void contextOperatorHook() {
		Hooks.onEachOperator(getContextKey(), Operators.lift((scannable, coreSubscriber) -> getSubscriber(coreSubscriber)));
	}
	
	@SuppressWarnings("rawtypes")
	public abstract CoreSubscriber getSubscriber(CoreSubscriber coreSubscriber);
	
	@PreDestroy
	private void cleanupHook() {
		Hooks.resetOnEachOperator(getContextKey());
	}

}
